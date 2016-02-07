package net.blep.modtech.core.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.blep.modtech.blocks.BlockHandler;
import net.blep.modtech.client.rendering.RenderingHandler;
import net.blep.modtech.core.ModTech;
import net.blep.modtech.core.config.ConfigHandler;
import net.blep.modtech.core.networking.NetworkManagerModtech;
import net.blep.modtech.core.util.LogHelper;
import net.blep.modtech.core.worldgen.WorldGeneratorModtechOres;
import net.blep.modtech.items.ItemHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

import java.io.File;

/**
 * Created by Kelan on 03/02/2016.
 */
public abstract class Proxy implements IProxy
{
    private NetworkManagerModtech networkManagerModtech;

    public static final Proxy get()
    {
        if (ModTech.PROXY == null)
            throw new RuntimeException("Failed to load proxy for ModTech; it was null or didn't have a side");
        return ModTech.PROXY;
    }

    public void preInit(FMLPreInitializationEvent event)
    {
        LogHelper.info("Pre-Initializing ModTech");
        registerConfigs(event.getSuggestedConfigurationFile());
        registerBlocks();
        registerItems();
    }

    public void init(FMLInitializationEvent event)
    {
        LogHelper.info("Initializing ModTech");
        registerNetwork();
        registerWorldGenerators();
    }

    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.info("Post-Initializing ModTech");
    }

    public NetworkManagerModtech getNetworkManager()
    {
        if (networkManagerModtech == null)
            networkManagerModtech = new NetworkManagerModtech();

        return networkManagerModtech;
    }

    @Override
    public void registerBlocks()
    {
        LogHelper.info("Registering blocks");
        BlockHandler.registerBlocks();
    }

    @Override
    public void registerItems()
    {
        LogHelper.info("Registering items");
        ItemHandler.registerItems();
    }

    @Override
    public void registerNetwork()
    {
        LogHelper.info("Setting up network manager");
        getNetworkManager().registerPackets();
    }

    @Override
    public void registerRenderers() {}

    @Override
    public void registerConfigs(File file)
    {
        LogHelper.info("Loading ModTech config files");
        ConfigHandler.init(file);
    }

    @Override
    public void registerRecipes()
    {
        LogHelper.info("Adding recipes");
    }

    @Override
    public void registerWorldGenerators()
    {
        LogHelper.info("Setting up world generator");
        GameRegistry.registerWorldGenerator(new WorldGeneratorModtechOres(), 10);
    }

    public abstract RenderingHandler getRenderers();

    public abstract World getWorld();

    public abstract EntityPlayerSP getPlayerSP();

    public abstract EntityPlayerMP getPlayerMP();

    public abstract Minecraft getMinecraftClient();

    public abstract MinecraftServer getMinecraftServer();
}
