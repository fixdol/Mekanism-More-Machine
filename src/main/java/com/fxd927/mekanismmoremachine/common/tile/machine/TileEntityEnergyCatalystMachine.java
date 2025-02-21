package com.fxd927.mekanismmoremachine.common.tile.machine;

import com.fxd927.mekanismmoremachine.api.recipes.EnergyCatalystRecipe;
import com.fxd927.mekanismmoremachine.api.recipes.cache.EnergyCatalystCachedRecipe;
import com.fxd927.mekanismmoremachine.common.capabilities.energy.ECMEnergyContainer;
import com.fxd927.mekanismmoremachine.common.recipe.IMMRecipeTypeProvider;
import com.fxd927.mekanismmoremachine.common.recipe.MMRecipeType;
import com.fxd927.mekanismmoremachine.common.recipe.lookup.IMMSingleRecipeLookupHandler;
import com.fxd927.mekanismmoremachine.common.recipe.lookup.cache.MMInputRecipeCache;
import com.fxd927.mekanismmoremachine.common.registries.MMBlocks;
import com.fxd927.mekanismmoremachine.common.tile.prefab.MMTileEntityProgressMachine;
import mekanism.api.IContentsListener;
import mekanism.api.math.FloatingLong;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.inputs.InputHelper;
import mekanism.api.recipes.outputs.IOutputHandler;
import mekanism.api.recipes.outputs.OutputHelper;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.capabilities.holder.energy.EnergyContainerHelper;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.capabilities.holder.slot.IInventorySlotHolder;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper;
import mekanism.common.integration.computer.annotation.ComputerMethod;
import mekanism.common.integration.computer.annotation.WrappingComputerMethod;
import mekanism.common.integration.computer.computercraft.ComputerConstants;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;
import mekanism.common.inventory.slot.OutputInventorySlot;
import mekanism.common.inventory.warning.WarningTracker;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.tile.component.TileComponentConfig;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.upgrade.MachineUpgradeData;
import mekanism.common.util.MekanismUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TileEntityEnergyCatalystMachine extends MMTileEntityProgressMachine<EnergyCatalystRecipe>
        implements IMMSingleRecipeLookupHandler.ItemRecipeLookupHandler<EnergyCatalystRecipe> {
    private static final List<CachedRecipe.OperationTracker.RecipeError> TRACKED_ERROR_TYPES = List.of(
            CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_ENERGY,
            CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_INPUT,
            CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_OUTPUT_SPACE,
            CachedRecipe.OperationTracker.RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT
    );

    protected final IInputHandler<@NotNull ItemStack> inputHandler;
    protected final IOutputHandler<@NotNull ItemStack> outputHandler;

    private FloatingLong recipeEnergyRequired = FloatingLong.ZERO;

    private MachineEnergyContainer<TileEntityEnergyCatalystMachine> energyContainer;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getInput", docPlaceholder = "input slot")
    InputInventorySlot inputSlot;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getOutput", docPlaceholder = "output slot")
    OutputInventorySlot outputSlot;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getEnergyItem", docPlaceholder = "energy slot")
    EnergyInventorySlot energySlot;

    public TileEntityEnergyCatalystMachine(BlockPos pos, BlockState state) {
        super(MMBlocks.ENERGY_CATALYST_MACHINE, pos, state, TRACKED_ERROR_TYPES, 600);
        configComponent = new TileComponentConfig(this, TransmissionType.ITEM, TransmissionType.ENERGY);
        configComponent.setupItemIOConfig(inputSlot, outputSlot, energySlot);
        configComponent.setupInputConfig(TransmissionType.ENERGY, energyContainer);

        ejectorComponent = new TileComponentEjector(this);
        ejectorComponent.setOutputData(configComponent, TransmissionType.ITEM);

        inputHandler = InputHelper.getInputHandler(inputSlot, CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_INPUT);
        outputHandler = OutputHelper.getOutputHandler(outputSlot, CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_OUTPUT_SPACE);
    }

    @NotNull
    @Override
    protected IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener, IContentsListener recipeCacheListener) {
        EnergyContainerHelper builder = EnergyContainerHelper.forSideWithConfig(this::getDirection, this::getConfig);
        builder.addContainer(energyContainer = ECMEnergyContainer.input(this, listener));
        return builder.build();
    }

    @NotNull
    @Override
    protected IInventorySlotHolder getInitialInventory(IContentsListener listener, IContentsListener recipeCacheListener) {
        InventorySlotHelper builder = InventorySlotHelper.forSideWithConfig(this::getDirection, this::getConfig);
        builder.addSlot(inputSlot = InputInventorySlot.at(this::containsRecipe, recipeCacheListener, 48, 35))
                .tracksWarnings(slot -> slot.warning(WarningTracker.WarningType.NO_MATCHING_RECIPE, getWarningCheck(CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_INPUT)));
        builder.addSlot(outputSlot = OutputInventorySlot.at(listener, 126, 35))
                .tracksWarnings(slot -> slot.warning(WarningTracker.WarningType.NO_SPACE_IN_OUTPUT, getWarningCheck(CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_OUTPUT_SPACE)));
        builder.addSlot(energySlot = EnergyInventorySlot.fillOrConvert(energyContainer, this::getLevel, listener, 17, 35));
        return builder.build();
    }

    @Override
    public void onCachedRecipeChanged(@Nullable CachedRecipe<EnergyCatalystRecipe> cachedRecipe, int cacheIndex) {
        super.onCachedRecipeChanged(cachedRecipe, cacheIndex);
        if (cachedRecipe == null) {
            recipeEnergyRequired = FloatingLong.ZERO;
        } else {
            EnergyCatalystRecipe recipe = cachedRecipe.getRecipe();
            recipeEnergyRequired = recipe.getEnergyRequired();
        }
        energyContainer.updateEnergyPerTick();
    }

    public FloatingLong getRecipeEnergyRequired() {
        return recipeEnergyRequired;
    }

    @Override
    protected void onUpdateServer() {
        super.onUpdateServer();
        energySlot.fillContainerOrConvert();
        recipeCacheLookupMonitor.updateAndProcess();
    }

    @Nullable
    @Override
    public EnergyCatalystRecipe getRecipe(int cacheIndex) {
        return findFirstRecipe(inputHandler);
    }

    @NotNull
    @Override
    public CachedRecipe<EnergyCatalystRecipe> createNewCachedRecipe(@NotNull EnergyCatalystRecipe recipe, int cacheIndex) {
        return new EnergyCatalystCachedRecipe(recipe, recheckAllRecipeErrors, inputHandler, outputHandler)
                .setErrorsChanged(this::onErrorsChanged)
                .setCanHolderFunction(() -> MekanismUtils.canFunction(this))
                .setActive(this::setActive)
                .setEnergyRequirements(energyContainer::getEnergyPerTick, energyContainer)
                .setRequiredTicks(this::getTicksRequired)
                .setOnFinish(this::markForSave)
                .setOperatingTicksChanged(this::setOperatingTicks);
    }

    @NotNull
    @Override
    public MachineUpgradeData getUpgradeData() {
        return new MachineUpgradeData(redstone, getControlType(), getEnergyContainer(), getOperatingTicks(), energySlot, inputSlot, outputSlot, getComponents());
    }

    public MachineEnergyContainer<TileEntityEnergyCatalystMachine> getEnergyContainer() {
        return energyContainer;
    }

    @Override
    public boolean isConfigurationDataCompatible(BlockEntityType<?> tileType) {
        return super.isConfigurationDataCompatible(tileType) || MekanismUtils.isSameTypeFactory(getBlockType(), tileType);
    }

    @ComputerMethod(methodDescription = ComputerConstants.DESCRIPTION_GET_ENERGY_USAGE)
    FloatingLong getEnergyUsage() {
        return getActive() ? energyContainer.getEnergyPerTick() : FloatingLong.ZERO;
    }

    @NotNull
    @Override
    public IMMRecipeTypeProvider<EnergyCatalystRecipe, MMInputRecipeCache.SingleItem<EnergyCatalystRecipe>> getMMRecipeType() {
        return MMRecipeType.ENERGY_CATALYST;
    }
}
