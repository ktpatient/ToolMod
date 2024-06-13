package com.kitp13.transfigured.modifiers.lib;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public interface Modifier {
    String getName();
    ItemLike getApplyItem();
    default MutableComponent tooltip(ItemStack stack){
        return Component.literal("");
    }
    default MutableComponent shiftTooltip(ItemStack stack){
        return Component.literal("");
    }
    default void onMine(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity, Random random){    }
    default void onAttack(ItemStack stack, LivingEntity target, LivingEntity attacker, Random random){}
}
