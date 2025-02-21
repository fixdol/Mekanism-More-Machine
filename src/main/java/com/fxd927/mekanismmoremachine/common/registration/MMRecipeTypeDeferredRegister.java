package com.fxd927.mekanismmoremachine.common.registration;

import com.fxd927.mekanismmoremachine.common.recipe.IMMRecipeTypeProvider;
import com.fxd927.mekanismmoremachine.common.recipe.MMRecipeType;
import com.fxd927.mekanismmoremachine.common.registration.impl.MMRecipeTypeRegistryObject;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import mekanism.common.registration.WrappedDeferredRegister;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class MMRecipeTypeDeferredRegister extends WrappedDeferredRegister<RecipeType<?>> {
    private final List<IMMRecipeTypeProvider<?, ?>> recipeTypes = new ArrayList<>();

    public MMRecipeTypeDeferredRegister(String modid) {
        super(modid, ForgeRegistries.RECIPE_TYPES);
    }

    public <RECIPE extends MekanismRecipe, MS_INPUT_CACHE extends IInputRecipeCache> MMRecipeTypeRegistryObject<RECIPE, MS_INPUT_CACHE> register(String name,
                                                                                                                                                 Supplier<? extends MMRecipeType<RECIPE, MS_INPUT_CACHE>> sup) {
        MMRecipeTypeRegistryObject<RECIPE, MS_INPUT_CACHE> registeredRecipeType = register(name, sup, MMRecipeTypeRegistryObject::new);
        recipeTypes.add(registeredRecipeType);
        return registeredRecipeType;
    }

    public List<IMMRecipeTypeProvider<?, ?>> getAllRecipeTypes() {
        return Collections.unmodifiableList(recipeTypes);
    }
}
