package com.gauntletrecolor;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class GauntletRecolorPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(GauntletRecolorPlugin.class);
		RuneLite.main(args);
	}
}