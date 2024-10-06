package com.skillingpetchance;

import com.google.inject.Provides;
import javax.inject.Inject;

import com.skillingpetchance.beaver.BeaverTracker;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.RuneScapeProfileChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.Skill;

import java.util.regex.Pattern;

@Slf4j
@PluginDescriptor(
	name = "Skilling Pet Chance"
)
public class SkillingPetChancePlugin extends Plugin
{

	private static  final Skill WOODCUTTING = Skill.WOODCUTTING;
	private static  final Skill FARMING = Skill.FARMING;
	private static  final Skill FISHING = Skill.FISHING;
	private static  final Skill HUNTER = Skill.HUNTER;
	private static  final Skill MINING = Skill.MINING;
	private static  final Skill RUNECRAFT = Skill.RUNECRAFT;
	private static  final Skill THIEVING = Skill.THIEVING;
	private static  final Skill AGILITY = Skill.AGILITY;
	private int woodcuttingLevel;
	private int farmingLevel;
	private int agilityLevel;
	private int hunterLevel;
	private int fishingLevel;
	private int miningLevel;
	private int runecraftingLevel;
	private int thievingLevel;


	private String previousCut;
	private int unknownCut = 0;

	//patterns
	private static final Pattern WOOD_CUT_PATTERN = Pattern.compile("You get (?:some|an)[\\w ]+(?:logs?|mushrooms)\\.");
	private static final Pattern CLEAN_CUT_PATTERN = Pattern.compile("You strike a clean cut without gathering any material.");

	@Inject
	private BeaverTracker beaverTracker;

	@Inject
	private Client client;

	@Inject
	private ConfigManager configManager;

	@Inject
	private SkillingPetChanceConfig config;

	@Override
	protected void startUp() throws Exception
	{
	}

	@Override
	protected void shutDown() throws Exception
	{
	}

	private void updateLevels(){
		woodcuttingLevel = client.getRealSkillLevel(WOODCUTTING);
		farmingLevel = client.getRealSkillLevel(FARMING);
		agilityLevel = client.getRealSkillLevel(AGILITY);
		hunterLevel = client.getRealSkillLevel(HUNTER);
		fishingLevel = client.getRealSkillLevel(FISHING);
		miningLevel = client.getRealSkillLevel(MINING);
		runecraftingLevel = client.getRealSkillLevel(RUNECRAFT);
		thievingLevel = client.getRealSkillLevel(THIEVING);
	}

	@Subscribe
	public void onChatMessage(ChatMessage event) {
		if (event.getType() != ChatMessageType.SPAM
				&& event.getType() != ChatMessageType.GAMEMESSAGE
				&& event.getType() != ChatMessageType.MESBOX)
		{
			return;
		}

		updateLevels();
		String[] splitStr = null;
		final var msg = event.getMessage();

		//woodcutting
		//TODO DISABLE on miscellania, ecetera, tutorial island, and pest control
		//accuracy could be improved if I can figure out detecting numilite/fossils at fossil island.
		if (WOOD_CUT_PATTERN.matcher(msg).matches())
		{
			splitStr = msg.split("\\s+");
			previousCut = splitStr[3].replaceAll("\\.", "");

			beaverTracker.addEntry(woodcuttingLevel, previousCut);
		}

		if (CLEAN_CUT_PATTERN.matcher(msg).matches())
		{
			if(previousCut == null || previousCut.isEmpty()){
				beaverTracker.incrementUnknownCut();
			}
			else{
				beaverTracker.addEntry(woodcuttingLevel, previousCut);
			}
		}
	}

	@Subscribe
	public void onRuneScapeProfileChanged(RuneScapeProfileChanged e){
		beaverTracker.loadFromConfig();
	}

	@Provides
	SkillingPetChanceConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SkillingPetChanceConfig.class);
	}
}
