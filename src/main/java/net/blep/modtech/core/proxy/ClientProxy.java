package net.blep.modtech.core.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.blep.modtech.client.rendering.RenderingHandler;
import net.blep.modtech.core.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

/**
 * Created by Kelan on 03/02/2016.
 */
public class ClientProxy extends ModHandler
{
    private RenderingHandler renderers;

    public ClientProxy()
    {
        LogHelper.info("We are on the client!");
    }

    @Override
    public RenderingHandler getRenderers()
    {
        if (renderers == null)
            renderers = new RenderingHandler();
        return renderers;
    }

    public World getWorld()
    {
        return Minecraft.getMinecraft().theWorld;
    }

    public EntityPlayerSP getPlayerSP()
    {
        return Minecraft.getMinecraft().thePlayer;
    }

    public EntityPlayerMP getPlayerMP()
    {
        throw new RuntimeException("Cannot access server-side player instance from client");
    }

    public Minecraft getMinecraftClient()
    {
        return Minecraft.getMinecraft();
    }

    public MinecraftServer getMinecraftServer()
    {
        return MinecraftServer.getServer();
    }

    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        renderers = new RenderingHandler();
    }

    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        renderers.registerRenderers();
    }

    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }
}
