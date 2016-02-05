package net.blep.modtech.core.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.blep.modtech.client.rendering.RenderingHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

/**
 * Created by Kelan on 03/02/2016.
 */
public class ClientProxy extends Proxy
{
    private static RenderingHandler renderers;

    public RenderingHandler getRenderers()
    {
        return renderers;
    }

    @Override
    public World getWorld()
    {
        return Minecraft.getMinecraft().theWorld;
    }

    @Override
    public EntityPlayerSP getPlayerSP()
    {
        return Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public EntityPlayerMP getPlayerMP()
    {
        throw new RuntimeException("Cannot access server-side player instance from client");
    }

    @Override
    public Minecraft getMinecraftClient()
    {
        return Minecraft.getMinecraft();
    }

    @Override
    public MinecraftServer getMinecraftServer()
    {
        return MinecraftServer.getServer();
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        System.out.println("ClientProxy preInit");
        renderers = new RenderingHandler();
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        System.out.println("ClientProxy init");
        renderers.registerRenderers();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        System.out.println("ClientProxy postInit");
    }
}
