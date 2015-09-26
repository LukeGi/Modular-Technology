package net.blep.modularTechnology.common.core;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author TheEpicTekkit
 */
public abstract class Proxy implements IInitializable
{

    @SidedProxy(clientSide = "net.blep.modularTechnology.client.core.ClientProxy", serverSide = "net.blep.modularTechnology.common.tech.ServerProxy")
    public static Proxy proxy;

    public abstract void preInit(FMLPreInitializationEvent event);

    public abstract void init(FMLInitializationEvent event);

    public abstract void postInit(FMLPostInitializationEvent event);

    public abstract List<EntityPlayer> getPlayers();

    public abstract EntityPlayer getPlayer();

    public abstract World getWorld();

    public abstract void registerTESR(Class<? extends TileEntity> tile, TileEntitySpecialRenderer renderer);
}
