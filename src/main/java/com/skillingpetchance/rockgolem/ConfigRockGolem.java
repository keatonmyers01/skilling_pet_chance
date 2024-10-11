package com.skillingpetchance.rockgolem;

import com.skillingpetchance.AbstractConfig;

import java.util.Map;


public class ConfigRockGolem extends AbstractConfig {
    @Override
    protected Map<String, Integer> initializeBaseRates() {
        return Map.<String, Integer>ofEntries(
            Map.entry("COMMON ORE", 741600),
            Map.entry("VOLCANIC SULPHUR", 710000),
            Map.entry("STARDUST", 521550),
            Map.entry("GOLD", 296640),
            Map.entry("COAL", 290640),
            Map.entry("PAY-DIRT", 247200),
            Map.entry("LOVAKITE", 245526),
            Map.entry("GEM", 211886),
            Map.entry("MITHRIL", 148320),
            Map.entry("BLAST MINE", 123600),
            //on fragment mined not minigame completion
            Map.entry("VOLCANIC MINE", 60000),
            Map.entry("ADAMANTITE", 59328),
            Map.entry("AMETHYST", 46350),
            Map.entry("RUNITE", 42377)
        );
    }
}
