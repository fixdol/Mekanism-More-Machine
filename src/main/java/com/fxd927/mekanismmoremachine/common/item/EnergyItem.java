package com.fxd927.mekanismmoremachine.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EnergyItem extends Item {
    public EnergyItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
