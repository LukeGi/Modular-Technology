package net.blep.modtech.core.proxy;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.blep.modtech.client.rendering.RenderingHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

/**
 * Created by Kelan on 03/02/2016.
 */
public abstract class Proxy
{
    @SidedProxy(clientSide = "net.blep.modtech.core.proxy.ClientProxy", serverSide = "net.blep.modtech.core.proxy.ServerProxy")
    public static Proxy PROXY;

    public abstract RenderingHandler getRenderers();

    public abstract World getWorld();

    public abstract EntityPlayerSP getPlayerSP();

    public abstract EntityPlayerMP getPlayerMP();

    public abstract Minecraft getMinecraftClient();

    public abstract MinecraftServer getMinecraftServer();

    public abstract void preInit(FMLPreInitializationEvent event);

    public abstract void init(FMLInitializationEvent event);

    public abstract void postInit(FMLPostInitializationEvent event);

    public static final Proxy get()
    {
        return PROXY;
    }
}
