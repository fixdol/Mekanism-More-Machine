package com.fxd927.mekanismmoremachine.common.recipe;

import com.fxd927.mekanismmoremachine.api.recipes.EnergyCatalystRecipe;
import com.fxd927.mekanismmoremachine.common.recipe.lookup.cache.MMInputRecipeCache;
import com.fxd927.mekanismmoremachine.common.registration.MMRecipeTypeDeferredRegister;
import com.fxd927.mekanismmoremachine.common.MekanismMoreMachine;
import com.fxd927.mekanismmoremachine.common.registration.impl.MMRecipeTypeRegistryObject;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.client.MekanismClient;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MMRecipeType<RECIPE extends MekanismRecipe, INPUT_CACHE extends IInputRecipeCache> implements RecipeType<RECIPE>,
        IMMRecipeTypeProvider<RECIPE, INPUT_CACHE> {
    public static final MMRecipeTypeDeferredRegister RECIPE_TYPES = new MMRecipeTypeDeferredRegister(MekanismMoreMachine.MODID);

    public static final MMRecipeTypeRegistryObject<EnergyCatalystRecipe, MMInputRecipeCache.SingleItem<EnergyCatalystRecipe>> ENERGY_CATALYST =
            register("energy_catalyst", recipeType -> new MMInputRecipeCache.SingleItem<>(recipeType, EnergyCatalystRecipe::getInput));

   public static <RECIPE extends MekanismRecipe, INPUT_CACHE extends IInputRecipeCache> MMRecipeTypeRegistryObject<RECIPE, INPUT_CACHE> register(String name,
                                                                                                                                                 Function<MMRecipeType<RECIPE, INPUT_CACHE>, INPUT_CACHE> inputCacheCreator) {
        return RECIPE_TYPES.register(name, () -> new MMRecipeType<>(name, inputCacheCreator));
    }

    public static void clearCache() {
        for (IMMRecipeTypeProvider<?, ?> recipeTypeProvider : RECIPE_TYPES.getAllRecipeTypes()) {
            recipeTypeProvider.getMSRecipeType().clearCaches();
        }
    }

    private List<RECIPE> cachedRecipes = Collections.emptyList();
    private final ResourceLocation registryName;
    private final INPUT_CACHE inputCache;

    private MMRecipeType(String name, Function<MMRecipeType<RECIPE, INPUT_CACHE>, INPUT_CACHE> inputCacheCreator) {
        this.registryName = MekanismMoreMachine.rl(name);
        this.inputCache = inputCacheCreator.apply(this);
    }

    @Override
    public String toString() {
        return registryName.toString();
    }

    @Override
    public ResourceLocation getRegistryName() {
        return registryName;
    }

    @Override
    public MMRecipeType<RECIPE, INPUT_CACHE> getMSRecipeType() {
        return this;
    }

    private void clearCaches() {
        cachedRecipes = Collections.emptyList();
        inputCache.clear();
    }

    @Override
    public INPUT_CACHE getInputCache() {
        return inputCache;
    }

    @NotNull
    @Override
    public List<RECIPE> getRecipes(@Nullable Level world) {
        if (world == null) {
            if (FMLEnvironment.dist.isClient()) {
                world = MekanismClient.tryGetClientWorld();
            } else {
                world = ServerLifecycleHooks.getCurrentServer().overworld();
            }
            if (world == null) {
                return Collections.emptyList();
            }
        }
        if (cachedRecipes.isEmpty()) {
            RecipeManager recipeManager = world.getRecipeManager();
            List<RECIPE> recipes = recipeManager.getAllRecipesFor(this);
            cachedRecipes = recipes.stream()
                    .filter(recipe -> !recipe.isIncomplete())
                    .toList();
        }
        return cachedRecipes;
    }

    /**
     * Helper for getting a recipe from a world's recipe manager.
     */
    public static <C extends Container, RECIPE_TYPE extends Recipe<C>> Optional<RECIPE_TYPE> getRecipeFor(RecipeType<RECIPE_TYPE> recipeType, C inventory, Level level) {
        return level.getRecipeManager().getRecipeFor(recipeType, inventory, level)
                .filter(recipe -> recipe.isSpecial() || !recipe.isIncomplete());
    }

    /**
     * Helper for getting a recipe from a world's recipe manager.
     */
    public static Optional<? extends Recipe<?>> byKey(Level level, ResourceLocation id) {
        return level.getRecipeManager().byKey(id)
                .filter(recipe -> recipe.isSpecial() || !recipe.isIncomplete());
    }
}
