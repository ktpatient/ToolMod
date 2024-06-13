package com.kitp13.transfigured.items;

import javax.tools.Tool;

public enum ToolCapabilities {
    PICKAXE(1,"pickaxe"), // 0001
    AXE(2,"axe"),         // 0010
    SHOVEL(4, "shovel");  // 0100

    private final int bit;
    private final String name;

    ToolCapabilities(int bit, String name) {
        this.bit = bit;
        this.name = name;
    }

    public int getBit() {
        return bit;
    }

    public String getName() {
        return name;
    }
    public static int combine(ToolCapabilities... capabilities) {
        int combined = 0;
        for (ToolCapabilities capability : capabilities) {
            combined |= capability.getBit();
        }
        return combined;
    }

    public static boolean hasCapability(int combined, ToolCapabilities capability) {
        return (combined & capability.getBit()) != 0;
    }
    public static boolean contains(ToolCapabilities[] arr, ToolCapabilities checkVal){
        for (ToolCapabilities cap : arr){
            if (cap == checkVal) {return true; }
        }
        return false;
    }
}
