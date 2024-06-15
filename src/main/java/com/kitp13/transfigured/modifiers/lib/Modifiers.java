package com.kitp13.transfigured.modifiers.lib;

import com.kitp13.transfigured.Transfigured;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Modifiers {
    private static final String MODIFIERS_KEY = "Modifiers";

    public static boolean hasModifier(ItemStack stack, String name){
        List<Modifier> modifiersList = getModifiers(stack);
        for (Modifier modifier : modifiersList){
            if (modifier.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    public static void addModifier(ItemStack stack, Modifier modifier) {
        CompoundTag tag = stack.getOrCreateTag();
        ListTag modifiersList = tag.getList(MODIFIERS_KEY, 10); // 10 for compound tag type
        CompoundTag modifierTag = new CompoundTag();
        if (modifier instanceof LeveledModifier leveledModifier) {
            modifierTag.putString("Type", leveledModifier.getName());
            modifierTag.putInt("Level", leveledModifier.getLevel());
        }
        else if (modifier instanceof BooleanModifier){
            modifierTag.putString("Type", modifier.getName());
        }
        else {
            Transfigured.LOGGER.error("Error passing in modifier {}", modifier.getName());
        }
        modifiersList.add(modifierTag);
        tag.put(MODIFIERS_KEY, modifiersList);
    }

    public static void removeModifier(ItemStack stack, Modifier modifier) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains(MODIFIERS_KEY)) {
            ListTag modifiersList = tag.getList(MODIFIERS_KEY, 10);
            ListTag newModifiersList = new ListTag();
            for (int i = 0; i < modifiersList.size(); i++) {
                CompoundTag modifierTag = modifiersList.getCompound(i);
                String type = modifierTag.getString("Type");
                if (!type.equals(modifier.getName())) {
                    newModifiersList.add(modifierTag);
                }
            }
            tag.put(MODIFIERS_KEY, newModifiersList);
        }
    }

    public static List<Modifier> getModifiers(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        List<Modifier> modifiers = new ArrayList<>();
        if (tag != null && tag.contains(MODIFIERS_KEY)) {
            ListTag modifiersList = tag.getList(MODIFIERS_KEY, 10);
            for (int i = 0; i < modifiersList.size(); i++) {
                CompoundTag modifierTag = modifiersList.getCompound(i);
                String type = modifierTag.getString("Type");

                if (ModifierRegistry.MODIFIERS_MAP.get(type) instanceof BooleanModifier){
                    modifiers.add(ModifierRegistry.MODIFIERS_MAP.get(type));
                } else if (ModifierRegistry.MODIFIERS_MAP.get(type) instanceof LeveledModifier modifier){
                    modifier.setLevel(modifierTag.getInt("Level"));
                    modifiers.add(modifier);
                }
            }
        }
        return modifiers;
    }
}
