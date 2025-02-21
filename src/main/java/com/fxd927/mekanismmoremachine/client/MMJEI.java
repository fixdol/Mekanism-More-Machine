package com.fxd927.mekanismmoremachine.client;

import com.fxd927.mekanismmoremachine.client.jei.MMRecipeRegistryHelper;
import com.fxd927.mekanismmoremachine.client.jei.machine.EnergyCatalystRecipeCategory;
import com.fxd927.mekanismmoremachine.common.MekanismMoreMachine;
import com.fxd927.mekanismmoremachine.common.recipe.MMRecipeType;
import com.fxd927.mekanismmoremachine.common.registries.MMBlocks;
import mekanism.client.jei.CatalystRegistryHelper;
import mekanism.client.jei.MekanismJEI;
import mekanism.client.jei.MekanismJEIRecipeType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

@JeiPlugin
public class MMJEI implements IModPlugin {
    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return MekanismMoreMachine.rl("jei_plugin");
    }

    @Override
    public void registerItemSubtypes(@Nonnull ISubtypeRegistration registry) {
        MekanismJEI.registerItemSubtypes(registry, MMBlocks.BLOCKS.getAllBlocks());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new EnergyCatalystRecipeCategory(guiHelper, MMJEIRecipeType.ENERGY_CATALYST_MACHINE, MMBlocks.ENERGY_CATALYST_MACHINE));
       }

    @Override
    public void registerRecipeCatalysts(@Nonnull IRecipeCatalystRegistration registry) {
        CatalystRegistryHelper.register(registry, MMBlocks.ENERGY_CATALYST_MACHINE, MekanismJEIRecipeType.GAS_CONVERSION);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        MMRecipeRegistryHelper.register(registry, MMJEIRecipeType.ENERGY_CATALYST_MACHINE, MMRecipeType.ENERGY_CATALYST);
      }
}
