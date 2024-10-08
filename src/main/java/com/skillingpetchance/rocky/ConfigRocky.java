package com.skillingpetchance.rocky;

import com.skillingpetchance.AbstractConfig;

import java.util.Map;


public class ConfigRocky extends AbstractConfig {
    @Override
    protected Map<String, Integer> initializeBaseRates() {
        return Map.<String, Integer>ofEntries(
            Map.entry("COMMON PICKPOCKET", 257211),
            Map.entry("VEG STALL", 206777),
            Map.entry("VALUABLES", 206777),
            Map.entry("TZHAAR-HUR", 176743),
            Map.entry("WATCHMAN", 134625),
            Map.entry("PALADIN", 127056),
            Map.entry("BAKERY STALL", 124066),
            Map.entry("FRUIT STALL", 124066),
            Map.entry("GNOME", 108718),
            Map.entry("HERO", 99175),
            Map.entry("ELF", 99175),
            Map.entry("VYRE", 99175),
            Map.entry("SILK STALL", 68926),
            Map.entry("TEA STALL", 68926),
            Map.entry("MONKEY STALL", 47718),
            Map.entry("COMMON STALL", 36490),
            Map.entry("CHEST ROOM 1", 41355),
            Map.entry("CHEST ROOM 2", 29540),
            Map.entry("CHEST ROOM 3", 25847),
            Map.entry("CHEST ROOM 4", 20678),
            Map.entry("CHEST ROOM 5", 20678),
            Map.entry("CHEST ROOM 6", 20678),
            Map.entry("SQUIRK", 10339),
            Map.entry("CHEST ROOM 7", 10339),
            Map.entry("CHEST ROOM 8", 6893),
            Map.entry("WEALTHY", 0)
        );
    }
}
