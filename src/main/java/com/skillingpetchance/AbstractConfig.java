package com.skillingpetchance;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConfig {
    private Map<Integer, Map<String, Action>> actions;
    private transient Map<String, Integer> baseRates;

    public AbstractConfig() {
        this.actions = new HashMap<>();
        this.baseRates = initializeBaseRates();
    }

    protected abstract Map<String, Integer> initializeBaseRates();

    public Map<Integer, Map<String, Action>> getActions() {
        return actions;
    }

    public void setActions(Map<Integer, Map<String, Action>> actions) {
        this.actions = actions;
    }

    public Map<String, Integer> getBaseRates() {
        return baseRates;
    }

    public void setRates() {
        this.baseRates = initializeBaseRates();
        Map<String, Action> actionsAtLevel;
        for (var level : actions.keySet()) {
            actionsAtLevel = actions.get(level);
            for (var entry : actionsAtLevel.keySet()) {
                actionsAtLevel.get(entry).calculateRate(
                        baseRates.get(entry)
                );
            }
        }
    }

    @Override
    public String toString() {
        return "Config{" +
                "actions=" + actions +
                '}';
    }
}