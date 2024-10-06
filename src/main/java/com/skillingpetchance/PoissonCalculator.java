package com.skillingpetchance;

import java.util.Map;

public class PoissonCalculator {
    public double calculateSuccess(Map<Integer, Map<String, Action>> actions){
        double totalLambda = 0.0;
        Map<String, Action> actionsAtLevel;
        for (var level : actions.keySet()) {
            actionsAtLevel= actions.get(level);
            for(var entry : actionsAtLevel.keySet()){
                double rate = actionsAtLevel.get(entry).getRate();
                int quantity = actionsAtLevel.get(entry).getQuantity();
                totalLambda += rate * quantity;
            }
        }
        double probNoSuccessAll = Math.exp(-totalLambda);
        return 1 - probNoSuccessAll;
    }

}
