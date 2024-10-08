package com.skillingpetchance.riftguardian;

import com.skillingpetchance.AbstractConfig;

import java.util.Map;


public class ConfigRiftGuardian extends AbstractConfig {
    @Override
    protected Map<String, Integer> initializeBaseRates() {
        return Map.<String, Integer>ofEntries(
            Map.entry("ARCEUUS BLOOD", 804984),
            Map.entry("ARCEUUS SOUL", 782999),
            Map.entry("OURANIA", 1487213),
            Map.entry("OTHER", 1795758)
        );
    }
}
