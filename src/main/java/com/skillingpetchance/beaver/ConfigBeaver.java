package com.skillingpetchance.beaver;

import com.skillingpetchance.AbstractConfig;

import java.util.Map;

import static java.util.Map.entry;

public class ConfigBeaver extends AbstractConfig{
    @Override
    protected Map<String, Integer> initializeBaseRates() {
        return Map.<String, Integer>ofEntries(
            entry("LOGS", 317647),
            entry("OAK", 361146),
            entry("WILLOW", 289286),
            entry("TEAK", 264366),
            entry("JUNIPER", 360000),
            entry("MAPLE", 221918),
            entry("BARK", 214367),
            entry("MAHOGANY", 220623),
            entry("ARCTIC", 145758),
            entry("YEW", 145013),
            entry("BLISTERWOOD", 289286),
            //do the numilite/fossils count for pet rate if so not tracking it rn.
            entry("MUSHROOMS", 343000),
            entry("MAGIC", 72321),
            entry("REDWOOD", 72321)
        );
    }
}
