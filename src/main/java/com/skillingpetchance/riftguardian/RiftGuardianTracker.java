package com.skillingpetchance.riftguardian;

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
public class RiftGuardianTracker {
    private final String KEY = "riftGuardian";

    private final ConfigManager configManager;
    private final Client client;

    private ConfigRiftGuardian configRiftGuardian;

    PoissonCalculator poissonCalculator = new PoissonCalculator();

    private int Daeyalt_Count = 0;
    private int Essence_Count = 0;

    @Inject
    private Gson gson;

    @Inject
    private RiftGuardianTracker(ConfigManager configManager, Client client) {
        this.configManager = configManager;
        this.client = client;
    }

    private Action getAction(int skillLevel, String actionPerformed) {
        Map<Integer, Map<String, Action>> actions = configRiftGuardian.getActions();
        Map<String, Action> level = actions.computeIfAbsent(skillLevel, k -> new HashMap<String, Action>());

        Action action = level.get(actionPerformed);

        if(action == null){
            Map<String, Integer> baseRates = configRiftGuardian.getBaseRates();
            if (baseRates.get(actionPerformed) == null){
                return null;
            }
            action = new Action(skillLevel, baseRates.get(actionPerformed));
            level.put(actionPerformed,action);
        }
        return action;
    }

    public void addEntry(int skillLevel, String actionPerformed, int quantity){
        actionPerformed =actionPerformed.toUpperCase();
        Action action = getAction(skillLevel, actionPerformed);
        if(action == null) {
            return;
        }

        action.incrementQuantity(quantity);
        saveToConfig(configRiftGuardian);
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", action.toString(), null);
        double rate = poissonCalculator.calculateSuccess(configRiftGuardian.getActions());
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "total rate: " + rate, null);
    }

    public void loadFromConfig(){
        String storedValue = configManager.getRSProfileConfiguration(SkillingPetChanceConfig.CONFIG_GROUP, KEY);

        if(storedValue == null || storedValue.isEmpty()){
            configRiftGuardian = new ConfigRiftGuardian();
        }else{
            try{
                configRiftGuardian = gson.fromJson(storedValue, ConfigRiftGuardian.class);
                configRiftGuardian.setRates();
            } catch (JsonSyntaxException ex){
                configRiftGuardian = null;
            }
        }
    }

    public void saveToConfig(ConfigRiftGuardian configRiftGuardian)
    {
        String json = gson.toJson(configRiftGuardian);
        {
            configManager.setRSProfileConfiguration(SkillingPetChanceConfig.CONFIG_GROUP, KEY, json);
        }
    }

    public int getDaeyalt() {
        return this.Daeyalt_Count;
    }

    public void setDaeyalt(int amount) {
        this.Daeyalt_Count = amount;
    }

    public int getRegular() {
        return this.Essence_Count;
    }

    public void setRegular(int amount) {
        this.Essence_Count = amount;
    }
}
