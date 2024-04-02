package com.gauntletrecolor;

import net.runelite.api.JagexColor;

import java.util.*;

public final class RecolorConstants {
    // ==== Lobby ====
    // Object/Model Ids
    static final int LOBBY_WINDOW_OBJ_ID = 36096;
    static final int LOBBY_WINDOW_MODEL_ID = 37370;
    static final int CORNER_PILLAR_OBJ_ID = 36100;
    static final int CORNER_PILLAR_MODEL_ID = 37379;
    static final int BEHIND_WINDOW_LEFT_OBJ_ID = 36099;
    static final int BEHIND_WINDOW_LEFT_MODEL_ID = 37384;
    static final int BEHIND_WINDOW_RIGHT_OBJ_ID = 36098;
    static final int BEHIND_WINDOW_RIGHT_MODEL_ID = 37369;
    static final int LOBBY_FLOOR_OBJ_ID = 36152;

    //Gameobjects
    static final int SCOREBOARD_OBJ_ID = 36060;
    static final int REWARD_CHEST_OBJ_ID = 37341;
    static final int CHANNEL_TELEPORT_CHEST_OBJ_ID = 36082;
    static final int ENTRY_TELEPORT_CHEST_OBJ_ID = 37340; //wrong plane
    static final int DEPOSIT_BOX_OBJ_ID = 36086;
    static final int RECIPE_BOOK_OBJ_ID = 36075;
    static final int POTIONS_BOOK_OBJ_ID = 36076;

    static final Set<Integer> LOBBY_GAME_MODELS = new HashSet<Integer>();
    static {
        LOBBY_GAME_MODELS.add(SCOREBOARD_OBJ_ID);
        LOBBY_GAME_MODELS.add(REWARD_CHEST_OBJ_ID);
        LOBBY_GAME_MODELS.add(CHANNEL_TELEPORT_CHEST_OBJ_ID);
        LOBBY_GAME_MODELS.add(DEPOSIT_BOX_OBJ_ID);
        LOBBY_GAME_MODELS.add(RECIPE_BOOK_OBJ_ID);
        LOBBY_GAME_MODELS.add(POTIONS_BOOK_OBJ_ID);
    }

    //static final int[] SCOREBOARD_GAME_OBJ_COLORS = ; TODO save values ahead of time

    // ==== Blue Gauntlet ====

    // Object/Model Ids
    static final int BLUE_FLOOR_OBJ_ID = 36149;
    static final Set<Integer> BLUE_FLOOR_IDS = new HashSet();
    static {
        BLUE_FLOOR_IDS.add(BLUE_FLOOR_OBJ_ID);
        BLUE_FLOOR_IDS.add(LOBBY_FLOOR_OBJ_ID);
        BLUE_FLOOR_IDS.add(36155);
        BLUE_FLOOR_IDS.add(36156);
        BLUE_FLOOR_IDS.add(36157);
        BLUE_FLOOR_IDS.add(36158);
        BLUE_FLOOR_IDS.add(36159);
        BLUE_FLOOR_IDS.add(36160);
        BLUE_FLOOR_IDS.add(36161);
        BLUE_FLOOR_IDS.add(36162);
    }

    static final int BLUE_WINDOW_OBJ_ID = 36095;
    static final int BLUE_WINDOW_MODEL_ID = 37370;
    static final int BLUE_WINDOW_INACTIVE_OBJ_ID = 36097;
    static final int BLUE_WINDOW_INACTIVE_MODEL_ID = 37372;

    static final int BLUE_SPIKE_WALL_CENTRE_OBJ_ID = 36106;
    static final int BLUE_SPIKE_WALL_CENTRE_MODEL_ID = 37439;
    static final int BLUE_SPIKE_WALL_RIGHT_OBJ_ID = 36109;
    static final int BLUE_SPIKE_WALL_RIGHT_MODEL_ID = 37440;
    static final int BLUE_SPIKE_WALL_LEFT_OBJ_ID = 36110;
    static final int BLUE_SPIKE_WALL_LEFT_MODEL_ID = 37441;
    static final int BLUE_SPIKE_WALL_CORNER_OBJ_ID = 36111;
    static final int BLUE_SPIKE_WALL_CORNER_MODEL_ID = 37436;
    static final int BLUE_SPIKE_WALL_DOUBLE1_OBJ_ID = 36107;
    static final int BLUE_SPIKE_WALL_DOUBLE1_MODEL_ID = 37437;
    static final int BLUE_SPIKE_WALL_DOUBLE2_OBJ_ID = 36108;
    static final int BLUE_SPIKE_WALL_DOUBLE2_MODEL_ID = 37438;

    static final Map<Integer, Integer> BLUE_GAUNTLET_ID_MAP = new HashMap();
    static {
        BLUE_GAUNTLET_ID_MAP.put(LOBBY_WINDOW_OBJ_ID, LOBBY_WINDOW_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(CORNER_PILLAR_OBJ_ID, CORNER_PILLAR_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BEHIND_WINDOW_LEFT_OBJ_ID, BEHIND_WINDOW_LEFT_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BEHIND_WINDOW_RIGHT_OBJ_ID, BEHIND_WINDOW_RIGHT_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_WINDOW_OBJ_ID, BLUE_WINDOW_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_WINDOW_INACTIVE_OBJ_ID, BLUE_WINDOW_INACTIVE_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_SPIKE_WALL_CENTRE_OBJ_ID, BLUE_SPIKE_WALL_CENTRE_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_SPIKE_WALL_RIGHT_OBJ_ID, BLUE_SPIKE_WALL_RIGHT_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_SPIKE_WALL_LEFT_OBJ_ID, BLUE_SPIKE_WALL_LEFT_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_SPIKE_WALL_CORNER_OBJ_ID, BLUE_SPIKE_WALL_CORNER_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_SPIKE_WALL_DOUBLE1_OBJ_ID, BLUE_SPIKE_WALL_DOUBLE1_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_SPIKE_WALL_DOUBLE2_OBJ_ID, BLUE_SPIKE_WALL_DOUBLE2_MODEL_ID);
    }


    // Gameobjects
    static final int BLUE_WATER_PUMP_OBJ_ID = 36078;
    static final int BLUE_RANGE_OBJ_ID = 36077;
    static final int BLUE_SINGING_BOWL_OBJ_ID = 36063;
    static final int BLUE_TOOL_STORAGE_OBJ_ID = 36074;
    static final int BLUE_CHANNEL_TELEPORT_OBJ_ID = 36062;
    static final int BLUE_DOOR_NODE_RIGHT_OBJ_ID = 36101;
    static final int BLUE_DOOR_NODE_LEFT_OBJ_ID = 36102;
    static final int BLUE_DOOR_NODE_RIGHT_LIT_OBJ_ID = 36103;
    static final int BLUE_DOOR_NODE_LEFT_LIT_OBJ_ID = 36104;
    static final int BLUE_HUNLLEF_BARRIER_OBJ_ID = 37339;
    static final int BLUE_PHREN_ROOTS_OBJ_ID = 36066;
    static final int BLUE_LINUM_OBJ_ID = 36072;
    static final int BLUE_ROCKS_OBJ_ID = 36064;
    static final int BLUE_GRYM_OBJ_ID = 36070;

    static final Set<Integer> BLUE_GAUNTLET_GAME_MODELS = new HashSet<Integer>();
    static {
        BLUE_GAUNTLET_GAME_MODELS.add(BLUE_WATER_PUMP_OBJ_ID);
        BLUE_GAUNTLET_GAME_MODELS.add(BLUE_RANGE_OBJ_ID);
        BLUE_GAUNTLET_GAME_MODELS.add(BLUE_SINGING_BOWL_OBJ_ID);
        BLUE_GAUNTLET_GAME_MODELS.add(BLUE_TOOL_STORAGE_OBJ_ID);
        BLUE_GAUNTLET_GAME_MODELS.add(BLUE_CHANNEL_TELEPORT_OBJ_ID);
        BLUE_GAUNTLET_GAME_MODELS.add(BLUE_DOOR_NODE_RIGHT_OBJ_ID);
        BLUE_GAUNTLET_GAME_MODELS.add(BLUE_DOOR_NODE_LEFT_OBJ_ID);
        BLUE_GAUNTLET_GAME_MODELS.add(BLUE_DOOR_NODE_RIGHT_LIT_OBJ_ID);
        BLUE_GAUNTLET_GAME_MODELS.add(BLUE_DOOR_NODE_LEFT_LIT_OBJ_ID);
        BLUE_GAUNTLET_GAME_MODELS.add(BLUE_HUNLLEF_BARRIER_OBJ_ID);
        BLUE_GAUNTLET_GAME_MODELS.add(BLUE_PHREN_ROOTS_OBJ_ID);
        BLUE_GAUNTLET_GAME_MODELS.add(BLUE_LINUM_OBJ_ID);
        BLUE_GAUNTLET_GAME_MODELS.add(BLUE_ROCKS_OBJ_ID);
        BLUE_GAUNTLET_GAME_MODELS.add(BLUE_GRYM_OBJ_ID);
    }










    ///////////////////////////////////


    //Recolor codes
    static final int[] LOBBY_GAME_OBJ_HSL_RANGE_VALUES = {26,45,1,8,1,140};
    static final int[] LOBBY_FLOOR_HSL_RANGE_VALUES = {1,40,1,9,1,150};

    //Lobby
    static short innerWindow = -31862;
    static short innerWindowTrim = -31784;
    static short windowBg1 = -31868;
    static short windowBg2 = -32506;
    static short overflow1 = -32630;
    static short overflow2 = -32626;
    static short cornerPillarRing = JagexColor.packHSL(32,2,16);
    static short cornerPillarMiddle = JagexColor.packHSL(33,7,37);

    //Yellow Hue=9
    static short innerWindowNew = 7315;
    static short innerWindowTrimNew = 10840;
    static short windowBg1New = 9990;
    static short windowBg2New = 9992;
    static short overflow1New = 9354;
    static short overflow2New = 9358;
    static short cornerPillarRingNew = JagexColor.packHSL(9,2,16);
    static short cornerPillarMiddleNew = JagexColor.packHSL(9,7,37); //TODO

    static final Map<Short, Short> LOBBY_WALL_RECOLOR_VALUES = new HashMap();
    static {
        LOBBY_WALL_RECOLOR_VALUES.put(innerWindow, innerWindowNew);
        LOBBY_WALL_RECOLOR_VALUES.put(innerWindowTrim, innerWindowTrimNew);
        LOBBY_WALL_RECOLOR_VALUES.put(windowBg1, windowBg1New);
        LOBBY_WALL_RECOLOR_VALUES.put(windowBg2, windowBg2New);
        LOBBY_WALL_RECOLOR_VALUES.put(overflow1, overflow1New);
        LOBBY_WALL_RECOLOR_VALUES.put(overflow2, overflow2New);
        LOBBY_WALL_RECOLOR_VALUES.put(cornerPillarRing, cornerPillarRingNew);
        LOBBY_WALL_RECOLOR_VALUES.put(cornerPillarMiddle, cornerPillarMiddleNew);
    }

    static short inactiveWindowBg = -31870;
    static short inactiveWindowBgNew = 10114;
    static short inactiveWindowTrim = -31866;
    static short inactiveWindowTrimNew = 10118;

    static final Map<Short, Short> BLUE_GAUNTLET_WALL_RECOLOR_VALUES = LOBBY_WALL_RECOLOR_VALUES;
    static {
        BLUE_GAUNTLET_WALL_RECOLOR_VALUES.put(inactiveWindowBg, inactiveWindowBgNew);
        BLUE_GAUNTLET_WALL_RECOLOR_VALUES.put(inactiveWindowTrim, inactiveWindowTrimNew);
    }

    static short centreSpikeColor1 = -32630; //JagexColor.packHSL(32,1,10);
    static short centreSpikeColor2 = -32626; //JagexColor.packHSL(32,1,14);
    static short centreSpikeColor3 = -32506; //JagexColor.packHSL(32,2,6);
    static short centreSpikeColor1New = 9354; //JagexColor.packHSL(9,1,10);
    static short centreSpikeColor2New = 9358; //JagexColor.packHSL(9,1,14);
    static short centreSpikeColor3New = 9478; //JagexColor.packHSL(9,2,6);
    static final Map<Short, Short> BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES = new HashMap();
    static {
        BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES.put(centreSpikeColor1, centreSpikeColor1New);
        BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES.put(centreSpikeColor2, centreSpikeColor2New);
        BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES.put(centreSpikeColor3, centreSpikeColor3New);
    }

    // Mapping model ID to which recolor map it should use
    static final Map<Integer, Map> MODEL_RECOLOR_MAPPING = new HashMap();
    static {
        MODEL_RECOLOR_MAPPING.put(LOBBY_WINDOW_MODEL_ID, LOBBY_WALL_RECOLOR_VALUES);
        MODEL_RECOLOR_MAPPING.put(CORNER_PILLAR_MODEL_ID, LOBBY_WALL_RECOLOR_VALUES);
        MODEL_RECOLOR_MAPPING.put(BEHIND_WINDOW_LEFT_MODEL_ID, LOBBY_WALL_RECOLOR_VALUES);
        MODEL_RECOLOR_MAPPING.put(BEHIND_WINDOW_RIGHT_MODEL_ID, LOBBY_WALL_RECOLOR_VALUES);
        MODEL_RECOLOR_MAPPING.put(BLUE_WINDOW_INACTIVE_MODEL_ID, BLUE_GAUNTLET_WALL_RECOLOR_VALUES);
        MODEL_RECOLOR_MAPPING.put(BLUE_SPIKE_WALL_CENTRE_MODEL_ID, BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES);
        MODEL_RECOLOR_MAPPING.put(BLUE_SPIKE_WALL_LEFT_MODEL_ID, BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES);
        MODEL_RECOLOR_MAPPING.put(BLUE_SPIKE_WALL_RIGHT_MODEL_ID, BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES);
        MODEL_RECOLOR_MAPPING.put(BLUE_SPIKE_WALL_CORNER_MODEL_ID, BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES);
        MODEL_RECOLOR_MAPPING.put(BLUE_SPIKE_WALL_DOUBLE1_MODEL_ID, BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES);
        MODEL_RECOLOR_MAPPING.put(BLUE_SPIKE_WALL_DOUBLE2_MODEL_ID, BLUE_GAUNTLET_SPIKE_RECOLOR_VALUES);




    }


}
