package com.fxd927.mekanismmoremachine.common.registries;

import com.fxd927.mekanismmoremachine.common.MekanismMoreMachine;
import com.fxd927.mekanismmoremachine.common.tile.machine.TileEntityEnergyCatalystMachine;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

public class MMTileEntityTypes {
    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismMoreMachine.MODID);

    public static final TileEntityTypeRegistryObject<TileEntityEnergyCatalystMachine> ENERGY_CATALYST_MACHINE;

    static {
        ENERGY_CATALYST_MACHINE = TILE_ENTITY_TYPES.register(MMBlocks.ENERGY_CATALYST_MACHINE, TileEntityEnergyCatalystMachine::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    }

    private MMTileEntityTypes(){
    }
}
