package com.kitp13.transfigured.modifiers.lib;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public abstract class BooleanModifier implements Modifier {
    @Override
    public String getName() {
        return "";
    }

    @Override
    public void onMine(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity, Random random) {
        Modifier.super.onMine(stack, level, state, pos, entity, random);
    }

    @Override
    public void onAttack(ItemStack stack, LivingEntity target, LivingEntity attacker, Random random) {
        Modifier.super.onAttack(stack, target, attacker, random);
    }
}
