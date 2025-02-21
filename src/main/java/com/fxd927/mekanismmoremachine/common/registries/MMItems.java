package com.fxd927.mekanismmoremachine.common.registries;

import com.fxd927.mekanismmoremachine.common.MekanismMoreMachine;
import com.fxd927.mekanismmoremachine.common.item.EnergyItem;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class MMItems {
    public static final ItemDeferredRegister ITEMS = new ItemDeferredRegister(MekanismMoreMachine.MODID);

    public static final ItemRegistryObject<EnergyItem> SHARD_ENERGY;
    public static final ItemRegistryObject<EnergyItem> CRYSTAL_ENERGY;

    static {
        SHARD_ENERGY = ITEMS.register("shard_energy", ()-> new EnergyItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
        CRYSTAL_ENERGY = ITEMS.register("crystal_energy", ()-> new EnergyItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
    }
}
