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
                Map.entry("FLOOR 1", new StaticAction(35000, "sepulchre floor 1")),
                Map.entry("FLOOR 2", new StaticAction(16000, "sepulchre floor 2")),
                Map.entry("FLOOR 3", new StaticAction(8000, "sepulchre floor 3")),
                Map.entry("FLOOR 4", new StaticAction(4000, "sepulchre floor 4")),
                Map.entry("FLOOR 5", new StaticAction(2000, "sepulchre floor 5"))
        );
    }

    @Override
    protected Map<String, Integer> initializeBaseRates() {
        return Map.<String, Integer>ofEntries(
            Map.entry("BARBARIAN OUTPOST", 131395),
            Map.entry("APE ATOLL AGILITY", 98373),
            Map.entry("CANIFIS ROOFTOP", 82758),
            Map.entry("GNOME STRONGHOLD AGILITY", 131395),
            Map.entry("SEERS' VILLAGE ROOFTOP", 98373),
            Map.entry("WILDERNESS AGILITY", 82758),
            Map.entry("ARDOUGNE ROOFTOP", 131395),
            Map.entry("POLLNIVNEACH ROOFTOP", 98373),
            Map.entry("DRAYNOR VILLAGE ROOFTOP", 82758),
            Map.entry("RELLEKKA ROOFTOP", 131395),
            Map.entry("FALADOR ROOFTOP", 82758),
            Map.entry("AL KHARID ROOFTOP", 131395),
            Map.entry("PRIFDDINAS AGILITY COURSE", 131395),
            Map.entry("VARROCK ROOFTOP", 98373),
            Map.entry("DORGESH-KAAN AGILITY", 82758),
            Map.entry("PENGUIN AGILITY", 98373),
            Map.entry("WEREWOLF AGILITY", 131395),
            Map.entry("AGILITY PYRAMID", 131395),
            Map.entry("SHAYZIEN BASIC AGILITY COURSE", 98373),
            Map.entry("SHAYZIEN ADVANCED AGILITY COURSE", 98373),
            Map.entry("COLOSSAL WYRM AGILITY COURSE (BASIC)", 28503),
            Map.entry("COLOSSAL WYRM AGILITY COURSE (ADVANCED)", 25406),
            Map.entry("AGILITY ARENA", 98373)
        );
    }

    public Map<String, StaticAction> getStaticActions() {
        return staticActions;
    }

    public void setStaticActions(Map<String, StaticAction> staticActions) {
        this.staticActions = staticActions;
    }
}
