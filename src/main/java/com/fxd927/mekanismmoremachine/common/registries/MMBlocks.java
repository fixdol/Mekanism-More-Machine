package com.fxd927.mekanismmoremachine.common.registries;

import com.fxd927.mekanismmoremachine.common.MekanismMoreMachine;
import com.fxd927.mekanismmoremachine.common.content.blocktype.MMMachine;
import com.fxd927.mekanismmoremachine.common.tile.machine.TileEntityEnergyCatalystMachine;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.item.block.machine.ItemBlockMachine;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.resource.BlockResourceInfo;

public class MMBlocks {
    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister(MekanismMoreMachine.MODID);

    public static final BlockRegistryObject<BlockTile.BlockTileModel<TileEntityEnergyCatalystMachine, MMMachine<TileEntityEnergyCatalystMachine>>, ItemBlockMachine> ENERGY_CATALYST_MACHINE;

    static {
        ENERGY_CATALYST_MACHINE = BLOCKS.register("energy_catalyst_machine",() -> new BlockTile.BlockTileModel<>(MMBlockTypes.ENERGY_CATALYST_MACHINE, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor())),ItemBlockMachine::new);
    }

    private MMBlocks(){
    }
}
