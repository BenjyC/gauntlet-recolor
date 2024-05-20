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

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileInputStream;
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

	private String END_OF_FILE = "END_OF_FILE";

	private boolean hunllefRecolored;

	private boolean shutdown = false;

	private boolean colorSearch = true;

	private RecolorSelection currentColor = RecolorSelection.BLUE;


	private int BLUE_GAUNTLET_REGION = 7512;
	private int RED_GAUNTLET_REGION = 7768;
	private int GAUNTLET_LOBBY_REGION = 12127;

	@Override
	protected void startUp() throws Exception {
		log.info("Gauntlet Recolor started!");
		shutdown = false;
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned event) {
		GameObject gameObj = event.getGameObject();
		if (BLUE_GAUNTLET_IDS.contains(gameObj.getId()) || LOBBY_GAME_OBJECTS.contains(gameObj.getId())
				|| BLUE_GAUNTLET_GAME_OBJECTS.contains(gameObj.getId())) {
			clientThread.invoke(() -> {
				try {
					updateGameObjects(gameObj);
				} catch (IOException e) {
					throw new RuntimeException(e); //TODO remove this try/catch
				}
			});
		}
	}

	private void recolorObjModels(Model model, boolean isFloor, int[] hslArray, int id) throws IOException {
		int[] faceColors1 = model.getFaceColors1();
		int[] faceColors2 = model.getFaceColors2();
		int[] faceColors3 = model.getFaceColors3();

		if (shutdown) {
			if (isInGauntletLobbyRegion()) {
				if (DEFAULT_BLUE_LOBBY_COLOR_ARRAYS_MAP.isEmpty()) {
					readColorArraysFromFile(DEFAULT_BLUE_LOBBY_COLOR_ARRAYS_MAP);
				}
				replaceColorArrays(faceColors1, faceColors2, faceColors3, id, DEFAULT_BLUE_LOBBY_COLOR_ARRAYS_MAP);


//			} else if (isInBlueGauntletRegion()) {
				//TODO fill this map
//				if (DEFAULT_BLUE_GAUNTLET_COLOR_ARRAYS_MAP.isEmpty()) {
//					readColorArraysFromFile(DEFAULT_BLUE_GAUNTLET_COLOR_ARRAYS_MAP);
//				}
//				replaceColorArrays(faceColors1, faceColors2, faceColors3, id, DEFAULT_BLUE_GAUNTLET_COLOR_ARRAYS_MAP);
//			} else {
				//TODO red gauntlet
//				if (DEFAULT_BLUE_COLOR_ARRAYS_MAP.isEmpty()) {
//					readColorArraysFromFile(DEFAULT_BLUE_COLOR_ARRAYS_MAP);
//				}
//				List<int[]> colors = DEFAULT_BLUE_COLOR_ARRAYS_MAP.get(id);
//				System.arraycopy(colors.get(0), 0, faceColors1, 0, faceColors1.length);
//				System.arraycopy(colors.get(1), 0, faceColors2, 0, faceColors2.length);
//				System.arraycopy(colors.get(2), 0, faceColors3, 0, faceColors3.length);
			}

		}
		else if (isFloor) {
			replaceFloorColorValues(faceColors1, faceColors2, faceColors3, FLOOR_COLOR_MAP.get(config.recolorSelection()));
		} else {
			replaceGameObjectColorValues(faceColors1, faceColors2, faceColors3, hslArray);
		}
	}

	private void replaceColorArrays(int[] fc1, int[] fc2, int[] fc3, int id, Map<Integer,List<int[]>> map) {
		List<int[]> colors = map.get(id);
		System.arraycopy(colors.get(0), 0, fc1, 0, fc1.length);
		System.arraycopy(colors.get(1), 0, fc2, 0, fc2.length);
		System.arraycopy(colors.get(2), 0, fc3, 0, fc3.length);
	}

	private void readColorArraysFromFile(Map<Integer,List<int[]>> map) {
		log.info("Reading from file...");

//		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
//			stream.forEach(System.out::println);
//		}
		try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/gauntletrecolor/colors/defaultcolors.txt"))) {
			String line;

			List<int[]> arr = new ArrayList<>();
			int objId = 0;
			int counter = 0;

			while ((line = br.readLine()) != null) {
				String key = line.split("=")[0];
				if (counter == 3) {
					//Once we hit 3 lines, push array to map and reset
					map.put(objId, arr);
					arr = new ArrayList<>();
					counter = 0;
				}
				if (!key.equals(END_OF_FILE)) {
					objId = Integer.parseInt(key.substring(0, 5));
					String currArr = line.split("=")[1];
					int[] currColors = Arrays.stream(currArr.substring(1, currArr.length() - 1).split(","))
							.map(String::trim).mapToInt(Integer::parseInt).toArray();
					arr.add(currColors);
					counter++;
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		log.info("Map populated.");
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

//			if (!config.recolorSelection().equals(currentColor)) {
//				switch (config.recolorSelection()) {
//					case BLACK:
//						log.info("inside case black");
//						colors[i] = JagexColor.packHSL(OBJECT_COLOR_MAP.get(RecolorSelection.BLACK),0,lum);
//						break;
//					case NAVY:
//						int newSat = sat;
//						if (sat <= 3) {
//							 newSat = sat * 2;
//						}
//						int newLum = lum;
//						if (lum <= 50) {
//							newLum = lum / 2;
//						}
//						colors[i] = JagexColor.packHSL(OBJECT_COLOR_MAP.get(RecolorSelection.NAVY),newSat,newLum);
//						if (recoloringWindows) {
//							System.out.println("HSL COMBO BEFORE: H" + hue + " S" + sat + " L" + lum);
//							System.out.println("HSL COMBO AFTER: H" + 38 + " S" + newSat + " L" + lum);
//						}
//						break;
//					default:
//						//log.info("inside case default");
//						int newColor = OBJECT_COLOR_MAP.get(config.recolorSelection());
//						colors[i] = JagexColor.packHSL(newColor,sat,lum);
//				}
//				if (config.recolorSelection().equals(RecolorSelection.BLACK)) {
//					colors[i] = JagexColor.packHSL(OBJECT_COLOR_MAP.get(config.recolorSelection()),0,lum);
//				} else {
//					colors[i] = JagexColor.packHSL(newColor,sat,lum);
//				}
//			} //TODO need a translation for the sat/lum of each color

			if (!config.recolorSelection().equals(currentColor)) {
				int newColor = OBJECT_COLOR_MAP.get(config.recolorSelection());
				colors[i] = JagexColor.packHSL(newColor,sat,lum);
			}
		}
	}


	@Subscribe
	public void onGroundObjectSpawned(GroundObjectSpawned event) {
		GroundObject groundObj = event.getGroundObject();
		if (BLUE_FLOOR_IDS.contains(groundObj.getId())) {
			clientThread.invoke(() -> {
				try {
					updateGroundObjects(groundObj);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
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
	public void onConfigChanged(ConfigChanged event) {
		if (!event.getKey().equals("colorSelection") || client.getGameState() != GameState.LOGGED_IN) {
			return;
		}

		recolorObjectsInScene();

		// Recolor NPCs
		List<NPC> NPCs = client.getNpcs();
		for (NPC npc : NPCs) {
			log.info(npc.getName());
			if (BLUE_GAUNTLET_NPCS.contains(npc.getId())) {
				log.info("inside npc config condition");
				clientThread.invoke(() -> updateNPCObjects(npc));
			}
		}

		log.info("You changed color to: " + config.recolorSelection().toString());
	}

	private void recolorObjectsInScene() {
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
								clientThread.invoke(() -> {
									try {
										updateGameObjects(gameObj);
									} catch (IOException e) {
										throw new RuntimeException(e); //TODO remove this try/catch
									}
								});
							}
						}
					}
					if (tile.getGroundObject() != null && BLUE_FLOOR_IDS.contains(tile.getGroundObject().getId())) {
						clientThread.invoke(() -> {
							try {
								updateGroundObjects(tile.getGroundObject());
							} catch (IOException e) {
								throw new RuntimeException(e);
							}
						});
					}
				}
			}
		}
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

		if (isInRedGauntletRegion()) {
			log.info("red gauntlet");
		}

		if (!isInBlueGauntletRegion()) {
			hunllefRecolored = false;
		}


	}

	private void updateGroundObjects(GroundObject groundObj) throws IOException {
		final Model model = (groundObj.getRenderable().getModel() != null)
				? groundObj.getRenderable().getModel() : (Model) groundObj.getRenderable();

		clientThread.invoke(() -> {
			try {
				recolorObjModels(model, true, null, groundObj.getId());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private void updateGameObjects(GameObject gameObj) throws IOException {
		final Model model = (gameObj.getRenderable().getModel() != null)
				? gameObj.getRenderable().getModel() : (Model) gameObj.getRenderable();

		//TODO do we need the range values?
		clientThread.invoke(() -> {
			try {
				recolorObjModels(model, false, LOBBY_FLOOR_HSL_RANGE_VALUES, gameObj.getId());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

	}

	private void updateNPCObjects(NPC npc) {
		final Model model = (npc.getModel() != null)
				? npc.getModel() : (Model) npc;

		clientThread.invoke(() -> {
			try {
				recolorObjModels(model, false, LOBBY_FLOOR_HSL_RANGE_VALUES, npc.getId());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private boolean isInBlueGauntletRegion() {
		if (client.getLocalPlayer() != null)
		{
			WorldPoint wp = WorldPoint.fromLocalInstance(client, client.getLocalPlayer().getLocalLocation());
			return wp.getRegionID() == BLUE_GAUNTLET_REGION;
		}
		return false;
	}

	private boolean isInRedGauntletRegion() {
		if (client.getLocalPlayer() != null)
		{
			WorldPoint wp = WorldPoint.fromLocalInstance(client, client.getLocalPlayer().getLocalLocation());
			return wp.getRegionID() == RED_GAUNTLET_REGION;
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

		shutdown = true;
		recolorObjectsInScene();

	}
}
