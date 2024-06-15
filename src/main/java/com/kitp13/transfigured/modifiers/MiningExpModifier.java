package com.kitp13.transfigured.modifiers;

import com.kitp13.transfigured.Transfigured;
import com.kitp13.transfigured.modifiers.lib.LeveledModifier;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.checkerframework.checker.units.qual.C;

import java.util.Random;

public class MiningExpModifier extends LeveledModifier {
    public static final String NAME = "MiningExp";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public ItemLike getApplyItem() {
        return Items.EXPERIENCE_BOTTLE;
    }

    @Override
    public MutableComponent tooltip(ItemStack stack) {
        return Component.literal(NAME);
    }

    @Override
    public MutableComponent shiftTooltip(ItemStack stack) {
        return Component.empty().append(this.tooltip(stack)).append(Component.literal(" Shift Description ").withStyle(ChatFormatting.AQUA)).append(Component.literal(""+getLevel()));
    }

    @Override
    public void onMine(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity, Random random) {
        int rand = random.nextInt(10);
        // Transfigured.LOGGER.info(rand);
        if (rand>=8){
            ExperienceOrb.award((ServerLevel) level,pos.getCenter(),getLevel());
        }
        super.onMine(stack, level, state, pos, entity, random);
    }
}
