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
    public static Item getItemFromValue(int value){
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
        if (leftStack.getItem() instanceof PaxelBase paxel){
            PaxelData data = getToolData(leftStack);
            if (rightStack.getItem() == Items.ANVIL && data.getTotalRepairs()-data.getUsedRepairs()>0&& data.isBroken()){
                ItemStack output = leftStack.copy();
                PaxelData newData = new PaxelData(data.getTotalSockets(), data.getUsedSockets(), data.getMiningSpeedModifier(), data.getTotalRepairs(),data.getUsedRepairs()+1, data.getDurabilityModifier(), false);
                setToolData(output,newData);
                event.setOutput(output);
                event.setCost(1);
                event.setMaterialCost(1);
            }
            if (getSockets(leftStack)>0) {
                ToolCapabilities[] capabilities = paxel.worksAsTool();
                int running = 0;
                for (ToolCapabilities capability : capabilities) {
                    running += capability.getBit();
                }

                if (rightStack.getItem() instanceof PickaxeItem && !ToolCapabilities.contains(capabilities, ToolCapabilities.PICKAXE)) {
                    Item item = getItemFromValue(running += ToolCapabilities.PICKAXE.getBit());
                    ItemStack output = copyItemStackWithNbt(leftStack,item);
                    setSockets(output, getUsedSockets(output) + 1);
                    event.setOutput(output);
                    event.setCost(1);
                }
                if (rightStack.getItem() instanceof ShovelItem && !ToolCapabilities.contains(capabilities, ToolCapabilities.SHOVEL)) {
                    Item item = getItemFromValue(running += ToolCapabilities.SHOVEL.getBit());
                    ItemStack output = copyItemStackWithNbt(leftStack,item);
                    setSockets(output, getUsedSockets(output) + 1);
                    event.setOutput(output);
                    event.setCost(1);
                }
                if (rightStack.getItem() instanceof AxeItem && !ToolCapabilities.contains(capabilities, ToolCapabilities.AXE)) {
                    Item item = getItemFromValue(running += ToolCapabilities.AXE.getBit());
                    ItemStack output = copyItemStackWithNbt(leftStack,item);
                    setSockets(output, getUsedSockets(output) + 1);
                    event.setOutput(output);
                    event.setCost(1);
                }
                for (Modifier modifier : ModifierRegistry.MODIFIERS_MAP.values()){
                    if (rightStack.getItem() == modifier.getApplyItem()){
                        if (modifier instanceof BooleanModifier){
                            ItemStack output = leftStack.copy();
                            setSockets(output, getUsedSockets(output) + 1);
                            PaxelBase.addModifier(output, modifier);
                            event.setOutput(output);
                            event.setCost(1);
                            event.setMaterialCost(1);
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
            }

        }
    }
}
