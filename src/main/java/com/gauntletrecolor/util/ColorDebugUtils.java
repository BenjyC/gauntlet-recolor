package com.gauntletrecolor.util;

import net.runelite.api.JagexColor;
import net.runelite.api.ModelData;

import java.awt.*;
import java.util.Set;

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

    public static void changeGameObjectColors(int[] colors, int[] hslArray) {
        for (int i = 0; i < colors.length; i++) {
            Integer colorInt = colors[i];
            int hue = JagexColor.unpackHue(colorInt.shortValue());
            int sat = JagexColor.unpackSaturation(colorInt.shortValue());
            int lum = JagexColor.unpackLuminance(colorInt.shortValue());

            if ((hue >= hslArray[0] && hue <= hslArray[1])
                    && (sat >= hslArray[2] && sat <= hslArray[3])
                    && (lum >= hslArray[4] && lum <= hslArray[5])) {
                //Recolor to yellow TODO change to any color
                colors[i] = JagexColor.packHSL(9,sat,lum);
            }
        }
    }

    public static void changeFloorColors(int[] colors, int[] hslArray) {
        for (int i = 0; i < colors.length; i++) {
            Integer colorInt = colors[i];
            int hue = JagexColor.unpackHue(colorInt.shortValue());
            int sat = JagexColor.unpackSaturation(colorInt.shortValue());
            int lum = JagexColor.unpackLuminance(colorInt.shortValue());

            if ((hue >= hslArray[0] && hue <= hslArray[1])
                    && (sat >= hslArray[2] && sat <= hslArray[3])
                    && (lum >= hslArray[4] && lum <= hslArray[5])) {
                //Recolor to yellow TODO change to any color
                colors[i] = JagexColor.packHSL(9,3,50);
            }
        }
    }


    //Model Data
    public static void findColors(ModelData md) {
        //Change color range here depending on what color you want to find
        for (int hue = 29; hue <= 33; hue++) {
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
}