package com.skillingpetchance.giantsquirrel;

import com.skillingpetchance.AbstractConfig;
import com.skillingpetchance.StaticAction;

import java.util.HashMap;
import java.util.Map;


public class ConfigGiantSquirrel extends AbstractConfig {
    Map<String, StaticAction> staticActions;

    public ConfigGiantSquirrel(){
        super();
        staticActions = Map.<String, StaticAction>ofEntries(
                Map.entry("sepulchre floor 1", new StaticAction(35000, "sepulchre floor 1")),
                Map.entry("sepulchre floor 2", new StaticAction(16000, "sepulchre floor 2")),
                Map.entry("sepulchre floor 3", new StaticAction(8000, "sepulchre floor 3")),
                Map.entry("sepulchre floor 4", new StaticAction(4000, "sepulchre floor 4")),
                Map.entry("sepulchre floor 5", new StaticAction(2000, "sepulchre floor 5"))
        );
    }

    @Override
    protected Map<String, Integer> initializeBaseRates() {
        return Map.<String, Integer>ofEntries(
                Map.entry("Barbarian Outpost", 131395),
                Map.entry("Ape Atoll Agility", 98373),
                Map.entry("Canifis Rooftop", 82758),
                Map.entry("Gnome Stronghold Agility", 131395),
                Map.entry("Seers' Village Rooftop", 98373),
                Map.entry("Wilderness Agility", 82758),
                Map.entry("Ardougne Rooftop", 131395),
                Map.entry("Pollnivneach Rooftop", 98373),
                Map.entry("Draynor Village Rooftop", 82758),
                Map.entry("Rellekka Rooftop", 131395),
                Map.entry("Falador Rooftop", 82758),
                Map.entry("Al Kharid Rooftop", 131395),
                Map.entry("Prifddinas Agility Course", 131395),
                Map.entry("Varrock Rooftop", 98373),
                Map.entry("Dorgesh-Kaan Agility", 82758),
                Map.entry("Penguin Agility", 98373),
                Map.entry("Werewolf Agility", 131395),
                Map.entry("Agility Pyramid", 131395),
                Map.entry("Shayzien Basic Agility Course", 98373),
                Map.entry("Shayzien Advanced Agility Course", 98373),
                Map.entry("Colossal Wyrm Agility Course (Basic)", 0),
                Map.entry("Colossal Wyrm Agility Course (Advanced)", 0),
                //per ticket
                Map.entry("Agility Arena", 98373)
        );
    }

    public Map<String, StaticAction> getStaticActions() {
        return staticActions;
    }

    public void setStaticActions(Map<String, StaticAction> staticActions) {
        this.staticActions = staticActions;
    }
}
