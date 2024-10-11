package com.skillingpetchance.tangleroot;

import com.skillingpetchance.AbstractConfig;

import java.util.Map;


public class ConfigTangleroot extends AbstractConfig {
    @Override
    protected Map<String, Integer> initializeBaseRates() {
        return Map.<String, Integer>ofEntries(
            Map.entry("TITHE FARM", 7494389),
            Map.entry("GRAPES", 385426),
            Map.entry("LOW TIER PLANTS", 281040),
            Map.entry("SWEETCORN", 224832),
            Map.entry("LIMPWURT ROOT", 224832),
            Map.entry("STRAWBERRY", 187360),
            Map.entry("SNAPE GRASS", 173977),
            Map.entry("WATERMELON", 160594),
            Map.entry("POTATO CACTUS", 160594),
            Map.entry("BARLEY", 112416),
            Map.entry("HAMMERSTONE", 112416),
            Map.entry("HERB", 98364),
            Map.entry("ASGARNIAN", 89933),
            Map.entry("JUTE", 89933),
            Map.entry("YANILLIAN", 74944),
            Map.entry("KRANDORIAN", 64238),
            Map.entry("WILDBLOOD", 56208),
            Map.entry("REDBERRIES", 44966),
            Map.entry("CADAVA BERRIES", 37472),
            Map.entry("DWELLBERRIES", 32119),
            Map.entry("JANGERBERRIES", 28104),
            Map.entry("WHITE BERRIES", 28104),
            Map.entry("POISON IVY", 28104),
            Map.entry("OAK", 22483),
            Map.entry("WILLOW", 16059),
            Map.entry("MAPLE", 14052),
            Map.entry("YEW", 11242),
            Map.entry("MAGIC", 9368),
            Map.entry("FRUIT TREE", 9000),
            Map.entry("NIGHTSHADE", 8000),
            Map.entry("GIANT SEAWEED", 7500),
            Map.entry("MUSHROOM", 7500),
            Map.entry("CACTUS", 7000),
            Map.entry("HESPORI", 7000),
            Map.entry("CALQUAT", 6000),
            Map.entry("TEAK", 5000),
            Map.entry("MAHOGANY", 5000),
            Map.entry("SPIRIT", 5000),
            Map.entry("REDWOOD", 5000)
        );
    }


}
