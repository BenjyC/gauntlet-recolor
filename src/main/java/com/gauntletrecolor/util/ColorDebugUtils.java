package com.gauntletrecolor.util;

import net.runelite.api.JagexColor;
import net.runelite.api.Model;
import net.runelite.api.ModelData;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Utility class with some helper methods for finding specific HSL colors from models
 * Only used when you want to find the short values of specific HSL combinations
 */
public class ColorDebugUtils {

    //Model
    public static void loopColorsBlue(int[] colors, Set colorsToChange) {
        for (int color : colors) {
            Integer colorInt = color;
            int hue = JagexColor.unpackHue(colorInt.shortValue());
            int sat = JagexColor.unpackSaturation(colorInt.shortValue());
            int lum = JagexColor.unpackLuminance(colorInt.shortValue());

            //Change color range here depending on what color you want to find
            if ((hue >= 25 && hue <= 33)
                    && (sat >= 1 && sat <= 8)
                    && (lum >= 40 && lum <= 130)) {
                System.out.println("@@@@@@@@@@@@ Found color @@@@@@@@@@@@");
                System.out.println(color);
                System.out.println("HUE: " + hue);
                System.out.println("SAT: " + sat);
                System.out.println("LUM: " + lum);
                colorsToChange.add(color);
            }
        }
    }

    //Model Data
    public static void findColors(ModelData md) {
        //Change color range here depending on what color you want to find
        for (int hue = 28; hue <= 34; hue++) {
            for (int sat = 1; sat <= 8; sat++) {
                for (int lum = 1; lum <= 150; lum++) {
                    short colorToFind = JagexColor.packHSL(hue, sat, lum);
                    //System.out.printf("Checking => HUE:%d, SAT:%d, LUM:%d ... \n", hue, sat, lum);
                    loopColors(md, colorToFind, hue, sat, lum);
                }
            }
        }
    }

    public static void loopColors(ModelData md, short colorToFind, int hue, int sat, int lum) {
        for (short color : md.getFaceColors()) {
            if (color == colorToFind) {
                System.out.println("@@@@@@@@@@@@ Found color @@@@@@@@@@@@");
                System.out.println("HUE: " + hue);
                System.out.println("SAT: " + sat);
                System.out.println("LUM: " + lum);
                break;
            }
        }
    }


    public static void writeColorArrays(Model model, int objId) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("/home/benjyc/Dev/Github/Other/RuneLite/MyPlugins/gauntlet-recolor/src/main/java/com/gauntletrecolor/outputColors.txt"),true));
        printWriter.println(objId);
        printWriter.printf("Array 1: %s", Arrays.toString(model.getFaceColors1()));
        printWriter.println("");
        printWriter.printf("Array 2: %s", Arrays.toString(model.getFaceColors2()));
        printWriter.println("");
        printWriter.printf("Array 3: %s", Arrays.toString(model.getFaceColors3()));
        printWriter.println("");

        printWriter.close();
    }

}