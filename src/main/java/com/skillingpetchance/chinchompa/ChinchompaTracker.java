package com.skillingpetchance.chinchompa;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.skillingpetchance.Action;
import com.skillingpetchance.PoissonCalculator;
import com.skillingpetchance.SkillingPetChanceConfig;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class ChinchompaTracker {
    private final String KEY = "chinchompa";

    private final ConfigManager configManager;
    private final Client client;

    private ConfigChinchompa configChinchompa;

    PoissonCalculator poissonCalculator = new PoissonCalculator();

    @Inject
    private Gson gson;

    @Inject
    private ChinchompaTracker(ConfigManager configManager, Client client) {
        this.configManager = configManager;
        this.client = client;
    }

    private Action getAction(int skillLevel, String actionPerformed) {
        Map<Integer, Map<String, Action>> actions = configChinchompa.getActions();
        Map<String, Action> level = actions.computeIfAbsent(skillLevel, k -> new HashMap<String, Action>());

        Action action = level.get(actionPerformed);

        if(action == null){
            Map<String, Integer> baseRates = configChinchompa.getBaseRates();
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
        saveToConfig(configChinchompa);
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", action.toString(), null);
        double rate = poissonCalculator.calculateSuccess(configChinchompa.getActions());
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "total rate: " + rate, null);
    }

    public void loadFromConfig(){
        String storedValue = configManager.getRSProfileConfiguration(SkillingPetChanceConfig.CONFIG_GROUP, KEY);

        if(storedValue == null || storedValue.isEmpty()){
            configChinchompa = new ConfigChinchompa();
        }else{
            try{
                configChinchompa = gson.fromJson(storedValue, ConfigChinchompa.class);
                configChinchompa.setRates();
            } catch (JsonSyntaxException ex){
                configChinchompa = null;
            }
        }
    }

    public void saveToConfig(ConfigChinchompa configChinchompa)
    {
        String json = gson.toJson(configChinchompa);
        {
            configManager.setRSProfileConfiguration(SkillingPetChanceConfig.CONFIG_GROUP, KEY, json);
        }
    }
}
