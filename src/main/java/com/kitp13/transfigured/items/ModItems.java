package com.kitp13.transfigured.items;

import com.kitp13.transfigured.Transfigured;
import com.kitp13.transfigured.items.tiers.Tiers;
import com.kitp13.transfigured.items.tools.paxel.*;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Transfigured.MOD_ID);

    public static RegistryObject<Item> MINING_DURABILITY_MODIFIER_ITEM = ITEMS.register("mining_durability_modifier", ()->new Item(new Item.Properties())) ;
    public static RegistryObject<Item> MINING_SPEED_AUGMENT = ITEMS.register("mining_speed_augment", ()->new Item(new Item.Properties())) ;
    public static RegistryObject<Item> MINING_XP_MODIFIER_ITEM = ITEMS.register("mining_xp_modifier", ()->new Item(new Item.Properties())) ;
    public static RegistryObject<Item> REPAIR_SLOT_DISCHARGER_ITEM = ITEMS.register("repair_slot_discharger", ()->new Item(new Item.Properties())) ;

    public static RegistryObject<Item> PAXEL_AXE = ITEMS.register("paxel_axe", () -> new PaxelAxe(5.0f,-3.0f, Tiers.TEST_TIER,null,new Item.Properties()));
    public static RegistryObject<Item> PAXEL_PAVEL = ITEMS.register("paxel_pavel", () -> new PaxelPavel(5.0f,-3.0f, Tiers.TEST_TIER,null,new Item.Properties()));
    public static RegistryObject<Item> PAXEL_PAXE = ITEMS.register("paxel_paxe", () -> new PaxelPaxe(5.0f,-3.0f, Tiers.TEST_TIER,null,new Item.Properties()));
    public static RegistryObject<Item> PAXEL_PAXEL = ITEMS.register("paxel_paxel", () -> new PaxelPaxel(5.0f,-3.0f, Tiers.TEST_TIER,null,new Item.Properties()));
    public static RegistryObject<Item> PAXEL_PICKAXE = ITEMS.register("paxel_pickaxe", () -> new PaxelPickaxe(5.0f,-3.0f, Tiers.TEST_TIER,null,new Item.Properties()));
    public static RegistryObject<Item> PAXEL_SHAXE = ITEMS.register("paxel_shaxe", () -> new PaxelShaxe(5.0f,-3.0f, Tiers.TEST_TIER,null,new Item.Properties()));
    public static RegistryObject<Item> PAXEL_SHOVEL = ITEMS.register("paxel_shovel", () -> new PaxelShovel(5.0f,-3.0f, Tiers.TEST_TIER,null,new Item.Properties()));

    public static RegistryObject<Item> WOODEN_PAXEL_PAVEL = ITEMS.register("wooden_paxel_pavel", () -> new PaxelPavel(5.0f,-3.0f, net.minecraft.world.item.Tiers.WOOD,null,new Item.Properties()));
    public static RegistryObject<Item> WOODEN_PAXEL_PAXE = ITEMS.register("wooden_paxel_paxe", () -> new PaxelPaxe(5.0f,-3.0f, net.minecraft.world.item.Tiers.WOOD,null,new Item.Properties()));
    public static RegistryObject<Item> WOODEN_PAXEL_PAXEL = ITEMS.register("wooden_paxel_paxel", () -> new PaxelPaxel(5.0f,-3.0f, net.minecraft.world.item.Tiers.WOOD,null,new Item.Properties()));
    public static RegistryObject<Item> WOODEN_PAXEL_SHAXE = ITEMS.register("wooden_paxel_shaxe", () -> new PaxelShaxe(5.0f,-3.0f, net.minecraft.world.item.Tiers.WOOD,null,new Item.Properties()));

    public static RegistryObject<Item> STONE_PAXEL_PAVEL = ITEMS.register("stone_paxel_pavel", () -> new PaxelPavel(5.0f,-3.0f, net.minecraft.world.item.Tiers.STONE,null,new Item.Properties()));
    public static RegistryObject<Item> STONE_PAXEL_PAXE = ITEMS.register("stone_paxel_paxe", () -> new PaxelPaxe(5.0f,-3.0f, net.minecraft.world.item.Tiers.STONE,null,new Item.Properties()));
    public static RegistryObject<Item> STONE_PAXEL_PAXEL = ITEMS.register("stone_paxel_paxel", () -> new PaxelPaxel(5.0f,-3.0f, net.minecraft.world.item.Tiers.STONE,null,new Item.Properties()));
    public static RegistryObject<Item> STONE_PAXEL_SHAXE = ITEMS.register("stone_paxel_shaxe", () -> new PaxelShaxe(5.0f,-3.0f, net.minecraft.world.item.Tiers.STONE,null,new Item.Properties()));

    public static RegistryObject<Item> IRON_PAXEL_PAVEL = ITEMS.register("iron_paxel_pavel", () -> new PaxelPavel(5.0f,-3.0f, net.minecraft.world.item.Tiers.IRON,null,new Item.Properties()));
    public static RegistryObject<Item> IRON_PAXEL_PAXE =  ITEMS.register("iron_paxel_paxe",   () -> new PaxelPaxe(5.0f,-3.0f, net.minecraft.world.item.Tiers.IRON,null,new Item.Properties()));
    public static RegistryObject<Item> IRON_PAXEL_PAXEL = ITEMS.register("iron_paxel_paxel", () -> new PaxelPaxel(5.0f,-3.0f, net.minecraft.world.item.Tiers.IRON,null,new Item.Properties()));
    public static RegistryObject<Item> IRON_PAXEL_SHAXE = ITEMS.register("iron_paxel_shaxe", () -> new PaxelShaxe(5.0f,-3.0f, net.minecraft.world.item.Tiers.IRON,null,new Item.Properties()));

    public static RegistryObject<Item> GOLD_PAXEL_PAVEL = ITEMS.register("gold_paxel_pavel", () -> new PaxelPavel(5.0f,-3.0f, net.minecraft.world.item.Tiers.GOLD,null,new Item.Properties()));
    public static RegistryObject<Item> GOLD_PAXEL_PAXE =  ITEMS.register("gold_paxel_paxe",   () -> new PaxelPaxe(5.0f,-3.0f, net.minecraft.world.item.Tiers.GOLD,null,new Item.Properties()));
    public static RegistryObject<Item> GOLD_PAXEL_PAXEL = ITEMS.register("gold_paxel_paxel", () -> new PaxelPaxel(5.0f,-3.0f, net.minecraft.world.item.Tiers.GOLD,null,new Item.Properties()));
    public static RegistryObject<Item> GOLD_PAXEL_SHAXE = ITEMS.register("gold_paxel_shaxe", () -> new PaxelShaxe(5.0f,-3.0f, net.minecraft.world.item.Tiers.GOLD,null,new Item.Properties()));

    public static RegistryObject<Item> DIAMOND_PAXEL_PAVEL = ITEMS.register("diamond_paxel_pavel", () -> new PaxelPavel(5.0f,-3.0f, net.minecraft.world.item.Tiers.DIAMOND,null,new Item.Properties()));
    public static RegistryObject<Item> DIAMOND_PAXEL_PAXE =  ITEMS.register("diamond_paxel_paxe",   () -> new PaxelPaxe(5.0f,-3.0f, net.minecraft.world.item.Tiers.DIAMOND,null,new Item.Properties()));
    public static RegistryObject<Item> DIAMOND_PAXEL_PAXEL = ITEMS.register("diamond_paxel_paxel", () -> new PaxelPaxel(5.0f,-3.0f, net.minecraft.world.item.Tiers.DIAMOND,null,new Item.Properties()));
    public static RegistryObject<Item> DIAMOND_PAXEL_SHAXE = ITEMS.register("diamond_paxel_shaxe", () -> new PaxelShaxe(5.0f,-3.0f, net.minecraft.world.item.Tiers.DIAMOND,null,new Item.Properties()));

    public static RegistryObject<Item> NETHERITE_PAXEL_PAVEL = ITEMS.register("netherite_paxel_pavel", () -> new PaxelPavel(5.0f,-3.0f, net.minecraft.world.item.Tiers.NETHERITE,null,new Item.Properties()));
    public static RegistryObject<Item> NETHERITE_PAXEL_PAXE =  ITEMS.register("netherite_paxel_paxe",   () -> new PaxelPaxe(5.0f,-3.0f, net.minecraft.world.item.Tiers.NETHERITE,null,new Item.Properties()));
    public static RegistryObject<Item> NETHERITE_PAXEL_PAXEL = ITEMS.register("netherite_paxel_paxel", () -> new PaxelPaxel(5.0f,-3.0f, net.minecraft.world.item.Tiers.NETHERITE,null,new Item.Properties()));
    public static RegistryObject<Item> NETHERITE_PAXEL_SHAXE = ITEMS.register("netherite_paxel_shaxe", () -> new PaxelShaxe(5.0f,-3.0f, net.minecraft.world.item.Tiers.NETHERITE,null,new Item.Properties()));

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
