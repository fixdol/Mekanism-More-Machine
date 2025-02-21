package com.fxd927.mekanismmoremachine.common.registration.impl;

import com.fxd927.mekanismmoremachine.common.recipe.IMMRecipeTypeProvider;
import com.fxd927.mekanismmoremachine.common.recipe.MMRecipeType;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import mekanism.common.registration.WrappedRegistryObject;
import net.minecraftforge.registries.RegistryObject;

public class MMRecipeTypeRegistryObject<RECIPE extends MekanismRecipe, MS_INPUT_CACHE extends IInputRecipeCache> extends
        WrappedRegistryObject<MMRecipeType<RECIPE, MS_INPUT_CACHE>> implements IMMRecipeTypeProvider<RECIPE, MS_INPUT_CACHE> {
    public MMRecipeTypeRegistryObject(RegistryObject<MMRecipeType<RECIPE, MS_INPUT_CACHE>> registryObject) {
        super(registryObject);
    }

    @Override
    public MMRecipeType<RECIPE, MS_INPUT_CACHE> getMSRecipeType() {
        return get();
    }
}
