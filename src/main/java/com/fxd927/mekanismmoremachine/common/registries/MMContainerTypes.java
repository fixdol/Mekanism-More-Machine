package com.fxd927.mekanismmoremachine.common.registries;

import com.fxd927.mekanismmoremachine.common.MekanismMoreMachine;
import com.fxd927.mekanismmoremachine.common.tile.machine.TileEntityEnergyCatalystMachine;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;

public class MMContainerTypes {
    public static final ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekanismMoreMachine.MODID);


    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityEnergyCatalystMachine>> ENERGY_CATALYST_MACHINE;

    static {
        ENERGY_CATALYST_MACHINE = CONTAINER_TYPES.register(MMBlocks.ENERGY_CATALYST_MACHINE, TileEntityEnergyCatalystMachine.class);
    }

    private MMContainerTypes(){
    }
}
