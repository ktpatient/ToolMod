package com.kitp13.transfigured.items.tools.paxel.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class PaxelData {
    private final long data;

    public PaxelData (long data){
        this.data = data;
    }
    public PaxelData(int totalSockets, int usedSockets, int miningSpeedModifier, int totalRepairs, int usedRepairs, int durabilityModifier, boolean isBroken){
        totalSockets = Math.min(totalSockets,255);
        usedSockets = Math.min(usedSockets,255);
        totalRepairs = Math.min(totalRepairs,255);
        usedRepairs = Math.min(usedRepairs,255);
        miningSpeedModifier = Math.min(miningSpeedModifier,255);
        durabilityModifier = Math.min(durabilityModifier,65535);
        this.data = ((long) totalSockets << 56) | ((long) usedSockets << 48 | (long) miningSpeedModifier << 40 | (long) totalRepairs << 32 | (long) usedRepairs << 24 | (long) durabilityModifier << 8| (isBroken ? 1L : 0L));
    }
    public int getDurabilityModifier() {
        return (int) ((data >> 8) & 0xFFFF);
    }
    public int getTotalRepairs(){
        return (int) ((data >> 32) & 0xFF);
    }
    public int getUsedRepairs(){
        return (int) ((data >> 24) & 0xFF);
    }
    public int getMiningSpeedModifier(){
        return (int) ((data >> 40) & 0xFF);
    }
    public int getTotalSockets() {
        return (int) ((data >> 56) & 0xFF);
    }

    public int getUsedSockets() {
        return (int) ((data >> 48) & 0xFF);
    }
    public long getData() {
        return data;
    }
    public boolean isBroken() {
        return (data & 0x01) == 1;
    }




    private static final String DATA_KEY = "tool_data";

    public static PaxelData getToolData(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return new PaxelData(nbt.getLong(DATA_KEY));
    }

    public static void setToolData(ItemStack stack, PaxelData data) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putLong(DATA_KEY, data.getData());
    }

    public static int getMiningSpeedModifier(ItemStack stack) {
        PaxelData data = getToolData(stack);
        return data.getMiningSpeedModifier();
    }

    public static int getDurabilityModifier(ItemStack stack) {
        PaxelData data = getToolData(stack);
        return data.getDurabilityModifier();
    }

    public static int getSockets(ItemStack stack) {
        PaxelData data = getToolData(stack);
        return data.getTotalSockets() - data.getUsedSockets();
    }
    public static int getUsedSockets(ItemStack stack){
        PaxelData data = getToolData(stack);
        return data.getUsedSockets();
    }
    public static void setSockets(ItemStack stack, int sockets) {
        PaxelData data = getToolData(stack);
        PaxelData newData = new PaxelData(data.getTotalSockets(), sockets, data.getMiningSpeedModifier(), data.getTotalRepairs(),data.getUsedRepairs(), data.getDurabilityModifier(), data.isBroken());
        setToolData(stack,newData);
    }
    public static void setRepairSlots(ItemStack stack, int val) {
        PaxelData data = getToolData(stack);
        PaxelData newData = new PaxelData(data.getTotalSockets(), data.getUsedSockets(), data.getMiningSpeedModifier(), data.getTotalRepairs(),val, data.getDurabilityModifier(), data.isBroken());
        setToolData(stack,newData);
    }
    public static void setModifiedDurability(ItemStack stack, int dura) {
        PaxelData data = getToolData(stack);
        PaxelData newData = new PaxelData(data.getTotalSockets(), data.getUsedSockets(), data.getMiningSpeedModifier(), data.getTotalRepairs(),data.getUsedRepairs(), dura, data.isBroken());
        setToolData(stack,newData);
    }
    public static void addMiningSpeed(ItemStack stack, int speed) {
        PaxelData data = getToolData(stack);
        PaxelData newData = new PaxelData(data.getTotalSockets(), data.getUsedSockets(), data.getMiningSpeedModifier()+speed, data.getTotalRepairs(),data.getUsedRepairs(), data.getDurabilityModifier(), data.isBroken());
        setToolData(stack,newData);
    }
}
