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

    PoissonCalculator poissonCalculator;

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

    public void addEntry(int woodcuttingLevel, String previousCut){
        Map<Integer, Map<String, Action>> actions = configBeaver.getActions();
        Map<String, Action> level = actions.computeIfAbsent(woodcuttingLevel, k -> new HashMap<String, Action>());

        Action action = level.get(previousCut);

        if(action == null){
            Map<String, Integer> baseRates = configBeaver.getBaseRates();
            action = new Action(woodcuttingLevel, baseRates.get(previousCut), previousCut);
            level.put(previousCut,action);
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
            System.out.println("no config");
            configBeaver = new ConfigBeaver();
        }else{
            try{
                System.out.println("config loaded");
                configBeaver = gson.fromJson(storedValue, ConfigBeaver.class);
                configBeaver.setRates();
                Map<Integer, Map<String, Action>> actions = configBeaver.getActions();
                if(actions != null) {
                    System.out.println(actions.toString());
                    System.out.println(poissonCalculator.calculateSuccess(actions));
                }else {
                    System.out.println("actions is null");
                }
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
