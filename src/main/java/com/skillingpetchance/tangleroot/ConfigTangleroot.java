package com.skillingpetchance.tangleroot;

import com.skillingpetchance.AbstractConfig;

import java.util.Map;


public class ConfigTangleroot extends AbstractConfig {
    @Override
    protected Map<String, Integer> initializeBaseRates() {
        return Map.<String, Integer>ofEntries(
                Map.entry("Tithe Farm", 7494389),
                Map.entry("Grapes", 385426),
                Map.entry("Potato", 281040),
                Map.entry("marigolds", 281040),
                Map.entry("onion", 281040),
                Map.entry("Cabbage", 281040),
                Map.entry("Rosemary", 281040),
                Map.entry("Tomato", 281040),
                Map.entry("Nasturtiums", 281040),
                Map.entry("Woad leaf", 281040),
                Map.entry("White lily", 281040),
                Map.entry("Sweetcorn", 224832),
                Map.entry("Limpwurt root", 224832),
                Map.entry("Strawberry", 187360),
                Map.entry("Snape grass", 173977),
                Map.entry("Watermelon", 160594),
                Map.entry("Potato cactus", 160594),
                Map.entry("Barley", 112416),
                Map.entry("Hammerstone", 112416),
                Map.entry("guam", 98364),
                Map.entry("marrentill", 98364),
                Map.entry("tarromin", 98364),
                Map.entry("harrlander", 98364),
                Map.entry("goutweed", 98364),
                Map.entry("rannar", 98364),
                Map.entry("toadflax", 98364),
                Map.entry("irit", 98364),
                Map.entry("avantoe", 98364),
                Map.entry("kwuarm", 98364),
                Map.entry("snapdragon", 98364),
                Map.entry("cadantine", 98364),
                Map.entry("lantadyme", 98364),
                Map.entry("dwarf weed", 98364),
                Map.entry("torstol", 98364),
                //Map.entry("herb", 98364), collapse herbs at some point, keep dif for dev atm
                Map.entry("Asgarnian", 89933),
                Map.entry("Yanillian", 74944),
                Map.entry("Krandorian", 64238),
                Map.entry("Wildblood", 56208),
                Map.entry("redberries", 44966),
                Map.entry("cadava berries", 37472),
                Map.entry("dwellberries", 32119),
                Map.entry("jangerberries", 28104),
                Map.entry("white berries", 28104),
                Map.entry("Poison ivy", 28104),
                Map.entry("Oak", 22483),
                Map.entry("Willow", 16059),
                Map.entry("Maple", 14052),
                Map.entry("Yew", 11242),
                Map.entry("Magic", 9368),
                //Map.entry("fruit tree", 9000), collapse fruit trees at some point, keep dif for dev atm
                Map.entry("apple", 9000),
                Map.entry("banana", 9000),
                Map.entry("orange", 9000),
                Map.entry("curry", 9000),
                Map.entry("pineapple", 9000),
                Map.entry("papaya", 9000),
                Map.entry("palm", 9000),
                Map.entry("crystal", 9000),
                Map.entry("dragonfruit", 9000),
                Map.entry("celastrus", 9000),
                Map.entry("nightshade", 8000),
                Map.entry("giant seaweed", 7500),
                Map.entry("mushroom", 7500),
                Map.entry("cactus", 7000),
                Map.entry("hespori", 7000),
                Map.entry("calquat", 6000),
                Map.entry("teak", 5000),
                Map.entry("mahogany", 5000),
                Map.entry("spirit", 5000),
                Map.entry("redwood", 5000)
        );
    }


}
