package com.fxd927.mekanismmoremachine.common.recipe.lookup.cache;

import com.fxd927.mekanismmoremachine.common.recipe.MMRecipeType;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.chemical.ChemicalChemicalToChemicalRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.FluidStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.common.recipe.lookup.cache.type.ChemicalInputCache;
import mekanism.common.recipe.lookup.cache.type.FluidInputCache;
import mekanism.common.recipe.lookup.cache.type.ItemInputCache;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.TriPredicate;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class MMInputRecipeCache {
    public static class SingleItem<RECIPE extends MekanismRecipe & Predicate<ItemStack>>
            extends MMSingleInputRecipeCache<ItemStack, ItemStackIngredient, RECIPE, ItemInputCache<RECIPE>> {

        public SingleItem(MMRecipeType<RECIPE, ?> recipeType, Function<RECIPE, ItemStackIngredient> inputExtractor) {
            super(recipeType, inputExtractor, new ItemInputCache<>());
        }
    }

    public static class SingleFluid<RECIPE extends MekanismRecipe & Predicate<FluidStack>>
            extends MMSingleInputRecipeCache<FluidStack, FluidStackIngredient, RECIPE, FluidInputCache<RECIPE>> {

        public SingleFluid(MMRecipeType<RECIPE, ?> recipeType, Function<RECIPE, FluidStackIngredient> inputExtractor) {
            super(recipeType, inputExtractor, new FluidInputCache<>());
        }
    }

    public static class SingleChemical<CHEMICAL extends Chemical<CHEMICAL>, STACK extends ChemicalStack<CHEMICAL>, RECIPE extends MekanismRecipe & Predicate<STACK>>
            extends MMSingleInputRecipeCache<STACK, ChemicalStackIngredient<CHEMICAL, STACK>, RECIPE, ChemicalInputCache<CHEMICAL, STACK, RECIPE>> {

        public SingleChemical(MMRecipeType<RECIPE, ?> recipeType, Function<RECIPE, ChemicalStackIngredient<CHEMICAL, STACK>> inputExtractor) {
            super(recipeType, inputExtractor, new ChemicalInputCache<>());
        }
    }

    public static class DoubleItem<RECIPE extends MekanismRecipe & BiPredicate<ItemStack, ItemStack>>
            extends MMDoubleInputRecipeCache.DoubleSameInputRecipeCache<ItemStack, ItemStackIngredient, RECIPE, ItemInputCache<RECIPE>> {

        public DoubleItem(MMRecipeType<RECIPE, ?> recipeType, Function<RECIPE, ItemStackIngredient> inputAExtractor,
                          Function<RECIPE, ItemStackIngredient> inputBExtractor) {
            super(recipeType, inputAExtractor, inputBExtractor, ItemInputCache::new);
        }
    }

    public static class ItemChemical<CHEMICAL extends Chemical<CHEMICAL>, STACK extends ChemicalStack<CHEMICAL>, RECIPE extends MekanismRecipe &
            BiPredicate<ItemStack, STACK>> extends MMDoubleInputRecipeCache<ItemStack, ItemStackIngredient, STACK, ChemicalStackIngredient<CHEMICAL, STACK>, RECIPE,
                        ItemInputCache<RECIPE>, ChemicalInputCache<CHEMICAL, STACK, RECIPE>> {

        public ItemChemical(MMRecipeType<RECIPE, ?> recipeType, Function<RECIPE, ItemStackIngredient> inputAExtractor,
                            Function<RECIPE, ChemicalStackIngredient<CHEMICAL, STACK>> inputBExtractor) {
            super(recipeType, inputAExtractor, new ItemInputCache<>(), inputBExtractor, new ChemicalInputCache<>());
        }
    }

    public static class FluidChemical<CHEMICAL extends Chemical<CHEMICAL>, STACK extends ChemicalStack<CHEMICAL>, RECIPE extends MekanismRecipe &
            BiPredicate<FluidStack, STACK>> extends MMDoubleInputRecipeCache<FluidStack, FluidStackIngredient, STACK, ChemicalStackIngredient<CHEMICAL, STACK>, RECIPE,
                        FluidInputCache<RECIPE>, ChemicalInputCache<CHEMICAL, STACK, RECIPE>> {

        public FluidChemical(MMRecipeType<RECIPE, ?> recipeType, Function<RECIPE, FluidStackIngredient> inputAExtractor,
                             Function<RECIPE, ChemicalStackIngredient<CHEMICAL, STACK>> inputBExtractor) {
            super(recipeType, inputAExtractor, new FluidInputCache<>(), inputBExtractor, new ChemicalInputCache<>());
        }
    }

    public static class EitherSideChemical<CHEMICAL extends Chemical<CHEMICAL>, STACK extends ChemicalStack<CHEMICAL>,
            RECIPE extends ChemicalChemicalToChemicalRecipe<CHEMICAL, STACK, ? extends ChemicalStackIngredient<CHEMICAL, STACK>>>
            extends MMEitherSideInputRecipeCache<STACK, ChemicalStackIngredient<CHEMICAL, STACK>, RECIPE, ChemicalInputCache<CHEMICAL, STACK, RECIPE>> {

        public EitherSideChemical(MMRecipeType<RECIPE, ?> recipeType) {
            super(recipeType, ChemicalChemicalToChemicalRecipe::getLeftInput, ChemicalChemicalToChemicalRecipe::getRightInput, new ChemicalInputCache<>());
        }
    }

    public static class ItemFluidChemical<CHEMICAL extends Chemical<CHEMICAL>, STACK extends ChemicalStack<CHEMICAL>, RECIPE extends MekanismRecipe &
            TriPredicate<ItemStack, FluidStack, STACK>> extends MMTripleInputRecipeCache<ItemStack, ItemStackIngredient, FluidStack, FluidStackIngredient, STACK,
                        ChemicalStackIngredient<CHEMICAL, STACK>, RECIPE, ItemInputCache<RECIPE>, FluidInputCache<RECIPE>, ChemicalInputCache<CHEMICAL, STACK, RECIPE>> {

        public ItemFluidChemical(MMRecipeType<RECIPE, ?> recipeType, Function<RECIPE, ItemStackIngredient> inputAExtractor,
                                 Function<RECIPE, FluidStackIngredient> inputBExtractor, Function<RECIPE, ChemicalStackIngredient<CHEMICAL, STACK>> inputCExtractor) {
            super(recipeType, inputAExtractor, new ItemInputCache<>(), inputBExtractor, new FluidInputCache<>(), inputCExtractor, new ChemicalInputCache<>());
        }
    }
}
