package com.kitp13.transfigured.events;

import com.kitp13.transfigured.Transfigured;
import com.kitp13.transfigured.items.ModItems;
import com.kitp13.transfigured.items.ToolCapabilities;
import com.kitp13.transfigured.items.tools.paxel.PaxelBase;
import com.kitp13.transfigured.items.tools.paxel.data.PaxelData;
import com.kitp13.transfigured.modifiers.lib.BooleanModifier;
import com.kitp13.transfigured.modifiers.lib.LeveledModifier;
import com.kitp13.transfigured.modifiers.lib.Modifier;
import com.kitp13.transfigured.modifiers.lib.ModifierRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.*;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

import static com.kitp13.transfigured.items.tools.paxel.data.PaxelData.*;

@Mod.EventBusSubscriber(modid = Transfigured.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class anvilEvent {
    public static Item getItemFromValue(Tier tier,int value){
        if (tier == Tiers.WOOD){
            Item[] arr = new Item[]{
                    null,
                    null,
                    null,
                    ModItems.WOODEN_PAXEL_PAXE.get(),
                    null,
                    ModItems.WOODEN_PAXEL_PAVEL.get(),
                    ModItems.WOODEN_PAXEL_SHAXE.get(),
                    ModItems.WOODEN_PAXEL_PAXEL.get(),
            };
            return arr[value];
        } else if (tier == Tiers.STONE){
            Item[] arr = new Item[]{
                    null,
                    null,
                    null,
                    ModItems.STONE_PAXEL_PAXE.get(),
                    null,
                    ModItems.STONE_PAXEL_PAVEL.get(),
                    ModItems.STONE_PAXEL_SHAXE.get(),
                    ModItems.STONE_PAXEL_PAXEL.get(),
            };
            return arr[value];
        }else if (tier == Tiers.IRON){
            Item[] arr = new Item[]{
                    null,
                    null,
                    null,
                    ModItems.IRON_PAXEL_PAXE.get(),
                    null,
                    ModItems.IRON_PAXEL_PAVEL.get(),
                    ModItems.IRON_PAXEL_SHAXE.get(),
                    ModItems.IRON_PAXEL_PAXEL.get(),
            };
            return arr[value];
        }else if (tier == Tiers.GOLD){
            Item[] arr = new Item[]{
                    null,
                    null,
                    null,
                    ModItems.GOLD_PAXEL_PAXE.get(),
                    null,
                    ModItems.GOLD_PAXEL_PAVEL.get(),
                    ModItems.GOLD_PAXEL_SHAXE.get(),
                    ModItems.GOLD_PAXEL_PAXEL.get(),
            };
            return arr[value];
        }else if (tier == Tiers.DIAMOND){
            Item[] arr = new Item[]{
                    null,
                    null,
                    null,
                    ModItems.DIAMOND_PAXEL_PAXE.get(),
                    null,
                    ModItems.DIAMOND_PAXEL_PAVEL.get(),
                    ModItems.DIAMOND_PAXEL_SHAXE.get(),
                    ModItems.DIAMOND_PAXEL_PAXEL.get(),
            };
            return arr[value];
        }else if (tier == Tiers.NETHERITE){
            Item[] arr = new Item[]{
                    null,
                    null,
                    null,
                    ModItems.NETHERITE_PAXEL_PAXE.get(),
                    null,
                    ModItems.NETHERITE_PAXEL_PAVEL.get(),
                    ModItems.NETHERITE_PAXEL_SHAXE.get(),
                    ModItems.NETHERITE_PAXEL_PAXEL.get(),
            };
            return arr[value];
        }
        Item[] arr = new Item[]{
                null,
                ModItems.PAXEL_PICKAXE.get(),
                ModItems.PAXEL_AXE.get(),
                ModItems.PAXEL_PAXE.get(),
                ModItems.PAXEL_SHOVEL.get(),
                ModItems.PAXEL_PAVEL.get(),
                ModItems.PAXEL_SHAXE.get(),
                ModItems.PAXEL_PAXEL.get(),
        };
        return arr[value];
    }
    public static ItemStack setDefault(ItemStack stack, int maxRepairs, int maxSockets){
        PaxelData newData = new PaxelData(maxSockets,0,0,maxRepairs,0,0,false);
        PaxelData.setToolData(stack,newData);
        return stack;
    }
    public static ItemStack copyItemStackWithNbt(ItemStack source, Item targetItem) {
        ItemStack target = new ItemStack(targetItem);

        if (source.hasTag()) {
            CompoundTag nbt = source.getTag();
            target.setTag(nbt.copy());
        }

        target.setCount(source.getCount());
        target.setDamageValue(source.getDamageValue());

        return target;
    }

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event){
        ItemStack leftStack = event.getLeft();
        ItemStack rightStack = event.getRight();
        if (rightStack.isEmpty()){
            return;
        }
        if (leftStack.getItem() instanceof TieredItem tieredItemLeft && rightStack.getItem() instanceof TieredItem tieredItemRight){
            if (tieredItemLeft.getTier() == tieredItemRight.getTier()){
                if (leftStack.getItem() instanceof PickaxeItem){
                    if (rightStack.getItem() instanceof AxeItem){
                        Item out = getItemFromValue(tieredItemLeft.getTier(),ToolCapabilities.combine(ToolCapabilities.PICKAXE,ToolCapabilities.AXE));
                        event.setOutput(setDefault(copyItemStackWithNbt(leftStack,out),tieredItemLeft.getTier().getLevel()+1,tieredItemLeft.getTier().getLevel()+1));
                        event.setCost(1);
                    }
                    if (rightStack.getItem() instanceof ShovelItem){
                        Item out = getItemFromValue(tieredItemLeft.getTier(),ToolCapabilities.combine(ToolCapabilities.PICKAXE,ToolCapabilities.SHOVEL));
                        event.setOutput(setDefault(copyItemStackWithNbt(leftStack,out),tieredItemLeft.getTier().getLevel()+1,tieredItemLeft.getTier().getLevel()+1));
                        event.setCost(1);
                    }
                }else if (leftStack.getItem() instanceof AxeItem){
                    if (rightStack.getItem() instanceof PickaxeItem){
                        Item out = getItemFromValue(tieredItemLeft.getTier(),ToolCapabilities.combine(ToolCapabilities.PICKAXE,ToolCapabilities.AXE));
                        event.setOutput(setDefault(copyItemStackWithNbt(leftStack,out),tieredItemLeft.getTier().getLevel()+1,tieredItemLeft.getTier().getLevel()+1));
                        event.setCost(1);
                    }
                    if (rightStack.getItem() instanceof ShovelItem){
                        Item out = getItemFromValue(tieredItemLeft.getTier(),ToolCapabilities.combine(ToolCapabilities.PICKAXE,ToolCapabilities.AXE));
                        event.setOutput(setDefault(copyItemStackWithNbt(leftStack,out),tieredItemLeft.getTier().getLevel()+1,tieredItemLeft.getTier().getLevel()+1));
                        event.setCost(1);
                    }
                } else if (leftStack.getItem() instanceof ShovelItem){
                    if (rightStack.getItem() instanceof AxeItem){
                        Item out = getItemFromValue(tieredItemLeft.getTier(),ToolCapabilities.combine(ToolCapabilities.PICKAXE,ToolCapabilities.AXE));
                        event.setOutput(setDefault(copyItemStackWithNbt(leftStack,out),tieredItemLeft.getTier().getLevel()+1,tieredItemLeft.getTier().getLevel()+1));
                        event.setCost(1);
                    }
                    if (rightStack.getItem() instanceof PickaxeItem){
                        Item out = getItemFromValue(tieredItemLeft.getTier(),ToolCapabilities.combine(ToolCapabilities.PICKAXE,ToolCapabilities.AXE));
                        event.setOutput(setDefault(copyItemStackWithNbt(leftStack,out),tieredItemLeft.getTier().getLevel()+1,tieredItemLeft.getTier().getLevel()+1));
                        event.setCost(1);
                    }
                }
            }
        }
        if (leftStack.getItem() instanceof PaxelBase paxel){
            PaxelData data = getToolData(leftStack);
            if (paxel.getTier().getRepairIngredient().getItems().length>0){
                if (rightStack.getItem() == paxel.getTier().getRepairIngredient().getItems()[0].getItem() && data.getTotalRepairs()-data.getUsedRepairs()>0){
                    ItemStack output = leftStack.copy();
                    PaxelData newData = new PaxelData(data.getTotalSockets(), data.getUsedSockets(), data.getMiningSpeedModifier(), data.getTotalRepairs(),data.getUsedRepairs()+1, data.getDurabilityModifier(), false);
                    setToolData(output,newData);
                    output.setDamageValue(0);
                    event.setOutput(output);
                    event.setCost(1);
                    event.setMaterialCost(1);
                }
            }
            if (getSockets(leftStack)>0) {
                ToolCapabilities[] capabilities = paxel.worksAsTool();
                int running = 0;
                for (ToolCapabilities capability : capabilities) {
                    running += capability.getBit();
                }

                if (rightStack.getItem() instanceof PickaxeItem && !ToolCapabilities.contains(capabilities, ToolCapabilities.PICKAXE)) {
                    Item item = getItemFromValue(((PaxelBase) leftStack.getItem()).getTier(), running += ToolCapabilities.PICKAXE.getBit());
                    ItemStack output = copyItemStackWithNbt(leftStack,item);
                    setSockets(output, getUsedSockets(output) + 1);
                    event.setOutput(output);
                    event.setCost(1);
                }
                if (rightStack.getItem() instanceof ShovelItem && !ToolCapabilities.contains(capabilities, ToolCapabilities.SHOVEL)) {
                    Item item = getItemFromValue(((PaxelBase) leftStack.getItem()).getTier(),running += ToolCapabilities.SHOVEL.getBit());
                    ItemStack output = copyItemStackWithNbt(leftStack,item);
                    setSockets(output, getUsedSockets(output) + 1);
                    event.setOutput(output);
                    event.setCost(1);
                }
                if (rightStack.getItem() instanceof AxeItem && !ToolCapabilities.contains(capabilities, ToolCapabilities.AXE)) {
                    Item item = getItemFromValue(((PaxelBase) leftStack.getItem()).getTier(),running += ToolCapabilities.AXE.getBit());
                    ItemStack output = copyItemStackWithNbt(leftStack,item);
                    setSockets(output, getUsedSockets(output) + 1);
                    event.setOutput(output);
                    event.setCost(1);
                }
                for (Modifier modifier : ModifierRegistry.MODIFIERS_MAP.values()){
                    if (rightStack.getItem() == modifier.getApplyItem()){
                        if (modifier instanceof BooleanModifier){
                            if (!PaxelBase.hasModifier(leftStack,modifier.getName())){
                                ItemStack output = leftStack.copy();
                                setSockets(output, getUsedSockets(output) + 1);
                                PaxelBase.addModifier(output, modifier);
                                event.setOutput(output);
                                event.setCost(1);
                                event.setMaterialCost(1);
                            }
                        } else if (modifier instanceof LeveledModifier leveledModifier) {
                            ItemStack output = leftStack.copy();
                            setSockets(output, getUsedSockets(output) + 1);
                            int currentLevel = 0;
                            for (Modifier modifiera : PaxelBase.getModifiers(output)) {
                                if (Objects.equals(modifiera.getName(), leveledModifier.getName())) {
                                    currentLevel += leveledModifier.getLevel();
                                    PaxelBase.removeModifier(output, modifier);
                                }
                            }
                            leveledModifier.setLevel(currentLevel + 1);
                            PaxelBase.addModifier(output, leveledModifier.setLevel(currentLevel + 1));
                            event.setOutput(output);
                            event.setCost(1);
                            event.setMaterialCost(1);
                        }
                    }
                }
                if (rightStack.getItem() == ModItems.REPAIR_SLOT_DISCHARGER_ITEM.get()){
                    ItemStack output = leftStack.copy();
                    PaxelData.setRepairSlots(output,0);
                    setSockets(output, getUsedSockets(output) + 1);
                    event.setOutput(output);
                    event.setCost(1);
                    event.setMaterialCost(1);
                } else if (rightStack.getItem() == ModItems.MINING_SPEED_AUGMENT.get()){
                    ItemStack output = leftStack.copy();
                    PaxelData.addMiningSpeed(output,10);
                    setSockets(output, getUsedSockets(output) + 1);
                    event.setOutput(output);
                    event.setCost(1);
                    event.setMaterialCost(1);
                }
            }

        }
    }
}
