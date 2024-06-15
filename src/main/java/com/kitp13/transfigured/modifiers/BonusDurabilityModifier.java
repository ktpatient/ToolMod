package com.kitp13.transfigured.modifiers;

import com.kitp13.transfigured.items.tools.paxel.data.PaxelData;
import com.kitp13.transfigured.modifiers.lib.BooleanModifier;
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

import java.util.Random;

public class BonusDurabilityModifier extends BooleanModifier {
    public static final String NAME = "BonusDura";
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public ItemLike getApplyItem() {
        return Items.ANVIL;
    }

    @Override
    public void onMine(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity, Random random) {
        int rand = random.nextInt(10);
        // Transfigured.LOGGER.info(rand);
        if (rand>=9){
            // ExperienceOrb.award((ServerLevel) level,pos.getCenter(),level);
            PaxelData.setModifiedDurability(stack, PaxelData.getDurabilityModifier(stack) + 1);
        }
    }

    @Override
    public MutableComponent tooltip(ItemStack stack) {
        return Component.literal("Bonus Durability");
    }

    @Override
    public MutableComponent shiftTooltip(ItemStack stack) {
        return Component.literal("Chance for the tool to gain additional max durability when Mining").withStyle(ChatFormatting.AQUA);
    }
}
