package com.fxd927.mekanismmoremachine.common.registries;

import com.fxd927.mekanismmoremachine.common.MMLang;
import com.fxd927.mekanismmoremachine.common.MekanismMoreMachine;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import mekanism.common.registration.impl.CreativeTabRegistryObject;
import mekanism.common.registries.MekanismCreativeTabs;

public class MMCreativeTab {
    public static final CreativeTabDeferredRegister CREATIVE_TABS = new CreativeTabDeferredRegister(MekanismMoreMachine.MODID);

    public static final CreativeTabRegistryObject MEKANISM_MACHINE = CREATIVE_TABS.registerMain(MMLang.MEKANISM_MORE_MACHINE, MMBlocks.ENERGY_CATALYST_MACHINE, builder ->
            builder.withBackgroundLocation(MekanismMoreMachine.rl("textures/gui/creative_tab.png"))
                    .withTabsBefore(MekanismCreativeTabs.MEKANISM.key())
                    .displayItems((displayParameters, output) -> {
                        CreativeTabDeferredRegister.addToDisplay(MMBlocks.BLOCKS, output);
                        CreativeTabDeferredRegister.addToDisplay(MMItems.ITEMS, output);
                    })
    );
}
