package com.skillingpetchance.giantsquirrel;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.skillingpetchance.Action;
import com.skillingpetchance.PoissonCalculator;
import com.skillingpetchance.SkillingPetChanceConfig;
import com.skillingpetchance.StaticAction;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class GiantSquirrelTracker {
    private final String KEY = "giantSquirrel";

    private final ConfigManager configManager;
    private final Client client;

    private ConfigGiantSquirrel configGiantSquirrel;

    PoissonCalculator poissonCalculator = new PoissonCalculator();

    @Inject
    private Gson gson;

    @Inject
    private GiantSquirrelTracker(ConfigManager configManager, Client client) {
        this.configManager = configManager;
        this.client = client;
    }

    private Action getAction(int skillLevel, String actionPerformed) {
        Map<Integer, Map<String, Action>> actions = configGiantSquirrel.getActions();
        Map<String, Action> level = actions.computeIfAbsent(skillLevel, k -> new HashMap<String, Action>());

        Action action = level.get(actionPerformed);

        if(action == null){
            Map<String, Integer> baseRates = configGiantSquirrel.getBaseRates();
            if (baseRates.get(actionPerformed) == null){
                return null;
            }
            action = new Action(skillLevel, baseRates.get(actionPerformed), actionPerformed);
            level.put(actionPerformed,action);
        }
        return action;
    }

    public void addEntry(int skillLevel, String actionPerformed){
        actionPerformed =actionPerformed.toUpperCase();
        Action action = getAction(skillLevel, actionPerformed);
        if(action == null) {
            return;
        }

        action.incrementQuantity(1 );
        saveToConfig(configGiantSquirrel);
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", action.toString(), null);
        double rate = poissonCalculator.calculateSuccess(configGiantSquirrel.getActions());
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "total rate: " + rate, null);
    }

    public void addStaticEntry(String actionPerformed){
        actionPerformed =actionPerformed.toUpperCase();
        Map<String, StaticAction> actions = configGiantSquirrel.getStaticActions();

        StaticAction action = actions.get(actionPerformed);
        action.incrementQuantity(1 );
        saveToConfig(configGiantSquirrel);
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", action.toString(), null);
        double rate = poissonCalculator.calculateSuccess(configGiantSquirrel.getActions());
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "total rate: " + rate, null);
    }

    public void loadFromConfig(){
        String storedValue = configManager.getRSProfileConfiguration(SkillingPetChanceConfig.CONFIG_GROUP, KEY);

        if(storedValue == null || storedValue.isEmpty()){
            configGiantSquirrel = new ConfigGiantSquirrel();
        }else{
            try{
                configGiantSquirrel = gson.fromJson(storedValue, ConfigGiantSquirrel.class);
                configGiantSquirrel.setRates();
            } catch (JsonSyntaxException ex){
                configGiantSquirrel = null;
            }
        }
    }

    public void saveToConfig(ConfigGiantSquirrel configGiantSquirrel)
    {
        String json = gson.toJson(configGiantSquirrel);
        {
            configManager.setRSProfileConfiguration(SkillingPetChanceConfig.CONFIG_GROUP, KEY, json);
        }
    }
}
