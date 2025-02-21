package com.fxd927.mekanismmoremachine.common.config;

import mekanism.common.config.MekanismConfigHelper;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;

public class MMConfig {
    private MMConfig() {
    }

    public static final MMStorageConfig storageConfig = new MMStorageConfig();
    public static final MMUsageConfig usageConfig = new MMUsageConfig();

    public static void registerConfigs(ModLoadingContext modLoadingContext) {
        ModContainer modContainer = modLoadingContext.getActiveContainer();
        MekanismConfigHelper.registerConfig(modContainer, storageConfig);
        MekanismConfigHelper.registerConfig(modContainer, usageConfig);
    }
}
