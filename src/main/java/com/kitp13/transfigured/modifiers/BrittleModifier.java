package com.kitp13.transfigured.modifiers;

import com.kitp13.transfigured.modifiers.lib.BooleanModifier;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class BrittleModifier extends BooleanModifier {
    public static final String NAME = "Brittle";

    @Override
    public MutableComponent tooltip(ItemStack stack) {
        return Component.literal("Brittle").withStyle(ChatFormatting.GREEN);
    }

    @Override
    public MutableComponent shiftTooltip(ItemStack stack) {
        return Component.literal("Takes extra damage than normal, when breaking blocks").withStyle(ChatFormatting.AQUA);
    }

    @Override
    public void onMine(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity, Random random) {
        stack.hurtAndBreak(3,entity, (e) -> e.broadcastBreakEvent(e.getUsedItemHand()));
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public ItemLike getApplyItem() {
        return Items.BONE;
    }
}
