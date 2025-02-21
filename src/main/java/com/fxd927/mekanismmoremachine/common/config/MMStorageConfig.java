package com.fxd927.mekanismmoremachine.common.config;

import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedFloatingLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class MMStorageConfig extends BaseMekanismConfig {
    private final ForgeConfigSpec configSpec;

    public final CachedFloatingLongValue energyCatalystMachine;

    MMStorageConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Mekanism: More Machine Energy Storage Config. This config is synced from server to client.").push("storage");

        energyCatalystMachine = CachedFloatingLongValue.define(this, builder, "Base energy storage (Joules).", "energyCatalystMachine",
                FloatingLong.createConst(6_400_000));

        builder.pop();
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "mekanismmoremachine-storage";
    }

    @Override
    public ForgeConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }

    @Override
    public boolean addToContainer() {
        return false;
    }
}
