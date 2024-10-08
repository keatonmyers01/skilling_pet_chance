package com.skillingpetchance;

import com.google.inject.Provides;
import javax.inject.Inject;

import com.skillingpetchance.beaver.BeaverTracker;
import com.skillingpetchance.chinchompa.ChinchompaTracker;
import com.skillingpetchance.giantsquirrel.GiantSquirrelTracker;
import com.skillingpetchance.heron.HeronTracker;
import com.skillingpetchance.riftguardian.RiftGuardianTracker;
import com.skillingpetchance.rockgolem.RockGolemTracker;
import com.skillingpetchance.rocky.RockyTracker;
import com.skillingpetchance.tangleroot.TanglerootTracker;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.WidgetLoaded;
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
	private boolean ignore = false;

	//patterns
	private static final Pattern WOOD_CUT_PATTERN = Pattern.compile("You get (?:some|an)[\\w ]+(?:logs?|mushrooms)\\.");
	private static final Pattern CLEAN_CUT_PATTERN = Pattern.compile("You strike a clean cut without gathering any material\\.");
	private static final Pattern CHINCHOMPA_PATTERN = Pattern.compile("You've caught a (black |carnivorous )?chinchompa\\.");
	private static final Pattern ORE_MINE_PATTERN = Pattern.compile("^You manage to (mine|chip|quarry)\\b.*");
	private static final Pattern COMMON_ORE_MINE_PATTERN = Pattern.compile("^(?:\\S+\\s+){5}(clay|copper|tin|blurite|limestone|iron|silver|barronite|shards|sandstone|granite|volcanic ash)\\b.*");
	private static final Pattern GEM_MINE_PATTERN = Pattern.compile("^You just mined\\b.*");
	private static final Pattern VOLCANIC_MINE_PATTERN = Pattern.compile("You mine out.*");
	private static final Pattern MAN_PICKPOCKET_PATTERN = Pattern.compile("^You pick the (man's|woman's|warrior's|villager's|rogue's|cave goblin's|Master Farmer's|guard's|Fremennik's|bandit's|Bandit's|knight's|Menaphite's) pocket\\.");
	private static final Pattern PICKPOCKET_PATTERN = Pattern.compile("^You pick the (watchman's|paladin's|gnome's|hero's|elf's|vyre's|wealthy) pocket\\.");
	private static final Pattern STALL_PATTERN = Pattern.compile("You steal .*");
	private static final Pattern AGILITY_PATTERN = Pattern.compile("Your (.+?) lap count is:.*");
	private static final Pattern SEPULCHRE_PATTERN = Pattern.compile("Floor \\d+ time:.*");
	private static final Pattern TRAWLER_PATTERN = Pattern.compile("You have contributed enough to earn rewards!*");
	private static final Pattern FISHING_PATTERN = Pattern.compile("You catch (some|a|an)?(\\d{1,2} )?.*");
	private static final Pattern AERIAL_PATTERN = Pattern.compile("You send your cormorant to try to catch a fish from out at sea\\.");
	private static final Pattern VALUABLES_PATTERN = Pattern.compile("You (find some valuable items\\.|grab a bunch of valuables!)");



	@Inject
	private BeaverTracker beaverTracker;

	@Inject
	private ChinchompaTracker chinchompaTracker;

	@Inject
	private RockGolemTracker rockGolemTracker;

	@Inject
	private RockyTracker rockyTracker;

	@Inject
	private HeronTracker heronTracker;

	@Inject
	private RiftGuardianTracker riftGuardianTracker;

	@Inject
	private GiantSquirrelTracker giantSquirrelTracker;

	@Inject
	private TanglerootTracker tanglerootTracker;

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
	private void checkMaxXp(){
		if(client.getSkillExperience(WOODCUTTING) == 200000000) woodcuttingLevel = 200;
		if(client.getSkillExperience(FARMING) == 200000000) farmingLevel = 200;
		if(client.getSkillExperience(AGILITY) == 200000000) agilityLevel = 200;
		if(client.getSkillExperience(HUNTER) == 200000000) hunterLevel = 200;
		if(client.getSkillExperience(FISHING) == 200000000) fishingLevel = 200;
		if(client.getSkillExperience(MINING) == 200000000) miningLevel = 200;
		if(client.getSkillExperience(RUNECRAFT) == 200000000) runecraftingLevel = 200;
		if(client.getSkillExperience(THIEVING) == 200000000) thievingLevel = 200;
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
		checkMaxXp();
		String[] splitStr = null;
		final var msg = event.getMessage();

		//Woodcutting
		//TODO DISABLE on miscellania, ecetera, tutorial island, and pest control
		//accuracy could be improved if I can figure out detecting numilite/fossils at fossil island.
		if(WOOD_CUT_PATTERN.matcher(msg).matches())
		{
			splitStr = msg.split("\\s+");
			previousCut = splitStr[3].replaceAll("\\.", "");

			beaverTracker.addEntry(woodcuttingLevel, previousCut);
		}

		if(CLEAN_CUT_PATTERN.matcher(msg).matches())
		{
			if(previousCut == null || previousCut.isEmpty()){
				beaverTracker.incrementUnknownCut();
			}
			else{
				beaverTracker.addEntry(woodcuttingLevel, previousCut);
			}
		}

		//Hunter
		if(CHINCHOMPA_PATTERN.matcher(msg).matches()){
			splitStr = msg.split("\\s+");
			String action = splitStr[3].replaceAll("\\.", "");

			chinchompaTracker.addEntry(hunterLevel , action);
		}

		//Mining
		if(ORE_MINE_PATTERN.matcher(msg).matches()){
			if(COMMON_ORE_MINE_PATTERN.matcher(msg).matches()){
				rockGolemTracker.addEntry(miningLevel, "common ore");
			}

			splitStr = msg.split("\\s+");
			String action = splitStr[5].replaceAll("\\.", "");
			if(action.equals("volcanic")){
				action = "volcanic sulphur";
			}
			rockGolemTracker.addEntry(miningLevel , action);
		}

		if(GEM_MINE_PATTERN.matcher(msg).matches()){
			rockGolemTracker.addEntry(miningLevel , "gem");
		}

		if(VOLCANIC_MINE_PATTERN.matcher(msg).matches()){
			rockGolemTracker.addEntry(miningLevel , "volcanic mine");
		}

		//Thieving

		if(MAN_PICKPOCKET_PATTERN.matcher(msg).matches()){
			rockyTracker.addEntry(thievingLevel, "common pickpocket");
		}

		if(PICKPOCKET_PATTERN.matcher(msg).matches()){
			splitStr = msg.split("\\s+");
			String action = splitStr[3].replaceAll("'s", "");
			rockyTracker.addEntry(thievingLevel, action);
		}

		//squirk
		if(msg.equals("You bend down to pick some herbs.")){
			ignore = true;
		}
		if(msg.equals("An elemental force emanating from the garden teleports you away.") && !ignore){
			rockyTracker.addEntry(thievingLevel, "squirk");
		} else if(msg.equals("An elemental force emanating from the garden teleports you away.")){
			ignore = false;
		}

		if(STALL_PATTERN.matcher(msg).matches()){
			String afterPattern = msg.split("steal ")[1].replaceAll("\\.", "");
			String item = msg.split("\\s+")[3];

			if(afterPattern.matches("(some|an) (bread|cake|choclate slice)")){
				rockyTracker.addEntry(thievingLevel, "Bakery Stall");
			}else if(afterPattern.matches("(a|an) (potato|tomato|clove of garlic|cabbage|onion)")){
				rockyTracker.addEntry(thievingLevel, "Veg Stall");
			}else if(afterPattern.matches("(a|some|an|the) (apple|strawberry|pineapple|papaya|strange fruit|golovanova top|lemon|lime|redberries|jangerberries|banana)")){
				rockyTracker.addEntry(thievingLevel, "Fruit Stall");
			}else if(item.equals("silk") || item.equals("tea")){
				rockyTracker.addEntry(thievingLevel, item + " Stall");
			}else{
				rockyTracker.addEntry(thievingLevel,  "Common Stall");
			}

		}

		if(VALUABLES_PATTERN.matcher(msg).matches()){
			rockyTracker.addEntry(thievingLevel,  "valuables");
		}

		//AGILITY
		if(AGILITY_PATTERN.matcher(msg).matches()){
			String course = msg.split("Your ")[1].split(" lap")[0];
			giantSquirrelTracker.addEntry(agilityLevel, course);
		}

		if(SEPULCHRE_PATTERN.matcher(msg).matches()){
			String floor = msg.split("\\s+")[1];
			giantSquirrelTracker.addStaticEntry("FLOOR " + floor);
		}

		//FISHING
		if(TRAWLER_PATTERN.matcher(msg).matches()) {
			heronTracker.addStaticEntry("trawler");
		}

		if(FISHING_PATTERN.matcher(msg).matches()){
			splitStr = msg.split("\\s+");
			String action = splitStr[3].replaceAll("\\.", "").replaceAll("!", "").replace("shrimp", "shrimps");
			heronTracker.addEntry(fishingLevel, action);
		}

		if(AERIAL_PATTERN.matcher(msg).matches()){
			heronTracker.addEntry(fishingLevel, "aerial");
		}
	}

    @Subscribe
    public void onWidgetLoaded(WidgetLoaded event) {

    }

    @Subscribe
	public void onRuneScapeProfileChanged(RuneScapeProfileChanged e){
		beaverTracker.loadFromConfig();
		chinchompaTracker.loadFromConfig();
		rockGolemTracker.loadFromConfig();
		rockyTracker.loadFromConfig();
		heronTracker.loadFromConfig();
		tanglerootTracker.loadFromConfig();
		giantSquirrelTracker.loadFromConfig();
		riftGuardianTracker.loadFromConfig();
	}

	@Provides
	SkillingPetChanceConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SkillingPetChanceConfig.class);
	}
}
