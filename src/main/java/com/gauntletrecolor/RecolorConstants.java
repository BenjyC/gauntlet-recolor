package com.gauntletrecolor;

import com.gauntletrecolor.util.RecolorSelection;
import net.runelite.api.JagexColor;

import net.runelite.api.ObjectID;
import net.runelite.api.NpcID;

import java.util.*;

public final class RecolorConstants {

    // ==== Lobby ====

    // Object IDs TODO can a lot of these be the inbuilt OBJECTIDS?
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

    static final Set<Integer> LOBBY_GAME_OBJECTS = new HashSet<>();
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

    static final Set<Integer> BLUE_FLOOR_IDS = new HashSet<>();
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

    static final Set<Integer> BLUE_GAUNTLET_IDS = new HashSet<>();
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

    static final Set<Integer> BLUE_GAUNTLET_GAME_OBJECTS = new HashSet<>();
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
    static final Set<Integer> BLUE_GAUNTLET_NPCS = new HashSet<>();
    static {
        BLUE_GAUNTLET_NPCS.add(NpcID.CRYSTALLINE_HUNLLEF);
        BLUE_GAUNTLET_NPCS.add(NpcID.CRYSTALLINE_HUNLLEF_9022);
        BLUE_GAUNTLET_NPCS.add(NpcID.CRYSTALLINE_HUNLLEF_9023);
        BLUE_GAUNTLET_NPCS.add(NpcID.CRYSTALLINE_HUNLLEF_9024);
        BLUE_GAUNTLET_NPCS.add(NpcID.CRYSTALLINE_RAT);
        BLUE_GAUNTLET_NPCS.add(NpcID.CRYSTALLINE_SPIDER);
        BLUE_GAUNTLET_NPCS.add(NpcID.CRYSTALLINE_BAT);
        BLUE_GAUNTLET_NPCS.add(NpcID.CRYSTALLINE_UNICORN);
        BLUE_GAUNTLET_NPCS.add(NpcID.CRYSTALLINE_SCORPION);
        BLUE_GAUNTLET_NPCS.add(NpcID.CRYSTALLINE_WOLF);
        BLUE_GAUNTLET_NPCS.add(NpcID.CRYSTALLINE_BEAR);
        BLUE_GAUNTLET_NPCS.add(NpcID.CRYSTALLINE_DRAGON);
        BLUE_GAUNTLET_NPCS.add(NpcID.CRYSTALLINE_DARK_BEAST);
    }



    ///////////////////////////////////


    // Recolors
    static final Map<RecolorSelection,Integer> OBJECT_COLOR_MAP = new HashMap<>();
    static {
        OBJECT_COLOR_MAP.put(RecolorSelection.BLUE, 32);
        OBJECT_COLOR_MAP.put(RecolorSelection.NAVY, 38); //38,7,60
        OBJECT_COLOR_MAP.put(RecolorSelection.YELLOW, 9); //working
        OBJECT_COLOR_MAP.put(RecolorSelection.GREEN, 24); //working
        OBJECT_COLOR_MAP.put(RecolorSelection.RED, 1); //working but should probably use CG colors
        OBJECT_COLOR_MAP.put(RecolorSelection.BLACK, 11); //26, sat always 0, lum the same, /2 is very dark
        OBJECT_COLOR_MAP.put(RecolorSelection.WHITE, 42);
        OBJECT_COLOR_MAP.put(RecolorSelection.PURPLE, 34); //TODO can the enums themselves contain these values

    }

    static final Map<RecolorSelection,Short> FLOOR_COLOR_MAP = new HashMap<>();
    static {
        FLOOR_COLOR_MAP.put(RecolorSelection.BLUE, JagexColor.packHSL(32,3,50)); //TODO get the short values
        FLOOR_COLOR_MAP.put(RecolorSelection.NAVY, JagexColor.packHSL(38,7,50));
        FLOOR_COLOR_MAP.put(RecolorSelection.YELLOW, JagexColor.packHSL(9,3,50));
        FLOOR_COLOR_MAP.put(RecolorSelection.GREEN, JagexColor.packHSL(21,3,50));
        FLOOR_COLOR_MAP.put(RecolorSelection.RED, JagexColor.packHSL(1,3,50));
        FLOOR_COLOR_MAP.put(RecolorSelection.BLACK, JagexColor.packHSL(26,0,60));
        FLOOR_COLOR_MAP.put(RecolorSelection.WHITE, JagexColor.packHSL(42,0,140));
        FLOOR_COLOR_MAP.put(RecolorSelection.PURPLE, JagexColor.packHSL(52,3,50));
    }

    static final int[] LOBBY_GAME_OBJ_HSL_RANGE_VALUES = {26,45,1,8,1,140};
    static final int[] LOBBY_FLOOR_HSL_RANGE_VALUES = {1,40,1,9,1,150};
}
