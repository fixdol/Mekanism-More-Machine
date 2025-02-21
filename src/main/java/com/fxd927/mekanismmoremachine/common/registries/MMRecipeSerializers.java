
package com.fxd927.mekanismmoremachine.common.registries;

import com.fxd927.mekanismmoremachine.api.recipes.EnergyCatalystRecipe;
import com.fxd927.mekanismmoremachine.common.MekanismMoreMachine;
import com.fxd927.mekanismmoremachine.common.recipe.impl.EnergyCatalystIRecipe;
import com.fxd927.mekanismmoremachine.common.recipe.serializer.EnergyCatalystRecipeSerializer;
import mekanism.common.registration.impl.RecipeSerializerDeferredRegister;
import mekanism.common.registration.impl.RecipeSerializerRegistryObject;

public class MMRecipeSerializers {
    public static final RecipeSerializerDeferredRegister RECIPE_SERIALIZERS = new RecipeSerializerDeferredRegister(MekanismMoreMachine.MODID);

    public static final RecipeSerializerRegistryObject<EnergyCatalystRecipe> ENERGY_CATALYST;

    static {
        ENERGY_CATALYST = RECIPE_SERIALIZERS.register("energy_catalyst", () -> new EnergyCatalystRecipeSerializer<>(EnergyCatalystIRecipe::new));
    }
    private MMRecipeSerializers(){
    }
}
