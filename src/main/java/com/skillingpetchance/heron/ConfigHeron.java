package com.skillingpetchance.heron;

import com.skillingpetchance.AbstractConfig;
import com.skillingpetchance.StaticAction;

import java.util.HashMap;
import java.util.Map;


public class ConfigHeron extends AbstractConfig {
    Map<String, StaticAction> staticActions;

    public ConfigHeron(){
        super();
        staticActions = Map.<String, StaticAction>ofEntries(
                //technically scales to contribution between 5000-2500 but going with the min contribution for now
                Map.entry("TRAWLER", new StaticAction(5000, "fishing trawler"))
        );
    }
    @Override
    protected Map<String, Integer> initializeBaseRates() {
        return Map.<String, Integer>ofEntries(
            Map.entry("SHRIMPS", 435165),
            Map.entry("ANCHOVIES", 435165),
            Map.entry("KARAMBWANJI", 443697),
            Map.entry("SARDINE", 528000),
            Map.entry("HERRING", 528000),
            Map.entry("TROUT", 461808),
            Map.entry("SALMON", 461808),
            Map.entry("PIKE", 305792),
            Map.entry("RAINBOW", 137739),
            Map.entry("TUNA", 137739),
            Map.entry("SWORDFISH", 137739),
            Map.entry("LOBSTER", 116129),
            Map.entry("CAVE", 257770),
            Map.entry("SLIMY", 257770),
            Map.entry("MONKFISH", 138583),
            Map.entry("KARAMBWAN", 170874),
            Map.entry("LEAPING", 426954),
            Map.entry("SHARK", 82243),
            Map.entry("INFERNAL", 160000),
            Map.entry("ANGLERFISH", 82758),
            Map.entry("MINNOWS", 131395),
            Map.entry("DARK", 149434),
            Map.entry("SACRED", 99000),
            Map.entry("MACKEREL", 382609),
            Map.entry("COD", 382609),
            Map.entry("BASS", 382609),
            Map.entry("AERIAL", 636833),
            Map.entry("RAW", 257770)
        );
    }

    public Map<String, StaticAction> getStaticActions() {
        return staticActions;
    }

    public void setStaticActions(Map<String, StaticAction> staticActions) {
        this.staticActions = staticActions;
    }
}
