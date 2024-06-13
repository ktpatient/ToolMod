package com.kitp13.transfigured.items.tools.paxel;

import com.kitp13.transfigured.Transfigured;
import com.kitp13.transfigured.items.ToolCapabilities;
import com.kitp13.transfigured.modifiers.lib.BooleanModifier;
import com.kitp13.transfigured.modifiers.lib.LeveledModifier;
import com.kitp13.transfigured.modifiers.lib.Modifier;
import com.kitp13.transfigured.modifiers.lib.ModifierRegistry;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PaxelBase extends DiggerItem {
    private final Random random;
    private static final String SOCKET_KEY = "socket";
    // private static final String TOOL_CAPABILITIES_KEY = "ToolCapabilities";
    private static final String MINING_MODIFIER_KEY = "MiningModifier";
    private static final String DURABILITY_MODIFIER_KEY = "DurabilityModifier";
    private static final String MODIFIERS_KEY = "Modifiers";
    public PaxelBase(float damage, float attackSpeed, Tier tier, TagKey<Block> blockTagKey, Properties properties) {
        super(damage, attackSpeed, tier, blockTagKey, properties);
        random = new Random();
    }
    public boolean isCorrectTool(BlockState state){
        return false;
    }

    @Override
    public boolean isCorrectToolForDrops(@NotNull ItemStack stack, @NotNull BlockState state) {
        return isCorrectTool(state);
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
        return isCorrectTool(state)?this.speed + getMiningSpeedModifier(stack):1.0f;
    }

    public ToolCapabilities[] worksAsTool(){
        return new ToolCapabilities[0];
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.empty().append(Component.literal("Sockets: ")).append(Component.literal(""+getSockets(stack)).withStyle(ChatFormatting.BLUE)));
        tooltip.add(Component.empty().append(Component.literal("Mining Speed Modifier: ")).append(Component.literal(""+getMiningSpeedModifier(stack)).withStyle(ChatFormatting.BLUE)));
        tooltip.add(Component.empty().append(Component.literal("Durability Modifier: ")).append(Component.literal(""+getDurabilityModifier(stack)).withStyle(ChatFormatting.BLUE)));
        for (ToolCapabilities tool : worksAsTool()){
            tooltip.add(Component.empty().append(Component.literal("Works as Tool: ")).append(Component.literal(tool.getName()).withStyle(ChatFormatting.BLUE)));
        }
        for (Modifier modifier : getModifiers(stack)){
            tooltip.add(Component.empty().append(Component.literal("    ")).append(modifier.tooltip(stack)));
            if (Screen.hasShiftDown()){
                tooltip.add(modifier.shiftTooltip(stack));
            }
        }
    }
    public static int getSockets(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getInt(SOCKET_KEY);
    }

    public static void setSockets(ItemStack stack, int sockets) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putInt(SOCKET_KEY, sockets);
    }
    public static void setMiningSpeedModifier(ItemStack stack, float modifier) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putFloat(MINING_MODIFIER_KEY, modifier);
    }

    public static float getMiningSpeedModifier(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains(MINING_MODIFIER_KEY)) {
            return tag.getFloat(MINING_MODIFIER_KEY);
        }
        return 0.0F;
    }
    public static void setDurabilityModifier(ItemStack stack, int modifier) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt(DURABILITY_MODIFIER_KEY, modifier);
    }

    public static int getDurabilityModifier(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains(DURABILITY_MODIFIER_KEY)) {
            return tag.getInt(DURABILITY_MODIFIER_KEY);
        }
        return 0;
    }
    @Override
    public int getMaxDamage(ItemStack stack) {
        int original =  super.getMaxDamage(stack);
        int modifier = getDurabilityModifier(stack);
        return original + modifier;
    }


    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(),InputConstants.KEY_LSHIFT)){
            setSockets(player.getItemInHand(hand),(getSockets(player.getItemInHand(hand))+1));
        } else if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(),InputConstants.KEY_LCONTROL)) {
            setMiningSpeedModifier(player.getItemInHand(hand), (getMiningSpeedModifier(player.getItemInHand(hand)) + 1));
        }
        return super.use(level, player, hand);
    }
    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        for (Modifier modifiers : getModifiers(stack)){
            modifiers.onAttack(stack, target, attacker,random);
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity entity) {
        for (Modifier modifiers : getModifiers(stack)){
            modifiers.onMine(stack, level, state, pos, entity,random);
        }
        return super.mineBlock(stack, level, state, pos, entity);
    }
    public static boolean hasModifier(ItemStack stack,String name){
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
