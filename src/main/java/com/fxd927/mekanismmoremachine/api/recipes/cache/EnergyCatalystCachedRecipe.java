package com.fxd927.mekanismmoremachine.api.recipes.cache;

import com.fxd927.mekanismmoremachine.api.recipes.EnergyCatalystRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.cache.CachedRecipeHelper;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.outputs.IOutputHandler;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.BooleanSupplier;

public class EnergyCatalystCachedRecipe extends CachedRecipe<EnergyCatalystRecipe> {
    private final IInputHandler<ItemStack> inputHandler;
    private final IOutputHandler<ItemStack> outputHandler;

    @Nullable
    private ItemStack input;
    @Nullable
    private ItemStack output;

    public EnergyCatalystCachedRecipe(EnergyCatalystRecipe recipe, BooleanSupplier recheckAllErrors, IInputHandler<ItemStack> inputHandler, IOutputHandler<ItemStack> outputHandler) {
        super(recipe, recheckAllErrors);
        this.inputHandler = Objects.requireNonNull(inputHandler, "Input handler cannot be null.");
        this.outputHandler = Objects.requireNonNull(outputHandler, "Output handler cannot be null.");
    }

    @Override
    protected void calculateOperationsThisTick(CachedRecipe.OperationTracker tracker) {
        super.calculateOperationsThisTick(tracker);
        CachedRecipeHelper.oneInputCalculateOperationsThisTick(tracker, inputHandler, recipe::getInput, input -> this.input = input, outputHandler, recipe::getOutput, output -> this.output = output, ItemStack::isEmpty);
    }

    @Override
    public boolean isInputValid() {
        ItemStack input = inputHandler.getInput();
        return recipe.test(input);
    }

    @Override
    protected void finishProcessing(int operations) {
        if (input != null && output != null) {
            outputHandler.handleOutput(output, operations);
        }
    }
}
