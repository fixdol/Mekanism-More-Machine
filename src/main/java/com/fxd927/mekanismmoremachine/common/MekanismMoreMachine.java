package com.fxd927.mekanismmoremachine.common;

import com.fxd927.mekanismmoremachine.common.config.MMConfig;
import com.fxd927.mekanismmoremachine.common.recipe.MMRecipeType;
import com.fxd927.mekanismmoremachine.common.registries.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MekanismMoreMachine.MODID)
public class MekanismMoreMachine
{
    public static final String MODID = "mekanismmoremachine";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MekanismMoreMachine()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MMConfig.registerConfigs(ModLoadingContext.get());
        MMBlocks.BLOCKS.register(modEventBus);
        MMContainerTypes.CONTAINER_TYPES.register(modEventBus);
        MMCreativeTab.CREATIVE_TABS.register(modEventBus);
        MMItems.ITEMS.register(modEventBus);
        MMRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        MMRecipeType.RECIPE_TYPES.register(modEventBus);
        MMTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation rl(String path){
        return new ResourceLocation(MekanismMoreMachine.MODID, path);
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
