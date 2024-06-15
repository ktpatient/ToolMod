package com.kitp13.transfigured.events;

import com.kitp13.transfigured.Transfigured;
import com.kitp13.transfigured.items.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Transfigured.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LootTableEvent {
    private static final ResourceLocation END_CITY_LOOT_TABLE = new ResourceLocation("minecraft", "chests/end_city_treasure");
    private static final ResourceLocation BURIED_TREASURE_LOOT_TABLE = new ResourceLocation("minecraft", "chests/buried_treasure");
    private static final ResourceLocation BLACKSMITH_LOOT_TABLE = new ResourceLocation("minecraft", "chests/village/village_blacksmith");

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        if (event.getName().equals(END_CITY_LOOT_TABLE)) {
            LootTable table = event.getTable();

            LootPool pool = LootPool.lootPool()
                    .name("repair_slot_pool")
                    .when(LootItemRandomChanceCondition.randomChance(0.05f))
                    .add(LootItem.lootTableItem(new ItemStack(ModItems.REPAIR_SLOT_DISCHARGER_ITEM.get()).getItem())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .build();

            LootPool pool2 = LootPool.lootPool()
                    .name("mining_speed_pool")
                    .when(LootItemRandomChanceCondition.randomChance(0.1f))
                    .add(LootItem.lootTableItem(new ItemStack(ModItems.MINING_SPEED_AUGMENT.get()).getItem())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .build();

            LootPool pool3 = LootPool.lootPool()
                    .name("mining_xp_pool")
                    .when(LootItemRandomChanceCondition.randomChance(0.1f))
                    .add(LootItem.lootTableItem(new ItemStack(ModItems.MINING_XP_MODIFIER_ITEM.get()).getItem())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .build();
            LootPool pool4 = LootPool.lootPool()
                    .name("mining_durability_pool")
                    .when(LootItemRandomChanceCondition.randomChance(0.1f))
                    .add(LootItem.lootTableItem(new ItemStack(ModItems.MINING_DURABILITY_MODIFIER_ITEM.get()).getItem())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .build();

            table.addPool(pool);
            table.addPool(pool2);
            table.addPool(pool3);
            table.addPool(pool4);
        } else if (event.getName().equals(BLACKSMITH_LOOT_TABLE)){
            LootTable table = event.getTable();

            LootPool pool = LootPool.lootPool()
                    .name("repair_slot_pool")
                    .when(LootItemRandomChanceCondition.randomChance(0.05f))
                    .add(LootItem.lootTableItem(new ItemStack(ModItems.REPAIR_SLOT_DISCHARGER_ITEM.get()).getItem())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .build();

            LootPool pool2 = LootPool.lootPool()
                    .name("mining_speed_pool")
                    .when(LootItemRandomChanceCondition.randomChance(0.35f))
                    .add(LootItem.lootTableItem(new ItemStack(ModItems.MINING_SPEED_AUGMENT.get()).getItem())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .build();

            LootPool pool3 = LootPool.lootPool()
                    .name("mining_xp_pool")
                    .when(LootItemRandomChanceCondition.randomChance(0.35f))
                    .add(LootItem.lootTableItem(new ItemStack(ModItems.MINING_XP_MODIFIER_ITEM.get()).getItem())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .build();
            LootPool pool4 = LootPool.lootPool()
                    .name("mining_durability_pool")
                    .when(LootItemRandomChanceCondition.randomChance(0.35f))
                    .add(LootItem.lootTableItem(new ItemStack(ModItems.MINING_DURABILITY_MODIFIER_ITEM.get()).getItem())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .build();

            table.addPool(pool);
            table.addPool(pool2);
            table.addPool(pool3);
            table.addPool(pool4);
        } else if (event.getName().equals(BURIED_TREASURE_LOOT_TABLE)){
            LootTable table = event.getTable();

            LootPool pool = LootPool.lootPool()
                    .name("repair_slot_pool")
                    .when(LootItemRandomChanceCondition.randomChance(0.1f))
                    .add(LootItem.lootTableItem(new ItemStack(ModItems.REPAIR_SLOT_DISCHARGER_ITEM.get()).getItem())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .build();

            LootPool pool2 = LootPool.lootPool()
                    .name("mining_speed_pool")
                    .when(LootItemRandomChanceCondition.randomChance(0.25f))
                    .add(LootItem.lootTableItem(new ItemStack(ModItems.MINING_SPEED_AUGMENT.get()).getItem())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .build();

            LootPool pool3 = LootPool.lootPool()
                    .name("mining_xp_pool")
                    .when(LootItemRandomChanceCondition.randomChance(0.25f))
                    .add(LootItem.lootTableItem(new ItemStack(ModItems.MINING_XP_MODIFIER_ITEM.get()).getItem())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .build();
            LootPool pool4 = LootPool.lootPool()
                    .name("mining_durability_pool")
                    .when(LootItemRandomChanceCondition.randomChance(0.25f))
                    .add(LootItem.lootTableItem(new ItemStack(ModItems.MINING_DURABILITY_MODIFIER_ITEM.get()).getItem())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .build();

            table.addPool(pool);
            table.addPool(pool2);
            table.addPool(pool3);
            table.addPool(pool4);
        }
    }

}
