package com.gauntletrecolor;

import com.gauntletrecolor.util.RecolorSelection;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(GauntletRecolorConfig.CONFIG_GROUP)
public interface GauntletRecolorConfig extends Config
{
	String CONFIG_GROUP = "gauntletrecolor";

	@ConfigItem(
			keyName = "colorSelection",
			name = "Recolor Selection",
			description = "Choose which color the Gauntlet will be recolored to.",
			position = 2
	)
	default RecolorSelection recolorSelection() { return RecolorSelection.BLUE; }
}
