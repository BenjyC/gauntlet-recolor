package com.gauntletrecolor;

import net.runelite.api.JagexColor;

import java.util.*;

public final class RecolorConstants {

    // ==== Lobby ====

    // Object IDs
    static final int LOBBY_WINDOW_OBJ_ID = 36096;
    static final int CORNER_PILLAR_OBJ_ID = 36100;
    static final int BEHIND_WINDOW_LEFT_OBJ_ID = 36099;
    static final int BEHIND_WINDOW_RIGHT_OBJ_ID = 36098;
    static final int LOBBY_FLOOR_OBJ_ID = 36152;

    // Gameobjects
    static final int SCOREBOARD_OBJ_ID = 36060;
    static final int REWARD_CHEST_OBJ_ID = 37341;
    static final int CHANNEL_TELEPORT_OBJ_ID = 36082;
    static final int ENTRY_TELEPORT_OBJ_ID = 37340; //wrong plane
    static final int DEPOSIT_BOX_OBJ_ID = 36086;
    static final int RECIPE_BOOK_OBJ_ID = 36075;
    static final int POTIONS_BOOK_OBJ_ID = 36076;

    static final Set<Integer> LOBBY_GAME_OBJECTS = new HashSet<Integer>();
    static {
        LOBBY_GAME_OBJECTS.add(SCOREBOARD_OBJ_ID);
        LOBBY_GAME_OBJECTS.add(REWARD_CHEST_OBJ_ID);
        LOBBY_GAME_OBJECTS.add(CHANNEL_TELEPORT_OBJ_ID);
        LOBBY_GAME_OBJECTS.add(ENTRY_TELEPORT_OBJ_ID);
        LOBBY_GAME_OBJECTS.add(DEPOSIT_BOX_OBJ_ID);
        LOBBY_GAME_OBJECTS.add(RECIPE_BOOK_OBJ_ID);
        LOBBY_GAME_OBJECTS.add(POTIONS_BOOK_OBJ_ID);
    }

    // ==== Blue Gauntlet ====

    // Object IDs
    static final int BLUE_FLOOR_OBJ_ID = 36149;
    static final int BLUE_SPIKE_WALL_FLOOR_1_OBJ_ID = 36155;
    static final int BLUE_SPIKE_WALL_FLOOR_2_OBJ_ID = 36156;
    static final int BLUE_SPIKE_WALL_FLOOR_3_OBJ_ID = 36157;
    static final int BLUE_SPIKE_WALL_FLOOR_4_OBJ_ID = 36158;
    static final int BLUE_SPIKE_WALL_FLOOR_5_OBJ_ID = 36159;
    static final int BLUE_SPIKE_WALL_FLOOR_6_OBJ_ID = 36160;
    static final int BLUE_SPIKE_WALL_FLOOR_7_OBJ_ID = 36161;
    static final int BLUE_SPIKE_WALL_FLOOR_8_OBJ_ID = 36162;

    static final Set<Integer> BLUE_FLOOR_IDS = new HashSet();
    static {
        BLUE_FLOOR_IDS.add(BLUE_FLOOR_OBJ_ID);
        BLUE_FLOOR_IDS.add(LOBBY_FLOOR_OBJ_ID);
        BLUE_FLOOR_IDS.add(BLUE_SPIKE_WALL_FLOOR_1_OBJ_ID);
        BLUE_FLOOR_IDS.add(BLUE_SPIKE_WALL_FLOOR_2_OBJ_ID);
        BLUE_FLOOR_IDS.add(BLUE_SPIKE_WALL_FLOOR_3_OBJ_ID);
        BLUE_FLOOR_IDS.add(BLUE_SPIKE_WALL_FLOOR_4_OBJ_ID);
        BLUE_FLOOR_IDS.add(BLUE_SPIKE_WALL_FLOOR_5_OBJ_ID);
        BLUE_FLOOR_IDS.add(BLUE_SPIKE_WALL_FLOOR_6_OBJ_ID);
        BLUE_FLOOR_IDS.add(BLUE_SPIKE_WALL_FLOOR_7_OBJ_ID);
        BLUE_FLOOR_IDS.add(BLUE_SPIKE_WALL_FLOOR_8_OBJ_ID);
    }

    static final int BLUE_WINDOW_OBJ_ID = 36095;
    static final int BLUE_WINDOW_INACTIVE_OBJ_ID = 36097;
    static final int BLUE_SPIKE_WALL_CENTRE_OBJ_ID = 36106;
    static final int BLUE_SPIKE_WALL_RIGHT_OBJ_ID = 36109;
    static final int BLUE_SPIKE_WALL_LEFT_OBJ_ID = 36110;
    static final int BLUE_SPIKE_WALL_CORNER_OBJ_ID = 36111;
    static final int BLUE_SPIKE_WALL_DOUBLE1_OBJ_ID = 36107;
    static final int BLUE_SPIKE_WALL_DOUBLE2_OBJ_ID = 36108;

    static final Set<Integer> BLUE_GAUNTLET_IDS = new HashSet();
    static {
        BLUE_GAUNTLET_IDS.add(LOBBY_WINDOW_OBJ_ID);
        BLUE_GAUNTLET_IDS.add(CORNER_PILLAR_OBJ_ID);
        BLUE_GAUNTLET_IDS.add(BEHIND_WINDOW_LEFT_OBJ_ID);
        BLUE_GAUNTLET_IDS.add(BEHIND_WINDOW_RIGHT_OBJ_ID);
        BLUE_GAUNTLET_IDS.add(BLUE_WINDOW_OBJ_ID);
        BLUE_GAUNTLET_IDS.add(BLUE_WINDOW_INACTIVE_OBJ_ID);
        BLUE_GAUNTLET_IDS.add(BLUE_SPIKE_WALL_CENTRE_OBJ_ID);
        BLUE_GAUNTLET_IDS.add(BLUE_SPIKE_WALL_RIGHT_OBJ_ID);
        BLUE_GAUNTLET_IDS.add(BLUE_SPIKE_WALL_LEFT_OBJ_ID);
        BLUE_GAUNTLET_IDS.add(BLUE_SPIKE_WALL_CORNER_OBJ_ID);
        BLUE_GAUNTLET_IDS.add(BLUE_SPIKE_WALL_DOUBLE1_OBJ_ID);
        BLUE_GAUNTLET_IDS.add(BLUE_SPIKE_WALL_DOUBLE2_OBJ_ID);
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

    static final Set<Integer> BLUE_GAUNTLET_GAME_OBJECTS = new HashSet<Integer>();
    static {
        BLUE_GAUNTLET_GAME_OBJECTS.add(BLUE_WATER_PUMP_OBJ_ID);
        BLUE_GAUNTLET_GAME_OBJECTS.add(BLUE_RANGE_OBJ_ID);
        BLUE_GAUNTLET_GAME_OBJECTS.add(BLUE_SINGING_BOWL_OBJ_ID);
        BLUE_GAUNTLET_GAME_OBJECTS.add(BLUE_TOOL_STORAGE_OBJ_ID);
        BLUE_GAUNTLET_GAME_OBJECTS.add(BLUE_CHANNEL_TELEPORT_OBJ_ID);
        BLUE_GAUNTLET_GAME_OBJECTS.add(BLUE_DOOR_NODE_RIGHT_OBJ_ID);
        BLUE_GAUNTLET_GAME_OBJECTS.add(BLUE_DOOR_NODE_LEFT_OBJ_ID);
        BLUE_GAUNTLET_GAME_OBJECTS.add(BLUE_DOOR_NODE_RIGHT_LIT_OBJ_ID);
        BLUE_GAUNTLET_GAME_OBJECTS.add(BLUE_DOOR_NODE_LEFT_LIT_OBJ_ID);
        BLUE_GAUNTLET_GAME_OBJECTS.add(BLUE_HUNLLEF_BARRIER_OBJ_ID);
        BLUE_GAUNTLET_GAME_OBJECTS.add(BLUE_PHREN_ROOTS_OBJ_ID);
        BLUE_GAUNTLET_GAME_OBJECTS.add(BLUE_LINUM_OBJ_ID);
        BLUE_GAUNTLET_GAME_OBJECTS.add(BLUE_ROCKS_OBJ_ID);
        BLUE_GAUNTLET_GAME_OBJECTS.add(BLUE_GRYM_OBJ_ID);
    }

    // NPCs
    static final int BLUE_HUNLLEF_NPC_ID = 9021;
    static final int BLUE_HUNLLEF2_NPC_ID = 9022;
    static final int BLUE_HUNLLEF3_NPC_ID = 9023;
    static final int BLUE_RAT_NPC_ID = 9026;
    static final int BLUE_SPIDER_NPC_ID = 9027;
    static final int BLUE_BAT_NPC_ID = 9028;
    static final int BLUE_HORSE_NPC_ID = 9029;
    static final int BLUE_SCORPION_NPC_ID = 9030;
    static final int BLUE_WOLF_NPC_ID = 9031;
    static final int BLUE_BEAR_NPC_ID = 9032;
    static final int BLUE_DRAGON_NPC_ID = 9033;
    static final int BLUE_DARK_BEAST_NPC_ID = 9034;

    static final Set<Integer> BLUE_GAUNTLET_NPCS = new HashSet<Integer>();
    static {
        BLUE_GAUNTLET_NPCS.add(BLUE_HUNLLEF_NPC_ID);
        BLUE_GAUNTLET_NPCS.add(BLUE_HUNLLEF2_NPC_ID);
        BLUE_GAUNTLET_NPCS.add(BLUE_HUNLLEF3_NPC_ID);
        BLUE_GAUNTLET_NPCS.add(BLUE_RAT_NPC_ID);
        BLUE_GAUNTLET_NPCS.add(BLUE_SPIDER_NPC_ID);
        BLUE_GAUNTLET_NPCS.add(BLUE_BAT_NPC_ID);
        BLUE_GAUNTLET_NPCS.add(BLUE_HORSE_NPC_ID);
        BLUE_GAUNTLET_NPCS.add(BLUE_SCORPION_NPC_ID);
        BLUE_GAUNTLET_NPCS.add(BLUE_WOLF_NPC_ID);
        BLUE_GAUNTLET_NPCS.add(BLUE_BEAR_NPC_ID);
        BLUE_GAUNTLET_NPCS.add(BLUE_DRAGON_NPC_ID);
        BLUE_GAUNTLET_NPCS.add(BLUE_DARK_BEAST_NPC_ID);
    }



    ///////////////////////////////////


    // Recolors
    static final int[] LOBBY_GAME_OBJ_HSL_RANGE_VALUES = {26,45,1,8,1,140};
    static final int[] LOBBY_FLOOR_HSL_RANGE_VALUES = {1,40,1,9,1,150};
}
