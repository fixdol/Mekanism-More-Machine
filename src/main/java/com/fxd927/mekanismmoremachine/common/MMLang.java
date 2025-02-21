package com.fxd927.mekanismmoremachine.common;

import mekanism.api.text.ILangEntry;
import net.minecraft.Util;

public enum MMLang implements ILangEntry {
    DESCRIPTION_ENERGY_CATALYST_MACHINE("description", "energy_catalyst_machine"),
    MEKANISM_MORE_MACHINE("constants", "mod_name");

    private final String key;

    MMLang(String type,String path){
        this(Util.makeDescriptionId(type, MekanismMoreMachine.rl(path)));
    }

    MMLang(String key){
        this.key = key;
    }

    @Override
    public String getTranslationKey(){
        return key;
    }
}
