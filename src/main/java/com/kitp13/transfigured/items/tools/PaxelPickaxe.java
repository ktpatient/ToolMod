package com.kitp13.transfigured.items.tools;

import com.kitp13.transfigured.items.ToolCapabilities;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PaxelPickaxe extends PaxelBase {
    public static final ToolCapabilities[] Capabilities = new ToolCapabilities[]{ToolCapabilities.PICKAXE};

    public PaxelPickaxe(float damage, float attackSpeed, Tier tier, TagKey<Block> blockTagKey, Properties properties) {
        super(damage, attackSpeed, tier, blockTagKey, properties);
    }

    @Override
    public boolean isCorrectTool(BlockState state) {
        return state.is(BlockTags.MINEABLE_WITH_PICKAXE);
    }
    @Override
    public ToolCapabilities[] worksAsTool() {
        return Capabilities;
    }
}
