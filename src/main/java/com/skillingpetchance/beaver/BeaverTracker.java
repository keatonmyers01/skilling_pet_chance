package com.skillingpetchance.beaver;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.skillingpetchance.Action;
import com.skillingpetchance.PoissonCalculator;
import com.skillingpetchance.SkillingPetChanceConfig;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class BeaverTracker {
    private final String KEY = "beaver";

    private final ConfigManager configManager;
    private final Client client;

    private ConfigBeaver configBeaver;

    PoissonCalculator poissonCalculator = new PoissonCalculator();

    int unknownCut;

    @Inject
    private Gson gson;

    @Inject
    private BeaverTracker(ConfigManager configManager, Client client) {
        this.configManager = configManager;
        this.client = client;
    }

    public void incrementUnknownCut(){
        unknownCut++;
    }

    private Action getAction(int skillLevel, String actionPerformed) {
        Map<Integer, Map<String, Action>> actions = configBeaver.getActions();
        Map<String, Action> level = actions.computeIfAbsent(skillLevel, k -> new HashMap<String, Action>());

        Action action = level.get(actionPerformed);

        if(action == null){
            Map<String, Integer> baseRates = configBeaver.getBaseRates();
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
        if(action == null){
            return;
        }

        action.incrementQuantity(1 + unknownCut);
        unknownCut = 0;
        saveToConfig(configBeaver);
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", action.toString(), null);
        double rate = poissonCalculator.calculateSuccess(configBeaver.getActions());
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "total rate: " + rate, null);

    }

    public void loadFromConfig(){
        String storedValue = configManager.getRSProfileConfiguration(SkillingPetChanceConfig.CONFIG_GROUP, KEY);

        if(storedValue == null || storedValue.isEmpty()){
            configBeaver = new ConfigBeaver();
        }else{
            try{
                configBeaver = gson.fromJson(storedValue, ConfigBeaver.class);
                configBeaver.setRates();
            } catch (JsonSyntaxException ex){
                configBeaver = null;
            }
        }
    }

    public void saveToConfig(ConfigBeaver configBeaver)
    {
        String json = gson.toJson(configBeaver);
        {
            configManager.setRSProfileConfiguration(SkillingPetChanceConfig.CONFIG_GROUP, KEY, json);
        }
    }
}
