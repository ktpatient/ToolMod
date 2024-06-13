package com.kitp13.transfigured;

import com.kitp13.transfigured.items.ModItems;
import com.kitp13.transfigured.modifiers.BrittleModifier;
import com.kitp13.transfigured.modifiers.MiningExpModifier;
import com.kitp13.transfigured.modifiers.lib.ModifierRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Transfigured.MOD_ID)
public class Transfigured {
    public static final String MOD_ID = "transfigured";
    public static final String MOD_NAME = "Transfigured Tools";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public Transfigured(){
        ModifierRegistry.registerModifier(BrittleModifier.NAME,new BrittleModifier());
        ModifierRegistry.registerModifier(MiningExpModifier.NAME,new MiningExpModifier());
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(bus);
    }
}
