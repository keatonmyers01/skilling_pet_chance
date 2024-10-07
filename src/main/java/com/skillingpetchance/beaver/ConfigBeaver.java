package com.skillingpetchance.beaver;

import com.skillingpetchance.AbstractConfig;

import java.util.Map;

import static java.util.Map.entry;

public class ConfigBeaver extends AbstractConfig{
    @Override
    protected Map<String, Integer> initializeBaseRates() {
        return Map.<String, Integer>ofEntries(
                entry("logs", 317647),
                entry("oak", 361146),
                entry("willow", 289286),
                entry("teak", 264366),
                entry("juniper", 360000),
                entry("maple", 221918),
                entry("bark", 214367),
                entry("mahogany", 220623),
                entry("arctic", 145758),
                entry("yew", 145013),
                entry("blisterwood", 289286),
                //do the numilite/fossils count for pet rate if so not tracking it rn.
                entry("mushrooms", 343000),
                entry("magic", 72321),
                entry("redwood", 72321)
        );
    }
}
