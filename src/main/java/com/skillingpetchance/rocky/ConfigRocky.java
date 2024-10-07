package com.skillingpetchance.rocky;

import com.skillingpetchance.AbstractConfig;

import java.util.Map;


public class ConfigRocky extends AbstractConfig {
    @Override
    protected Map<String, Integer> initializeBaseRates() {
        return Map.<String, Integer>ofEntries(
                Map.entry("common pickpocket", 257211),
                Map.entry("Veg stall", 206777),
                Map.entry("valuables", 206777),
                Map.entry("TzHaar-Hur", 176743),
                Map.entry("watchman", 134625),
                Map.entry("paladin", 127056),
                Map.entry("Bakery Stall", 124066),
                Map.entry("Fruit Stall", 124066),
                Map.entry("gnome", 108718),
                Map.entry("hero", 99175),
                Map.entry("elf", 99175),
                Map.entry("vyre", 99175),
                Map.entry("silk Stall", 68926),
                Map.entry("tea Stall", 68926),
                Map.entry("Crafting stall", 47718),
                Map.entry("General stall", 47718),
                Map.entry("Food Stall", 47718),
                Map.entry("Grand Gold Chest(room 1)", 41355),
                Map.entry("Common stall", 36490),
                Map.entry("Grand Gold Chest(room 2)", 29540),
                Map.entry("Grand Gold Chest(room 3)", 25847),
                Map.entry("Grand Gold Chest(room 4)", 20678),
                Map.entry("Grand Gold Chest(room 5)", 20678),
                Map.entry("Grand Gold Chest(room 6)", 20678),
                Map.entry("squirk", 10339),
                Map.entry("Grand Gold Chest(room 7)", 10339),
                Map.entry("Grand Gold Chest(room 8)", 6893),
                Map.entry("wealthy", 0)
        );
    }
}
