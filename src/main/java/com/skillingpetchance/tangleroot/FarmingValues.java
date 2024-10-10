package com.skillingpetchance.tangleroot;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FarmingValues {

    public static List<Integer> getFarmingRegions() {
        return Arrays.asList(
                10548, 11062, 6192, 12083, 4922, 15148, 6967, 14391, 13151,
                11058, 11317, 9781, 9265, 9777, 11056, 11828, 12594, 11573,
                12854, 14907, 14651, 6702, 10300, 6711, 12082, 10290, 12596,
                11570, 5421, 11060, 12851, 10551, 10288, 13622, 5021, 13106,
                15008
        );
    }

    //no redwood patch yet
    public static List<Integer> getFarmingPatches() {
        return Arrays.asList(
                7849, 7848, 50693, 7847, 33649, 27111, 7850, 34919, 8554,
                8555, 8552, 8553, 50696, 50695, 8550, 8551, 33694, 33693,
                21950, 27113, 27114, 8556, 8557, 34922, 34921, 7964, 7965,
                34007, 7962, 26579, 7963, 7807, 34629, 34906, 8389, 33732,
                19147, 8391, 8388, 8390, 30482, 30480, 30481, 50692, 8383,
                8382, 33733, 27116, 8338, 7580, 7577, 7579, 34006, 7578,
                55341, 8174, 8175, 8176, 8173, 8337, 34630,
                7771, 33761, 30500, 30501
        );
    }

    //States that happens after the plant is harvested or is checked depending on type of plant
    public static List<Integer> getTriggerStates(){
        return Arrays.asList(7840, 8573, 8207, // empty flower, allotment, and hops
                33747, 7757, //cactus plots
                7701, 7591, 7616, 7644, 7725, 7674, //berry plots
                8467, 8488, 8444, 8513, 8409, //tree
                8032, 7978, 8117, 8090, 34020, 34918, 33719, //fruit tree, crystal, and celastrus
                30483, 8311, //seaweed, mushrooms
                33725, 8355 //hespori, spirit
        );
    }

    //integer will be the pre harvest state
    public static Map<Integer, String> getStateToPlantType(){
        return Map.<Integer, String>ofEntries(
                Map.entry(8562, "LOW TIER PLANTS"), Map.entry(7871, "LOW TIER PLANTS"), Map.entry(8584, "LOW TIER PLANTS"),
                Map.entry(8539, "LOW TIER PLANTS"), Map.entry(7903, "LOW TIER PLANTS"), Map.entry(8645, "LOW TIER PLANTS"),
                Map.entry(7887, "LOW TIER PLANTS"), Map.entry(7923, "LOW TIER PLANTS"), Map.entry(33654, "LOW TIER PLANTS"),
                Map.entry(8624, "SWEETCORN"), Map.entry(7855, "LIMPWURT ROOT"), Map.entry(8601, "STRAWBERRY"),
                Map.entry(33673, "SNAPE GRASS"), Map.entry(8664, "WATERMELON"), Map.entry(33748, "POTATO CACTUS"),
                Map.entry(8196, "Barley"), Map.entry(8181, "Hammerstone"), Map.entry(-2, "Asgarnian"),
                Map.entry(-1, "Yanillian"), Map.entry(8243, "Jute"), Map.entry(-3, "Krandorian"),
                Map.entry(8265, "Wildblood"), Map.entry(7702, "redberries"), Map.entry(7592, "cadava berries"),
                Map.entry(7617, "dwellberries"), Map.entry(7645, "jangerberries"), Map.entry(7726, "white berries"),
                Map.entry(7675, "Poison ivy"), Map.entry(8466, "Oak"), Map.entry(8487, "Willow"),
                Map.entry(8443, "Maple"), Map.entry(8512, "Yew"), Map.entry(8408, "Magic"),
                Map.entry(-4, "FRUIT TREE"), Map.entry(-5, "FRUIT TREE"), Map.entry(-6, "FRUIT TREE"),
                Map.entry(8033, "FRUIT TREE"), Map.entry(7979, "FRUIT TREE"), Map.entry(8118, "FRUIT TREE"),
                Map.entry(8091, "FRUIT TREE"), Map.entry(34917, "FRUIT TREE"), Map.entry(34021, "FRUIT TREE"),
                Map.entry(33704, "FRUIT TREE"), Map.entry(30493, "giant seaweed"), Map.entry(8326, "mushroom"),
                Map.entry(7758, "cactus"), Map.entry(33730, "hespori"), Map.entry(-11, "calquat"),
                Map.entry(-7, "teak"), Map.entry(-8, "mahogany"), Map.entry(-9, "spirit"),
                Map.entry(-10, "redwood")
        );
    }

    //no redwood patch yet
    public static Map<Integer, Integer> getPatchToRegion() {
        return Map.<Integer, Integer>ofEntries(
                Map.entry(7849, 10548), Map.entry(7848, 11062), Map.entry(50693, 6192),
                Map.entry(7847, 12083), Map.entry(33649, 4922), Map.entry(27111, 6967),
                Map.entry(7850, 14391), Map.entry(34919, 13151), Map.entry(8554, 10548),
                Map.entry(8555, 10548), Map.entry(8552, 11062), Map.entry(8553, 11062),
                Map.entry(50696, 6192), Map.entry(50695, 6192), Map.entry(8550, 12083),
                Map.entry(8551, 12083), Map.entry(33694, 4922), Map.entry(33693, 4922),
                Map.entry(21950, 15148), Map.entry(27113, 6967), Map.entry(27114, 6967),
                Map.entry(8556, 14391), Map.entry(8557, 14391), Map.entry(34922, 13151),
                Map.entry(34921, 13151), Map.entry(7964, 11058), Map.entry(7965, 11317),
                Map.entry(34007, 4922), Map.entry(7962, 9781), Map.entry(26579, 9265),
                Map.entry(7963, 9777), Map.entry(7807, 11056), Map.entry(34629, 4922),
                Map.entry(34906, 13151), Map.entry(8389, 11828), Map.entry(33732, 4922),
                Map.entry(19147, 9781), Map.entry(8391, 12594), Map.entry(8388, 11573),
                Map.entry(8390, 12854), Map.entry(30482, 14907), Map.entry(30480, 14651),
                Map.entry(30481, 14651), Map.entry(50692, 6702), Map.entry(8383, 11058),
                Map.entry(8382, 10300), Map.entry(33733, 4922), Map.entry(27116, 6711),
                Map.entry(8338, 12082), Map.entry(7580, 10290), Map.entry(7577, 12596),
                Map.entry(7579, 10300), Map.entry(34006, 4922), Map.entry(7578, 11570),
                Map.entry(55341, 5421), Map.entry(8174, 11060), Map.entry(8175, 12851),
                Map.entry(8176, 10551), Map.entry(8173, 10288), Map.entry(8337, 13622),
                Map.entry(34630, 5021), Map.entry(7771, 13106), Map.entry(33761, 4922),
                Map.entry(30500, 15008), Map.entry(30501, 15008)
        );
    }

}
