package com.skillingpetchance.tangleroot;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.skillingpetchance.Action;
import com.skillingpetchance.PoissonCalculator;
import com.skillingpetchance.SkillingPetChanceConfig;
import com.skillingpetchance.TrackerInterface;
import com.skillingpetchance.chinchompa.ConfigChinchompa;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class TanglerootTracker implements TrackerInterface<ConfigTangleroot> {
    private final String KEY = "tangleroot";

    private final ConfigManager configManager;
    private final Client client;

    private ConfigTangleroot configTangleroot;

    double rate = 0;

    PoissonCalculator poissonCalculator = new PoissonCalculator();

    @Inject
    private Gson gson;

    @Inject
    private TanglerootTracker(ConfigManager configManager, Client client) {
        this.configManager = configManager;
        this.client = client;
    }

    public double getRate(){
        return rate;
    }

    private Action getAction(int skillLevel, String actionPerformed) {
        actionPerformed = actionPerformed.toUpperCase();
        Map<Integer, Map<String, Action>> actions = configTangleroot.getActions();
        Map<String, Action> level = actions.computeIfAbsent(skillLevel, k -> new HashMap<String, Action>());

        Action action = level.get(actionPerformed);

        if(action == null){
            Map<String, Integer> baseRates = configTangleroot.getBaseRates();
            if (baseRates.get(actionPerformed) == null){
                return null;
            }
            action = new Action(skillLevel, baseRates.get(actionPerformed));
            level.put(actionPerformed,action);
        }
        return action;
    }

    @Override
    public void addEntry(int skillLevel, String actionPerformed){
        Action action = getAction(skillLevel, actionPerformed);
        if(action == null) {
            return;
        }

        action.incrementQuantity(1 );
        saveToConfig(configTangleroot);
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", action.toString(), null);
        rate = poissonCalculator.calculateSuccess(configTangleroot.getActions());
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "total rate: " + rate, null);
    }

    public void loadFromConfig(){
        String storedValue = configManager.getRSProfileConfiguration(SkillingPetChanceConfig.CONFIG_GROUP, KEY);

        if(storedValue == null || storedValue.isEmpty()){
            configTangleroot = new ConfigTangleroot();
        }else{
            try{
                configTangleroot = gson.fromJson(storedValue, ConfigTangleroot.class);
                configTangleroot.setRates();
            } catch (JsonSyntaxException ex){
                configTangleroot = null;
            }
        }
    }

    public void saveToConfig(ConfigTangleroot configTangleroot)
    {
        String json = gson.toJson(configTangleroot);
        {
            configManager.setRSProfileConfiguration(SkillingPetChanceConfig.CONFIG_GROUP, KEY, json);
        }
    }
}
