package com.gauntletrecolor;

import net.runelite.api.JagexColor;

import java.util.HashMap;
import java.util.Map;

public final class RecolorConstants {
    //Lobby
    static final int LOBBY_WINDOW_OBJ_ID = 36096;
    static final int LOBBY_WINDOW_MODEL_ID = 37370;
    static final int CORNER_PILLAR_OBJ_ID = 36100;
    static final int CORNER_PILLAR_MODEL_ID = 37379;
    static final int LOBBY_FLOOR_OBJ_ID = 36100;

    //Blue Gauntlet
    static final Map<Integer, Integer> BLUE_GAUNTLET_ID_MAP = new HashMap();

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

    static {
        BLUE_GAUNTLET_ID_MAP.put(LOBBY_WINDOW_OBJ_ID, LOBBY_WINDOW_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(CORNER_PILLAR_OBJ_ID, CORNER_PILLAR_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_WINDOW_OBJ_ID, BLUE_WINDOW_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_WINDOW_INACTIVE_OBJ_ID, BLUE_WINDOW_INACTIVE_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_SPIKE_WALL_CENTRE_OBJ_ID, BLUE_SPIKE_WALL_CENTRE_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_SPIKE_WALL_RIGHT_OBJ_ID, BLUE_SPIKE_WALL_RIGHT_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_SPIKE_WALL_LEFT_OBJ_ID, BLUE_SPIKE_WALL_LEFT_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_SPIKE_WALL_CORNER_OBJ_ID, BLUE_SPIKE_WALL_CORNER_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_SPIKE_WALL_DOUBLE1_OBJ_ID, BLUE_SPIKE_WALL_DOUBLE1_MODEL_ID);
        BLUE_GAUNTLET_ID_MAP.put(BLUE_SPIKE_WALL_DOUBLE2_OBJ_ID, BLUE_SPIKE_WALL_DOUBLE2_MODEL_ID);
    }

    //Recolor codes
    static short innerWindow = -31862;
    static short innerWindowTrim = -31784;
    static short windowBg1 = -31868;
    static short windowBg2 = -32506;
    static short overflow1 = -32630;
    static short overflow2 = -32626;

    static short innerWindowNew = 7315;
    static short innerWindowTrimNew = 10840;
    static short windowBg1New = 9990;
    static short windowBg2New = 9992;
    static short overflow1New = 9354;
    static short overflow2New = 9358;

    static final Map<Short, Short> LOBBY_WALL_RECOLOR_VALUES = new HashMap();
    static {
        LOBBY_WALL_RECOLOR_VALUES.put(innerWindow, innerWindowNew);
        LOBBY_WALL_RECOLOR_VALUES.put(innerWindowTrim, innerWindowTrimNew);
        LOBBY_WALL_RECOLOR_VALUES.put(windowBg1, windowBg1New);
        LOBBY_WALL_RECOLOR_VALUES.put(windowBg2, windowBg2New);
        LOBBY_WALL_RECOLOR_VALUES.put(overflow1, overflow1New);
        LOBBY_WALL_RECOLOR_VALUES.put(overflow2, overflow2New);
    }

    static short inactiveWindowBg = -31870;
    static short inactiveWindowBgNew = 10114;
    static short inactiveWindowTrim = -31866;
    static short inactiveWindowTrimNew = 10118;

    static final Map<Short, Short> BLUE_GAUNTLET_WALL_RECOLOR_VALUES = LOBBY_WALL_RECOLOR_VALUES;
    static {
        LOBBY_WALL_RECOLOR_VALUES.put(inactiveWindowBg, inactiveWindowBgNew);
        LOBBY_WALL_RECOLOR_VALUES.put(inactiveWindowTrim, inactiveWindowTrimNew);
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

}
