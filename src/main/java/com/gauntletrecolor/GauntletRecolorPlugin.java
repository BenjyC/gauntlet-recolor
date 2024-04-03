package com.gauntletrecolor;

import com.gauntletrecolor.util.ColorDebugUtils;
import com.gauntletrecolor.util.RecolorSelection;
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
import net.runelite.client.events.ConfigChanged;
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

	private GameObject gameObj;
	private int[] defaultObjFaceColors1;
	private int[] defaultObjFaceColors2;
	private int[] defaultObjFaceColors3;

	private boolean isStartup;

	private boolean colorSearch = true;
	private Set<Integer> colorsToChange = new HashSet<>();

	@Override
	protected void startUp() throws Exception {
		log.info("Gauntlet Recolor started!");
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned event) {
		GameObject gameObj = event.getGameObject();
		final Model model = gameObj.getRenderable().getModel();
		if (BLUE_GAUNTLET_IDS.contains(gameObj.getId()) || LOBBY_GAME_OBJECTS.contains(gameObj.getId())
				|| BLUE_GAUNTLET_GAME_OBJECTS.contains(gameObj.getId())) {
			clientThread.invoke(() -> updateGameObjects(gameObj));
		}
	}

	private void recolorGameObjModels(Model model, int[] hslArray) {
		final int[] faceColors1 = model.getFaceColors1();
		final int[] faceColors2 = model.getFaceColors2();
		final int[] faceColors3 = model.getFaceColors3();

		changeGameObjectColors(faceColors1, hslArray);
		changeGameObjectColors(faceColors2, hslArray);
		changeGameObjectColors(faceColors3, hslArray);
	}

	private void changeGameObjectColors(int[] colors, int[] hslArray) {
		for (int i = 0; i < colors.length; i++) {
			Integer colorInt = colors[i];
			int hue = JagexColor.unpackHue(colorInt.shortValue());
			int sat = JagexColor.unpackSaturation(colorInt.shortValue());
			int lum = JagexColor.unpackLuminance(colorInt.shortValue());

			if ((hue >= hslArray[0] && hue <= hslArray[1])
					&& (sat >= hslArray[2] && sat <= hslArray[3])
					&& (lum >= hslArray[4] && lum <= hslArray[5])) {

				if (!config.recolorSelection().equals(RecolorSelection.BLUE)) {
					int newColor = OBJECT_COLOR_MAP.get(config.recolorSelection());
					colors[i] = JagexColor.packHSL(newColor,sat,lum);
				}
			}
		}
	}

	@Subscribe
	public void onGroundObjectSpawned(GroundObjectSpawned event) {
		GroundObject groundObj = event.getGroundObject();
		if (BLUE_FLOOR_IDS.contains(groundObj.getId())) {
			clientThread.invoke(() -> updateGroundObjects(groundObj));
		}
	}

	private void recolorGroundObjModels(Model model, int[] hslArray) {
		final int[] faceColors1 = model.getFaceColors1();
		final int[] faceColors2 = model.getFaceColors2();
		final int[] faceColors3 = model.getFaceColors3();

		replaceFloorColorValues(faceColors1, faceColors2, faceColors3, FLOOR_COLOR_MAP.get(config.recolorSelection()));
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

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (!event.getKey().equals("colorSelection") || client.getGameState() != GameState.LOGGED_IN) {
			return;
		}

		Scene scene = client.getScene();
		Tile[][] tiles = scene.getTiles()[client.getPlane()];

		for (int x = 0; x < Constants.SCENE_SIZE; x++) {
			for (int y = 0; y < Constants.SCENE_SIZE; y++) {
				Tile tile = tiles[x][y];
				if (tile != null) {
					for (GameObject gameObj : tile.getGameObjects()) {
						if (gameObj != null) {
							if (BLUE_GAUNTLET_IDS.contains(gameObj.getId()) || LOBBY_GAME_OBJECTS.contains(gameObj.getId())
									|| BLUE_GAUNTLET_GAME_OBJECTS.contains(gameObj.getId())) {
								clientThread.invoke(() -> updateGameObjects(gameObj));
							}
						}
					}
					if (tile.getGroundObject() != null && BLUE_FLOOR_IDS.contains(tile.getGroundObject().getId())) {
						clientThread.invoke(() -> updateGroundObjects(tile.getGroundObject()));
					}
				}
			}
		}

		System.out.println("You changed color to: " + config.recolorSelection().toString());
	}

	private void updateGroundObjects(GroundObject groundObj) {
		final Model model = (groundObj.getRenderable().getModel() != null)
				? groundObj.getRenderable().getModel() : (Model) groundObj.getRenderable();

		clientThread.invoke(() -> recolorGroundObjModels(model, LOBBY_FLOOR_HSL_RANGE_VALUES));

	}
	private void updateGameObjects(GameObject gameObj) {
		final Model model = (gameObj.getRenderable().getModel() != null)
				? gameObj.getRenderable().getModel() : (Model) gameObj.getRenderable();

		clientThread.invoke(() -> recolorGameObjModels(model, LOBBY_FLOOR_HSL_RANGE_VALUES));
	}

	@Subscribe
	void onNpcSpawned(final NpcSpawned event) {
		final NPC npc = event.getNpc();

		if (BLUE_GAUNTLET_NPCS.contains(npc.getId())) {
			final Model model = npc.getModel();
			if (model == null) {
				Model newModel = (Model) npc;
				clientThread.invoke(() -> recolorGameObjModels(newModel, LOBBY_GAME_OBJ_HSL_RANGE_VALUES));
			} else {
				clientThread.invoke(() -> recolorGameObjModels(model, LOBBY_GAME_OBJ_HSL_RANGE_VALUES));
			}
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged) {
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN){

		}

	}

	@Provides
	GauntletRecolorConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(GauntletRecolorConfig.class);
	}

	@Override
	protected void shutDown() throws Exception {
		log.info("Gauntlet Recolor stopped!");
	}
}
