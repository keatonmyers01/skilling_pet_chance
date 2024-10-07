package com.skillingpetchance.riftguardian;

import com.skillingpetchance.AbstractConfig;

import java.util.Map;


public class ConfigRiftGuardian extends AbstractConfig {
    @Override
    protected Map<String, Integer> initializeBaseRates() {
        return Map.<String, Integer>ofEntries(
                Map.entry("arceuus blood", 804984),
                Map.entry("arceuus soul", 782999),
                Map.entry("ourania", 1487213),
                Map.entry("other", 1795758)
        );
    }
}
