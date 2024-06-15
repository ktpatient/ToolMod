package com.kitp13.transfigured.items;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;

import javax.tools.Tool;

public enum ToolCapabilities {
    PICKAXE(1,"Pickaxe"), // 0001
    AXE(2,"Axe"),         // 0010
    SHOVEL(4, "Shovel");  // 0100

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
    public static ToolCapabilities fromTool(ItemStack stack){
        if (stack.getItem() instanceof PickaxeItem) {return PICKAXE;}
        if (stack.getItem() instanceof AxeItem) {return AXE;}
        if (stack.getItem() instanceof ShovelItem) {return SHOVEL;}
        return PICKAXE;
    }
}
