package com.skillingpetchance.heron;

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
public class HeronTracker {
    private final String KEY = "heron";

    private final ConfigManager configManager;
    private final Client client;

    private ConfigHeron configHeron;

    PoissonCalculator poissonCalculator = new PoissonCalculator();

    @Inject
    private Gson gson;

    @Inject
    private HeronTracker(ConfigManager configManager, Client client) {
        this.configManager = configManager;
        this.client = client;
    }

    private Action getAction(int skillLevel, String actionPerformed) {
        Map<Integer, Map<String, Action>> actions = configHeron.getActions();
        Map<String, Action> level = actions.computeIfAbsent(skillLevel, k -> new HashMap<String, Action>());

        Action action = level.get(actionPerformed);

        if(action == null){
            Map<String, Integer> baseRates = configHeron.getBaseRates();
            if (baseRates.get(actionPerformed) == null){
                return null;
            }
            action = new Action(skillLevel, baseRates.get(actionPerformed));
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
        saveToConfig(configHeron);
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", action.toString(), null);
        double rate = poissonCalculator.calculateSuccess(configHeron.getActions());
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "total rate: " + rate, null);
    }

    public void addStaticEntry(String actionPerformed){
        actionPerformed =actionPerformed.toUpperCase();
        Map<String, StaticAction> actions = configHeron.getStaticActions();

        StaticAction action = actions.get(actionPerformed);
        action.incrementQuantity(1 );
        saveToConfig(configHeron);
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", action.toString(), null);
        double rate = poissonCalculator.calculateSuccess(configHeron.getActions());
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "total rate: " + rate, null);
    }

    public void loadFromConfig(){
        String storedValue = configManager.getRSProfileConfiguration(SkillingPetChanceConfig.CONFIG_GROUP, KEY);

        if(storedValue == null || storedValue.isEmpty()){
            configHeron = new ConfigHeron();
        }else{
            try{
                configHeron = gson.fromJson(storedValue, ConfigHeron.class);
                configHeron.setRates();
            } catch (JsonSyntaxException ex){
                configHeron = null;
            }
        }
    }

    public void saveToConfig(ConfigHeron configHeron)
    {
        String json = gson.toJson(configHeron);
        {
            configManager.setRSProfileConfiguration(SkillingPetChanceConfig.CONFIG_GROUP, KEY, json);
        }
    }
}
