package com.fxd927.mekanismmoremachine.common.registries;

import com.fxd927.mekanismmoremachine.common.MMLang;
import com.fxd927.mekanismmoremachine.common.config.MMConfig;
import com.fxd927.mekanismmoremachine.common.content.blocktype.MMBlockShapes;
import com.fxd927.mekanismmoremachine.common.content.blocktype.MMMachine;
import com.fxd927.mekanismmoremachine.common.tile.machine.TileEntityEnergyCatalystMachine;
import mekanism.api.Upgrade;
import mekanism.common.block.attribute.Attributes;
import mekanism.common.registries.MekanismSounds;

import java.util.EnumSet;

public class MMBlockTypes {
    public static final MMMachine<TileEntityEnergyCatalystMachine> ENERGY_CATALYST_MACHINE = MMMachine.MMMachineBuilder
            .createMSMachine(() -> MMTileEntityTypes.ENERGY_CATALYST_MACHINE, MMLang.DESCRIPTION_ENERGY_CATALYST_MACHINE)
            .withCustomShape(MMBlockShapes.ENERGY_CATALYST_MACHINE)
            .withSound(MekanismSounds.RESISTIVE_HEATER)
            .withGui(() -> MMContainerTypes.ENERGY_CATALYST_MACHINE)
            .withEnergyConfig(MMConfig.usageConfig.energyCatalystMachine, MMConfig.storageConfig.energyCatalystMachine)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.MUFFLING))
            .withComputerSupport("energyCatalystMachine")
            .replace(Attributes.ACTIVE_LIGHT)
            .build();

    private MMBlockTypes(){
    }
}
