package com.skillingpetchance;

import com.skillingpetchance.rocky.ConfigRocky;

public interface TrackerInterface<T> {
     default void addEntry(int skillLevel, String actionPerformed) {
         throw new UnsupportedOperationException("This method is not implemented.");
     }

     default void addEntry(int skillLevel, String actionPerformed, int quantity){
         throw new UnsupportedOperationException("This method is not implemented.");
     }
     void loadFromConfig();
     void saveToConfig(T config);
     double getRate();
    }
