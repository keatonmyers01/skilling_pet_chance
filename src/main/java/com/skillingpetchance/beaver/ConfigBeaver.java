package com.skillingpetchance.beaver;

import com.skillingpetchance.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigBeaver {
    private List<Map<String, Action>> actions;

    private Map<String, Action> generateInitMap(int level){
        Map<String, Action> initMap = new HashMap<String, Action>();
        initMap.put("logs", new Action(level, 317647, "logs")); //
        if(level < 10) return initMap;
        initMap.put("oak", new Action(level, 361146, "oak"));//
        if(level < 25) return initMap;
        initMap.put("willow", new Action(level, 289286, "willow"));//
        if(level < 30) return initMap;
        initMap.put("teak", new Action(level, 264366, "teak")); //
        if(level < 37) return initMap;
        initMap.put("juniper", new Action(level, 360000, "juniper"));
        if(level < 40) return initMap;
        initMap.put("maple", new Action(level, 221918, "maple"));//
        initMap.put("bark", new Action(level, 214367, "bark"));//
        if(level < 45) return initMap;
        initMap.put("mahogany", new Action(level, 220623, "mahogany"));//
        if(level < 49) return initMap;
        initMap.put("artic", new Action(level, 145758, "artic"));
        if(level < 55) return initMap;
        initMap.put("yew", new Action(level, 145013, "yew"));//
        if(level < 57) return initMap;
        initMap.put("blisterwood", new Action(level, 289286, "blisterwood"));//
        if(level < 60) return initMap;
        initMap.put("mushrooms", new Action(level, 343000, "mushrooms"));
        if(level < 70) return initMap;
        initMap.put("magic", new Action(level, 72321, "magic"));//
        if(level < 85) return initMap;
        initMap.put("redwood", new Action(level, 72321, "redwood"));//
        return initMap;
    }

    public ConfigBeaver(){
        actions = new ArrayList<Map<String, Action>>();
        Map<String, Action> initMap;

        for(int i = 1; i <= 99; i++){
            initMap = generateInitMap(i);
            actions.add(initMap);
        }
    }

    public List<Map<String, Action>> getActions() {
        return actions;
    }

    public void setActions(List<Map<String, Action>> actions) {
        this.actions = actions;
    }
}
