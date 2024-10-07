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
                Map.entry("trawler", new StaticAction(5000, "fishing trawler"))
        );
    }
    @Override
    protected Map<String, Integer> initializeBaseRates() {
        return Map.<String, Integer>ofEntries(
                Map.entry("shrimps", 435165),
                Map.entry("anchovies", 435165),
                Map.entry("Karambwanji", 443697),
                Map.entry("sardine", 528000),
                Map.entry("herring", 528000),
                Map.entry("trout", 461808),
                Map.entry("salmon", 461808),
                Map.entry("pike", 305792),
                Map.entry("rainbow", 137739),
                Map.entry("tuna", 137739),
                Map.entry("swordfish", 137739),
                Map.entry("lobster", 116129),
                Map.entry("cave", 257770),
                Map.entry("slimy", 257770),
                Map.entry("monkfish", 138583),
                Map.entry("Karambwan", 170874),
                Map.entry("leaping", 426954),
                Map.entry("shark", 82243),
                Map.entry("infernal", 160000),
                Map.entry("anglerfish", 82758),
                //may or may not be static, there are conflicting sources pointed to on the wiki, most recent (from mod ash) is not static. Treating as if not static
                Map.entry("minnows", 131395),
                Map.entry("dark", 149434),
                Map.entry("sacred", 99000),
                Map.entry("mackerel", 382609),
                Map.entry("cod", 382609),
                Map.entry("bass", 382609),
                Map.entry("aerial", 636833),
                //camdozaal
                Map.entry("Raw", 257770)
        );
    }

    public Map<String, StaticAction> getStaticActions() {
        return staticActions;
    }

    public void setStaticActions(Map<String, StaticAction> staticActions) {
        this.staticActions = staticActions;
    }
}
