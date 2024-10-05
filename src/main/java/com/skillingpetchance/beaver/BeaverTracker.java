package com.skillingpetchance.beaver;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.skillingpetchance.SkillingPetChanceConfig;
import net.runelite.client.config.ConfigManager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Singleton
public class BeaverTracker {
    private final String KEY = "beaver";

    private final ConfigManager configManager;

    @Inject
    private Gson gson;

    @Inject
    private BeaverTracker(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public ConfigBeaver loadFromConfig(){
        System.out.println("loading");
        String storedValue = configManager.getRSProfileConfiguration(SkillingPetChanceConfig.CONFIG_GROUP, KEY);
        if(storedValue != null) System.out.println("storedValue");
        ConfigBeaver configBeaver;
        if(storedValue == null || storedValue.isEmpty()){
            configBeaver = new ConfigBeaver();
        }else{
            try{
            configBeaver = gson.fromJson(storedValue, ConfigBeaver.class);
            } catch (JsonSyntaxException ex){
                configBeaver = null;
            }
        }
        return configBeaver;
    }

    public void saveToConfig(ConfigBeaver configBeaver)
    {
        String json = gson.toJson(configBeaver);
        {
            System.out.println("saving");
            configManager.setRSProfileConfiguration(SkillingPetChanceConfig.CONFIG_GROUP, KEY, json);
            System.out.println(configManager.getRSProfileConfiguration(SkillingPetChanceConfig.CONFIG_GROUP, KEY));
        }
    }
}
