package com.fxd927.mekanismmoremachine.client;

import com.fxd927.mekanismmoremachine.client.gui.GuiEnergyCatalystMachine;
import com.fxd927.mekanismmoremachine.common.MekanismMoreMachine;
import com.fxd927.mekanismmoremachine.common.registries.MMContainerTypes;
import mekanism.client.ClientRegistrationUtil;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = MekanismMoreMachine.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMClientRegistration {
    private MMClientRegistration() {
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerContainers(RegisterEvent event) {
        event.register(Registries.MENU, helper -> {
            ClientRegistrationUtil.registerScreen(MMContainerTypes.ENERGY_CATALYST_MACHINE, GuiEnergyCatalystMachine::new);
        });
    }
}
