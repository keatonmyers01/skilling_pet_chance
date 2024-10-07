package com.skillingpetchance.rockgolem;

import com.skillingpetchance.AbstractConfig;

import java.util.Map;


public class ConfigRockGolem extends AbstractConfig {
    @Override
    protected Map<String, Integer> initializeBaseRates() {
        return Map.<String, Integer>ofEntries(
                Map.entry("common ore", 741600),
                Map.entry("volcanic sulphur", 710000),
                Map.entry("stardust", 521550),
                Map.entry("gold", 296640),
                Map.entry("coal", 290640),
                Map.entry("pay-dirt", 247200),
                Map.entry("lovakite", 245526),
                Map.entry("gem", 211886),
                Map.entry("mithril", 148320),
                Map.entry("blast mine ", 123600),
                //on fragment mined not minigame completion
                Map.entry("volcanic mine", 60000),
                Map.entry("adamantite rocks", 59328),
                Map.entry("amethyst", 46350),
                Map.entry("runite", 42377)
        );
    }
}
