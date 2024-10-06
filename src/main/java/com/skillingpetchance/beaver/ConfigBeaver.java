package com.skillingpetchance.beaver;

import com.skillingpetchance.Action;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class ConfigBeaver {
    private Map<Integer, Map<String, Action>> actions;

    private transient final Map<String, Integer> baseRates = Map.ofEntries(
            entry("logs", 317647),
            entry("oak", 361146),
            entry("willow", 289286),
            entry("teak", 264366),
            entry("juniper", 360000),
            entry("maple", 221918),
            entry("bark", 214367),
            entry("mahogany", 220623),
            entry("arctic", 145758),
            entry("yew", 145013),
            entry("blisterwood", 289286),
            entry("mushrooms", 343000),
            entry("magic", 72321),
            entry("redwood", 72321)
    );


    public ConfigBeaver(){
        actions = new HashMap<>();
    }

    public Map<Integer, Map<String, Action>> getActions() {
        return actions;
    }

    public void setActions(Map<Integer, Map<String, Action>> actions) {
        this.actions = actions;
    }

    public Map<String, Integer> getBaseRates() {
        return baseRates;
    }

    //rates are transient to make storage take up less space in the config, but I want to have the rate stored with the
    //entry in the object so it doesn't need to be calculated each time the math is done.
    public void setRates(){
        Map<String, Action> actionsAtLevel;
        for (var level : actions.keySet()) {
            actionsAtLevel= actions.get(level);
            for(var entry : actionsAtLevel.keySet()){
                actionsAtLevel.get(entry).calculateRate(
                        baseRates.get(entry)
                );
            }
        }
    }

    @Override
    public String toString() {
        return "ConfigBeaver{" +
                "actions=" + actions +
                '}';
    }
}
