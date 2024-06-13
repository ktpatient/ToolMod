package com.kitp13.transfigured.modifiers.lib;

import java.util.HashMap;

public class ModifierRegistry {
    public static HashMap<String, Modifier> MODIFIERS_MAP= new HashMap<>();
    public static void registerModifier(String name, Modifier modifier){
        MODIFIERS_MAP.put(name,modifier);
    }
}
