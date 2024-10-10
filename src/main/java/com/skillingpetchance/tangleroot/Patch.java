package com.skillingpetchance.tangleroot;

import net.runelite.api.GameObject;

public class Patch {
    GameObject patch;
    int state;
    boolean justSpawned;

    public Patch(GameObject patch, int state, boolean justSpawned) {
        this.patch = patch;
        this.state = state;
        this.justSpawned = justSpawned;
    }

    public GameObject getPatch() {
        return patch;
    }

    public void setPatch(GameObject patch) {
        this.patch = patch;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isJustSpawned() {
        return justSpawned;
    }

    public void setJustSpawned(boolean justSpawned) {
        this.justSpawned = justSpawned;
    }
}
