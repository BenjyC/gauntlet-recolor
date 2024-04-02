package com.gauntletrecolor;

import com.gauntletrecolor.util.ColorDebugUtils;
import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.groundmarkers.GroundMarkerOverlay;
import net.runelite.client.util.ColorUtil;
import net.runelite.client.ui.overlay.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

import static com.gauntletrecolor.RecolorConstants.*;

@Slf4j
@PluginDescriptor(
	name = "Gauntlet Recolor"
)
public class GauntletRecolorPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private GauntletRecolorConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private RecolorGroundOverlay overlay;

	private GameObject gameObj;
	private int[] defaultObjFaceColors1;
	private int[] defaultObjFaceColors2;
	private int[] defaultObjFaceColors3;
	private RuneLiteObject runeliteObject;
	private boolean colorSearch = true;
	private Set<Integer> colorsToChange = new HashSet<>();

	@Getter(AccessLevel.PACKAGE)
	private final List<ColorTileMarker> points = new ArrayList<>();

	@Override
	protected void startUp() throws Exception
	{
		log.info("Gauntlet Recolor started!");
		//overlayManager.add(overlay);
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned event)
	{
		GameObject gameObj = event.getGameObject();
		int objId = gameObj.getId();
		if (BLUE_GAUNTLET_ID_MAP.keySet().contains(objId)) {
			clientThread.invoke(() -> replaceWallModels(gameObj, BLUE_GAUNTLET_ID_MAP.get(objId)));
		}

		if (LOBBY_GAME_MODELS.contains(gameObj.getId()) || BLUE_GAUNTLET_GAME_MODELS.contains(gameObj.getId())) {
			final Model model = gameObj.getRenderable().getModel();
			if (model == null) {
				Model newModel = (Model) gameObj.getRenderable();
				clientThread.invoke(() -> replaceGameObjModels(newModel, LOBBY_GAME_OBJ_HSL_RANGE_VALUES));
			} else {
				clientThread.invoke(() -> replaceGameObjModels(model, LOBBY_GAME_OBJ_HSL_RANGE_VALUES));
			}
		}
	}

	private void replaceWallModels(GameObject gameObject, int modelId) {
		runeliteObject = client.createRuneLiteObject();

		LocalPoint lp = gameObject.getLocalLocation();
		ModelData wallModel = client.loadModelData(modelId).cloneColors();

		// Get correct color mapping for each wall model and recolor
		for (Map.Entry<Integer, Map> recolorMapping: MODEL_RECOLOR_MAPPING.entrySet()) {
			Map<Short,Short> colorMap = recolorMapping.getValue();
			for (Map.Entry<Short,Short> colorCodes : colorMap.entrySet()) {
				wallModel.recolor(colorCodes.getKey(), colorCodes.getValue());
			}
		}

		Scene scene = client.getScene();
		scene.removeGameObject(gameObject);

		runeliteObject.setActive(false);
		runeliteObject.setModel(wallModel.light());
		runeliteObject.setOrientation(gameObject.getOrientation());
		runeliteObject.setLocation(lp, client.getPlane());
		runeliteObject.setActive(true);
	}

	private void replaceGameObjModels(Model model, int[] hslArray) {
		final int[] faceColors1 = model.getFaceColors1();
		final int[] faceColors2 = model.getFaceColors2();
		final int[] faceColors3 = model.getFaceColors3();

		ColorDebugUtils.changeGameObjectColors(faceColors1, hslArray);
		ColorDebugUtils.changeGameObjectColors(faceColors2, hslArray);
		ColorDebugUtils.changeGameObjectColors(faceColors3, hslArray);
	}

	@Subscribe
	public void onGroundObjectSpawned(GroundObjectSpawned event)
	{
		GroundObject groundObj = event.getGroundObject();
		if (BLUE_FLOOR_IDS.contains(groundObj.getId())) {
//			Scene scene = client.getScene();
//			Tile[] tiles = scene.getTiles()[client.getPlane()][0];
//
//			SceneTilePaint paint = tiles[0].getSceneTilePaint();
			final Model model = groundObj.getRenderable().getModel();
			if (model == null) {
				Model newModel = (Model) groundObj.getRenderable();
				clientThread.invoke(() -> replaceFloors(newModel, LOBBY_FLOOR_HSL_RANGE_VALUES));
			} else {
				clientThread.invoke(() -> replaceFloors(model, LOBBY_FLOOR_HSL_RANGE_VALUES));
			}
		}
	}

	private void replaceFloors(Model model, int[] hslArray) {
		final int[] faceColors1 = model.getFaceColors1();
		final int[] faceColors2 = model.getFaceColors2();
		final int[] faceColors3 = model.getFaceColors3();

		replaceFloorColorValues(faceColors1, faceColors2, faceColors3, JagexColor.packHSL(9,3,50));
	}

	private void replaceFloorColorValues(int[] faceColors1, int[] faceColors2, int[] faceColors3, short color) {
		for (int i = 0; i < faceColors1.length; i++) {
			faceColors1[i] = color;
		}
		for (int i = 0; i < faceColors2.length; i++) {
			faceColors2[i] = color;
		}
		for (int i = 0; i < faceColors3.length; i++) {
			faceColors3[i] = color;
		}
	}

//	private void replaceFaceColorValues(int[] faceColors1, int[] faceColors2, int[] faceColors3, int color) {
//		for (int i = 0; i < faceColors1.length; i++) {
//			if (colorsToChange.contains(faceColors1[i])) {
//				faceColors1[i] = color;
//			}
//		}
//		for (int i = 0; i < faceColors2.length; i++) {
//			if (colorsToChange.contains(faceColors2[i])) {
//				faceColors2[i] = color;
//			}
//		}
//		for (int i = 0; i < faceColors3.length; i++) {
//			if (colorsToChange.contains(faceColors3[i])) {
//				faceColors3[i] = color;
//			}
//		}
//	}

	@Subscribe
	void onNpcSpawned(final NpcSpawned event) {
		final NPC npc = event.getNpc();

		if (9021 == npc.getId()) {
			log.info("Hunllef spawned");
			final Model model = npc.getModel();
			if (model == null) {
				Model newModel = (Model) npc;
				clientThread.invoke(() -> replaceGameObjModels(newModel, LOBBY_GAME_OBJ_HSL_RANGE_VALUES));
			} else {
				clientThread.invoke(() -> replaceGameObjModels(model, LOBBY_GAME_OBJ_HSL_RANGE_VALUES));
			}
		}
	}

	private static int colorToRs2hsb(final Color color) {
		final float[] hsbVals = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

		// "Correct" the brightness level to avoid going to white at full saturation, or having a low brightness at
		// low saturation
		hsbVals[2] -= Math.min(hsbVals[1], hsbVals[2] / 2);

		final int encode_hue = (int) (hsbVals[0] * 63);
		final int encode_saturation = (int) (hsbVals[1] * 7);
		final int encode_brightness = (int) (hsbVals[2] * 127);
		return (encode_hue << 10) + (encode_saturation << 7) + (encode_brightness);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

	@Provides
	GauntletRecolorConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(GauntletRecolorConfig.class);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Gauntlet Recolor stopped!");
		//overlayManager.remove(overlay);
	}
}
