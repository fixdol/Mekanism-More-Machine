package com.fxd927.mekanismmoremachine.common.recipe.impl;

import com.fxd927.mekanismmoremachine.api.recipes.EnergyCatalystRecipe;
import com.fxd927.mekanismmoremachine.common.recipe.MMRecipeType;
import com.fxd927.mekanismmoremachine.common.registries.MMBlocks;
import com.fxd927.mekanismmoremachine.common.registries.MMRecipeSerializers;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.math.FloatingLong;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

@NothingNullByDefault
public class EnergyCatalystIRecipe extends EnergyCatalystRecipe {
    public EnergyCatalystIRecipe(ResourceLocation id, ItemStackIngredient input, ItemStack output, FloatingLong energyRequired) {
        super(id, input, output, energyRequired);
    }

    @Override
    public RecipeType<EnergyCatalystRecipe> getType() {
        return MMRecipeType.ENERGY_CATALYST.get();
    }

    @Override
    public RecipeSerializer<EnergyCatalystRecipe> getSerializer() {
        return MMRecipeSerializers.ENERGY_CATALYST.get();
    }

    @Override
    public String getGroup() {
        return MMBlocks.ENERGY_CATALYST_MACHINE.getName();
    }

    @Override
    public ItemStack getToastSymbol() {
        return MMBlocks.ENERGY_CATALYST_MACHINE.getItemStack();
    }
}
