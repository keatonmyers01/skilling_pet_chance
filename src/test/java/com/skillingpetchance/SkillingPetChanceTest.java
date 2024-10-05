package com.skillingpetchance;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SkillingPetChanceTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SkillingPetChancePlugin.class);
		RuneLite.main(args);
	}
}