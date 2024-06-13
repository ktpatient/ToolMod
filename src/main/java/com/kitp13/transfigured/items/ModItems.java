package com.kitp13.transfigured.items;

import com.kitp13.transfigured.Transfigured;
import com.kitp13.transfigured.items.tiers.Tiers;
import com.kitp13.transfigured.items.tools.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Transfigured.MOD_ID);

    public static RegistryObject<Item> PAXEL_AXE = ITEMS.register("paxel_axe", () -> new PaxelAxe(5.0f,-3.0f, Tiers.TEST_TIER,null,new Item.Properties()));
    public static RegistryObject<Item> PAXEL_PAVEL = ITEMS.register("paxel_pavel", () -> new PaxelPavel(5.0f,-3.0f, Tiers.TEST_TIER,null,new Item.Properties()));
    public static RegistryObject<Item> PAXEL_PAXE = ITEMS.register("paxel_paxe", () -> new PaxelPaxe(5.0f,-3.0f, Tiers.TEST_TIER,null,new Item.Properties()));
    public static RegistryObject<Item> PAXEL_PAXEL = ITEMS.register("paxel_paxel", () -> new PaxelPaxel(5.0f,-3.0f, Tiers.TEST_TIER,null,new Item.Properties()));
    public static RegistryObject<Item> PAXEL_PICKAXE = ITEMS.register("paxel_pickaxe", () -> new PaxelPickaxe(5.0f,-3.0f, Tiers.TEST_TIER,null,new Item.Properties()));
    public static RegistryObject<Item> PAXEL_SHAXE = ITEMS.register("paxel_shaxe", () -> new PaxelShaxe(5.0f,-3.0f, Tiers.TEST_TIER,null,new Item.Properties()));
    public static RegistryObject<Item> PAXEL_SHOVEL = ITEMS.register("paxel_shovel", () -> new PaxelShovel(5.0f,-3.0f, Tiers.TEST_TIER,null,new Item.Properties()));

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
