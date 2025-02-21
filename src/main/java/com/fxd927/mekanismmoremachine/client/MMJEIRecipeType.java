package com.fxd927.mekanismmoremachine.client;

import com.fxd927.mekanismmoremachine.api.recipes.EnergyCatalystRecipe;
import com.fxd927.mekanismmoremachine.common.registries.MMBlocks;
import mekanism.client.jei.MekanismJEIRecipeType;

public class MMJEIRecipeType {
    public static final MekanismJEIRecipeType<EnergyCatalystRecipe> ENERGY_CATALYST_MACHINE = new MekanismJEIRecipeType<>(MMBlocks.ENERGY_CATALYST_MACHINE, EnergyCatalystRecipe.class);
}
