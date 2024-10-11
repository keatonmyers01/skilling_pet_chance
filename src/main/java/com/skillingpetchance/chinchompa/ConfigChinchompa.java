package com.skillingpetchance.chinchompa;

import com.skillingpetchance.AbstractConfig;

import java.util.Map;


public class ConfigChinchompa extends AbstractConfig {
    @Override
    protected Map<String, Integer> initializeBaseRates() {
        return Map.<String, Integer>ofEntries(
            Map.entry("CHINCHOMPA", 131395),
            Map.entry("CARNIVOROUS", 98373),
            Map.entry("BLACK", 82758)
        );
    }
}
