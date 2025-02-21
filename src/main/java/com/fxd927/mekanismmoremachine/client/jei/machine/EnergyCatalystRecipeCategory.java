package com.fxd927.mekanismmoremachine.client.jei.machine;

import com.fxd927.mekanismmoremachine.api.recipes.EnergyCatalystRecipe;
import mekanism.api.providers.IBlockProvider;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.slot.GuiSlot;
import mekanism.client.gui.element.slot.SlotType;
import mekanism.client.jei.BaseRecipeCategory;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import org.jetbrains.annotations.NotNull;

public class EnergyCatalystRecipeCategory extends BaseRecipeCategory<EnergyCatalystRecipe> {
    private final GuiSlot input;
    private final GuiSlot output;

    public EnergyCatalystRecipeCategory(IGuiHelper helper, MekanismJEIRecipeType<EnergyCatalystRecipe> recipeType, IBlockProvider mekanismBlock) {
        super(helper, recipeType, mekanismBlock, 3, 3, 170, 79);
        input = addSlot(SlotType.INPUT, 48, 35);
        output = addSlot(SlotType.OUTPUT, 126, 35);
        addSlot(SlotType.POWER, 17, 35).with(SlotOverlay.POWER);
        addElement(new GuiVerticalPowerBar(this, FULL_BAR, 37, 15));
        addSimpleProgress(ProgressType.LARGE_RIGHT, 71, 38);
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, EnergyCatalystRecipe recipe, @NotNull IFocusGroup focusGroup) {
        initItem(builder, RecipeIngredientRole.INPUT, input, recipe.getInput().getRepresentations());
        initItem(builder, RecipeIngredientRole.OUTPUT, output, recipe.getOutputDefinition());
    }
}
