package net.blep.modtech.core.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.blep.modtech.client.rendering.RenderingHandler;
import net.blep.modtech.core.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

/**
 * Created by Kelan on 03/02/2016.
 */
public class ServerProxy extends Proxy
{
    public ServerProxy()
    {
        LogHelper.info("Oh, e are on the server!");
    }

    @Override
    public RenderingHandler getRenderers()
    {
        throw new RuntimeException("Cannot access client-side RenderingHandler instance from server");
    }

    @Override
    public World getWorld()
    {
        throw new RuntimeException("Cannot access client-side world instance from server");
    }

    @Override
    public EntityPlayerSP getPlayerSP()
    {
        throw new RuntimeException("Cannot access client-side player instance from server");
    }

    @Override
    public EntityPlayerMP getPlayerMP()
    {
        return null;
    }

    @Override
    public Minecraft getMinecraftClient()
    {
        throw new RuntimeException("Cannot access client-side instance of Minecraft.getMinecraft() from server");
    }

    @Override
    public MinecraftServer getMinecraftServer()
    {
        return MinecraftServer.getServer();
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }
}
