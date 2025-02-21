package com.fxd927.mekanismmoremachine.common.config;

import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedFloatingLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class MMUsageConfig extends BaseMekanismConfig {
    public final CachedFloatingLongValue energyCatalystMachine;

    private final ForgeConfigSpec configSpec;

    MMUsageConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Mekanism: More Machine Energy Usage Config. This config is synced from server to client.").push("storage");

        energyCatalystMachine = CachedFloatingLongValue.define(this, builder, "Energy per operation tick (Joules).", "energyCatalystMachine",
                FloatingLong.createConst(100));

        builder.pop();
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "mekanismmoremachine-usage";
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
