package com.gauntletrecolor;

import com.gauntletrecolor.util.ColorDebugUtils;
import com.gauntletrecolor.util.RecolorSelection;
import com.google.inject.Provides;
import javax.inject.Inject;
import javax.swing.*;

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

	private GameObject gameObj;
	private int[] defaultObjFaceColors1;
	private int[] defaultObjFaceColors2;
	private int[] defaultObjFaceColors3;

	private boolean hunllefRecolored;

	private boolean recoloringWindows = false;


	private boolean colorSearch = true;
	private Set<Integer> colorsToChange = new HashSet<>();

	private RecolorSelection currentColor = RecolorSelection.BLUE;

	private int BLUE_GAUNTLET_REGION = 7512;
	private int RED_GAUNTLET_REGION = 7768;
	private int GAUNTLET_LOBBY_REGION = 12127;

	@Override
	protected void startUp() throws Exception {
		log.info("Gauntlet Recolor started!");
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned event) {
		GameObject gameObj = event.getGameObject();
		if (BLUE_GAUNTLET_IDS.contains(gameObj.getId()) || LOBBY_GAME_OBJECTS.contains(gameObj.getId())
				|| BLUE_GAUNTLET_GAME_OBJECTS.contains(gameObj.getId())) {
			if (gameObj.getId() == LOBBY_WINDOW_OBJ_ID){
				log.info("@@@@@@@@@@@@@@@@@@");
				log.info("recoloring windows");
				recoloringWindows = true;
			}
			clientThread.invoke(() -> updateGameObjects(gameObj));
			log.info("@@@@@@@@@@@@@@@@@@");

		}
	}

	private void recolorObjModels(Model model, boolean isFloor, int[] hslArray) {
		final int[] faceColors1 = model.getFaceColors1();
		final int[] faceColors2 = model.getFaceColors2();
		final int[] faceColors3 = model.getFaceColors3();

		if (isFloor) {
			replaceFloorColorValues(faceColors1, faceColors2, faceColors3, FLOOR_COLOR_MAP.get(config.recolorSelection()));
		} else {
			replaceGameObjectColorValues(faceColors1, faceColors2, faceColors3, hslArray);
		}
	}

	private void replaceGameObjectColorValues(int[] faceColors1, int[] faceColors2, int[] faceColors3, int[] hslArray) {
		for (int i = 0; i < faceColors1.length; i++) {
			replaceColorInRange(i, faceColors1, hslArray);
		}
		for (int i = 0; i < faceColors2.length; i++) {
			replaceColorInRange(i, faceColors2, hslArray);
		}
		for (int i = 0; i < faceColors3.length; i++) {
			replaceColorInRange(i, faceColors3, hslArray);
		}
	}

	private void replaceColorInRange(int i, int[] colors, int[] hslArray) {
		Integer colorInt = colors[i];
		int hue = JagexColor.unpackHue(colorInt.shortValue());
		int sat = JagexColor.unpackSaturation(colorInt.shortValue());
		int lum = JagexColor.unpackLuminance(colorInt.shortValue());

		if ((hue >= hslArray[0] && hue <= hslArray[1])
				&& (sat >= hslArray[2] && sat <= hslArray[3])
				&& (lum >= hslArray[4] && lum <= hslArray[5])) {

			if (!config.recolorSelection().equals(currentColor)) {
				switch (config.recolorSelection()) {
					case BLACK:
						log.info("inside case black");
						colors[i] = JagexColor.packHSL(OBJECT_COLOR_MAP.get(RecolorSelection.BLACK),0,lum);
						break;
					case NAVY:
						int newSat = 6;
						colors[i] = JagexColor.packHSL(OBJECT_COLOR_MAP.get(RecolorSelection.NAVY),newSat,lum);
						if (recoloringWindows) {
							System.out.println("HSL COMBO BEFORE: H" + hue + " S" + sat + " L" + lum);
							System.out.println("HSL COMBO AFTER: H" + 38 + " S" + newSat + " L" + lum);
						}
						break;
					default:
						log.info("inside case default");
						int newColor = OBJECT_COLOR_MAP.get(config.recolorSelection());
						colors[i] = JagexColor.packHSL(newColor,sat,lum);
				}
//				if (config.recolorSelection().equals(RecolorSelection.BLACK)) {
//					colors[i] = JagexColor.packHSL(OBJECT_COLOR_MAP.get(config.recolorSelection()),0,lum);
//				} else {
//					colors[i] = JagexColor.packHSL(newColor,sat,lum);
//				}
			}
//			if (!config.recolorSelection().equals(currentColor)) {
//				int newColor = OBJECT_COLOR_MAP.get(config.recolorSelection());
//				colors[i] = JagexColor.packHSL(newColor,sat,lum);
//				//TODO need a translation for the sat/lum of each color
//				//e.g light blue to dark blue has same sat but needs half the lum
//
//			}
		}
	}


	@Subscribe
	public void onGroundObjectSpawned(GroundObjectSpawned event) {
		GroundObject groundObj = event.getGroundObject();
		if (BLUE_FLOOR_IDS.contains(groundObj.getId())) {
			clientThread.invoke(() -> updateGroundObjects(groundObj));
		}
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
			clientThread.invoke(() -> updateNPCObjects(npc));
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (!event.getKey().equals("colorSelection") || client.getGameState() != GameState.LOGGED_IN) {
			return;
		}

		// Recolor all GameObjects and GroundObjects in scene
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

		// Recolor NPCs
		List<NPC> NPCs = client.getNpcs();
		for (NPC npc : NPCs) {
			log.info(npc.getName());
			if (BLUE_GAUNTLET_NPCS.contains(npc.getId())) {
				log.info("inside npc config condition");
				clientThread.invoke(() -> updateNPCObjects(npc));
			}
		}

		System.out.println("You changed color to: " + config.recolorSelection().toString());
	}

	@Subscribe
	public void onGameTick(GameTick tick) {
		if (isInBlueGauntletRegion()) {
			if (!hunllefRecolored) { //TODO this still doesn't recolor hunllef on first entering
				// Recolor hunllef manually when entering gauntlet
				log.info("recoloring hunllef manually");
				List<NPC> NPCs = client.getNpcs();
				for (NPC npc : NPCs) {
					if (isHunllef(npc)) {
						clientThread.invoke(() -> updateNPCObjects(npc));
						hunllefRecolored = true;
					}
				}
			}
		}

		if (!isInBlueGauntletRegion()) {
			hunllefRecolored = false;
		}
	}

	private void updateGroundObjects(GroundObject groundObj) {
		final Model model = (groundObj.getRenderable().getModel() != null)
				? groundObj.getRenderable().getModel() : (Model) groundObj.getRenderable();

		clientThread.invoke(() -> recolorObjModels(model, true, null));

	}

	private void updateGameObjects(GameObject gameObj) {
		final Model model = (gameObj.getRenderable().getModel() != null)
				? gameObj.getRenderable().getModel() : (Model) gameObj.getRenderable();

		//TODO do we need the range values?
		clientThread.invoke(() -> recolorObjModels(model, false, LOBBY_FLOOR_HSL_RANGE_VALUES));
	}

	private void updateNPCObjects(NPC npc) {
		final Model model = (npc.getModel() != null)
				? npc.getModel() : (Model) npc;

		clientThread.invoke(() -> recolorObjModels(model, false, LOBBY_FLOOR_HSL_RANGE_VALUES));
	}

	private boolean isInBlueGauntletRegion() {
		if (client.getLocalPlayer() != null)
		{
			WorldPoint wp = WorldPoint.fromLocalInstance(client, client.getLocalPlayer().getLocalLocation());
			return wp.getRegionID() == BLUE_GAUNTLET_REGION;
		}
		return false;
	}

	private boolean isInGauntletLobbyRegion() {
		if (client.getLocalPlayer() != null)
		{
			return client.getLocalPlayer().getWorldLocation().getRegionID() == GAUNTLET_LOBBY_REGION;
		}
		return false;
	}

	//TODO remove this if its not used elsewhere
	private boolean isHunllef(NPC npc) {
		return npc.getId() == NpcID.CRYSTALLINE_HUNLLEF || npc.getId() == NpcID.CRYSTALLINE_HUNLLEF_9022
				|| npc.getId() == NpcID.CRYSTALLINE_HUNLLEF_9023 || npc.getId() == NpcID.CRYSTALLINE_HUNLLEF_9024;
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
