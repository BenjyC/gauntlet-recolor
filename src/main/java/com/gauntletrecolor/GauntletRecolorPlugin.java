package com.gauntletrecolor;

import com.gauntletrecolor.util.ColorDebugUtils;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GroundObjectSpawned;
import net.runelite.api.events.GameStateChanged;
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
		log.info("Example started!");
		overlayManager.add(overlay);
	}

	void loadPoints() {
		System.out.println("inside loadpoints");
		points.clear();

		int[] regions = client.getMapRegions();
		System.out.println(regions);

		if (regions == null) {
			return;
		}


//		for (int regionId : regions) {
////			if (regionId == ?) {
////
////			}
//			System.out.println("@@@@@@@@@@@@@@@@@@@@@");
//			System.out.println(regionId);
////			Collection<GroundMarkerPoint> regionPoints = getActivePoints(regionId);
////			Collection<ColorTileMarker> colorTileMarkers = translateToColorTileMarker(regionPoints);
////			points.addAll(colorTileMarkers);
//		}
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned event)
	{
		GameObject gameObj = event.getGameObject();
		final Model model = gameObj.getRenderable().getModel();
		int objId = gameObj.getId();
		//TODO: If blue gauntlet, is that needed or can I just update colors everywhere
		if (BLUE_GAUNTLET_ID_MAP.keySet().contains(objId)) {
			Scene scene = client.getScene();
			clientThread.invoke(() -> replaceWallModels(gameObj, BLUE_GAUNTLET_ID_MAP.get(objId)));
			scene.removeGameObject(gameObj);
		}
		//36063 singing bowl
		//36077 range
		if (36063 == gameObj.getId()) {
			if (model == null) {
				Model newModel = (Model) gameObj.getRenderable();
				clientThread.invoke(() -> replaceGameObjModels(newModel));
			} else {
				clientThread.invoke(() -> replaceGameObjModels(model));
			}
		}
	}

	private void replaceGameObjModels(Model model) {
		final int[] faceColors1 = model.getFaceColors1();
		final int[] faceColors2 = model.getFaceColors2();
		final int[] faceColors3 = model.getFaceColors3();
		final Color color = new Color(234, 226, 169);
//		final Color color = new Color(208, 62, 155);

		replaceFaceColorValues(faceColors1, faceColors2, faceColors3, colorToRs2hsb(color));
	}

	@Subscribe
	public void onGroundObjectSpawned(GroundObjectSpawned event)
	{
		GroundObject groundObj = event.getGroundObject();
		if (LOBBY_FLOOR_OBJ_ID == groundObj.getId())
		{
			loadPoints();
			//log.info("Lobby ground objects spawned");
//			scene.removeGroundObject(obj);

//			Scene scene = client.getScene();
			//overlayManager.add(overlay);

//			Tile[][] tiles = scene.getTiles()[client.getPlane()];
//			Tile tile = tiles[groundObj.getLocalLocation().getSceneX()][groundObj.getLocalLocation().getSceneY()];
//			SceneTilePaint stp = tile.getSceneTilePaint();
//			for (int x = 0; x < Constants.SCENE_SIZE; x++) {
//				for (int y = 0; y < Constants.SCENE_SIZE; y++) {
//				}
//			}
			//clientThread.invoke(() -> replaceFloors(groundObj, 37409));
		}
	}

	private void replaceFloors(GroundObject groundObject, int modelId) {
		log.info("Inside replaceFloors");
		runeliteObject = client.createRuneLiteObject();

		Renderable groundRenderable = groundObject.getRenderable();
		Model groundModel = groundRenderable.getModel() != null ? groundRenderable.getModel() : (Model) groundRenderable;

		final Color color = new Color(208, 62, 155);


		short floor1 = JagexColor.packHSL(32,7,6);
		short floor2 = JagexColor.packHSL(32,7,10);
		short floor3 = JagexColor.packHSL(32,7,12);
//		HUE 32, SAT 7, LUM 134
//		HUE 32, SAT 7, LUM 138
//		HUE 32, SAT 7, LUM 140

		short floor1New = JagexColor.packHSL(9,7,6);
		short floor2New = JagexColor.packHSL(9,7,10);
		short floor3New = JagexColor.packHSL(9,7,12);

		ModelData floorTile = client.loadModelData(modelId).cloneColors();

//		//========= Find colors loop - Model =========
//		if (colorSearch) {
//			System.out.println("Before finding model colors");
//			ColorDebugUtils.findColors(floorTile);
//			colorSearch = false;
//			System.out.println("After finding model colors");
//		}

		LocalPoint lp = groundObject.getLocalLocation();

		floorTile.recolor(floor1, floor1New);
		floorTile.recolor(floor2, floor2New);
		floorTile.recolor(floor3, floor3New);

		runeliteObject.setActive(false);
		runeliteObject.setModel(floorTile.light());
		runeliteObject.setLocation(lp, client.getPlane());
		runeliteObject.setActive(true);
	}

	private void replaceWallModels(GameObject gameObject, int modelId) {
		log.info("Inside replaceWallModels");

		runeliteObject = client.createRuneLiteObject();

		LocalPoint lp = gameObject.getLocalLocation();
		ModelData wallModel = client.loadModelData(modelId).cloneColors();
		switch (modelId) {
			case LOBBY_WINDOW_MODEL_ID:
				for (Map.Entry<Short, Short> colorMapping : LOBBY_WALL_RECOLOR_VALUES.entrySet()) {
					Short colorToChange = colorMapping.getKey();
					Short newColor = colorMapping.getValue();
					wallModel.recolor(colorToChange, newColor);
				}

			case BLUE_WINDOW_INACTIVE_MODEL_ID:
				for (Map.Entry<Short, Short> colorMapping : BLUE_GAUNTLET_WALL_RECOLOR_VALUES.entrySet()) {
					Short colorToChange = colorMapping.getKey();
					Short newColor = colorMapping.getValue();
					wallModel.recolor(colorToChange, newColor);
				}
			case BLUE_SPIKE_WALL_CENTRE_MODEL_ID:
				for (Map.Entry<Short, Short> colorMapping : BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES.entrySet()) {
					Short colorToChange = colorMapping.getKey();
					Short newColor = colorMapping.getValue();
					wallModel.recolor(colorToChange, newColor);
				}
			case BLUE_SPIKE_WALL_LEFT_MODEL_ID:
				for (Map.Entry<Short, Short> colorMapping : BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES.entrySet()) {
					Short colorToChange = colorMapping.getKey();
					Short newColor = colorMapping.getValue();
					wallModel.recolor(colorToChange, newColor);
				}
			case BLUE_SPIKE_WALL_RIGHT_MODEL_ID:
				for (Map.Entry<Short, Short> colorMapping : BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES.entrySet()) {
					Short colorToChange = colorMapping.getKey();
					Short newColor = colorMapping.getValue();
					wallModel.recolor(colorToChange, newColor);
				}
			case BLUE_SPIKE_WALL_CORNER_MODEL_ID:
				for (Map.Entry<Short, Short> colorMapping : BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES.entrySet()) {
					Short colorToChange = colorMapping.getKey();
					Short newColor = colorMapping.getValue();
					wallModel.recolor(colorToChange, newColor);
				}
			case BLUE_SPIKE_WALL_DOUBLE1_MODEL_ID:
				for (Map.Entry<Short, Short> colorMapping : BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES.entrySet()) {
					Short colorToChange = colorMapping.getKey();
					Short newColor = colorMapping.getValue();
					wallModel.recolor(colorToChange, newColor);
				}
			case BLUE_SPIKE_WALL_DOUBLE2_MODEL_ID:
				for (Map.Entry<Short, Short> colorMapping : BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES.entrySet()) {
					Short colorToChange = colorMapping.getKey();
					Short newColor = colorMapping.getValue();
					wallModel.recolor(colorToChange, newColor);
				}
				//TODO: Move this stuff into helper method
		}

//		if (modelId == BLUE_SPIKE_WALL_RIGHT_MODEL_ID) {
//			//========= Find colors loop - Model =========
//			if (colorSearch) {
//				System.out.println("Before finding model colors");
//				ColorDebugUtils.findColors(wallModel);
//				colorSearch = false;
//				System.out.println("After finding model colors");
//			}
//		}

		log.info("Model recolored");
		runeliteObject.setActive(false);
		runeliteObject.setModel(wallModel.light());
		runeliteObject.setOrientation(gameObject.getOrientation());
		runeliteObject.setLocation(lp, client.getPlane());
		runeliteObject.setActive(true);
	}

	private void replaceFaceColorValues(int[] faceColors1, int[] faceColors2, int[] faceColors3, int color) {
		for (int i = 0; i < faceColors1.length; i++) {
			if (colorsToChange.contains(faceColors1[i])) {
				faceColors1[i] = color;
			}
		}
		for (int i = 0; i < faceColors2.length; i++) {
			if (colorsToChange.contains(faceColors2[i])) {
				faceColors2[i] = color;
			}
		}
		for (int i = 0; i < faceColors3.length; i++) {
			if (colorsToChange.contains(faceColors3[i])) {
				faceColors3[i] = color;
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

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
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
}
