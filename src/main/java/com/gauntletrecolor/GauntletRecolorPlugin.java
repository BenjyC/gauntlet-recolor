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

	private GameObject gameObj;
	private int[] defaultObjFaceColors1;
	private int[] defaultObjFaceColors2;
	private int[] defaultObjFaceColors3;
	private RuneLiteObject runeliteObject;
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
		if (BLUE_GAUNTLET_IDS.contains(gameObj.getId())
			|| LOBBY_GAME_OBJECTS.contains(gameObj.getId())
			|| BLUE_GAUNTLET_GAME_OBJECTS.contains(gameObj.getId())) {

			if (model == null) {
				Model newModel = (Model) gameObj.getRenderable();
				clientThread.invoke(() -> replaceGameObjModels(newModel, LOBBY_GAME_OBJ_HSL_RANGE_VALUES));
			} else {
				clientThread.invoke(() -> replaceGameObjModels(model, LOBBY_GAME_OBJ_HSL_RANGE_VALUES));
			}
		}
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
	public void onGroundObjectSpawned(GroundObjectSpawned event) {
		GroundObject groundObj = event.getGroundObject();
		if (BLUE_FLOOR_IDS.contains(groundObj.getId())) {
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

		//todo save floor color to var
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

	@Subscribe
	void onNpcSpawned(final NpcSpawned event) {
		final NPC npc = event.getNpc();

		if (BLUE_GAUNTLET_NPCS.contains(npc.getId())) {
			final Model model = npc.getModel();
			if (model == null) {
				Model newModel = (Model) npc;
				clientThread.invoke(() -> replaceGameObjModels(newModel, LOBBY_GAME_OBJ_HSL_RANGE_VALUES));
			} else {
				clientThread.invoke(() -> replaceGameObjModels(model, LOBBY_GAME_OBJ_HSL_RANGE_VALUES));
			}
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged) {
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
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
