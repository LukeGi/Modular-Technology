package net.blep.modularTechnology.common.tech;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.blep.modularTechnology.core.common.ModularTechnology;
import net.blep.modularTechnology.common.core.Proxy;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;

/**
 * @author TheEpicTekkit
 */
public class ServerProxy extends Proxy
{

    @Override
    public ServerProxy instance()
    {
        return this;
    }

    @SideOnly(Side.SERVER)
    public void preInit(FMLPreInitializationEvent event)
    {
        ModularTechnology.LOGGER.info("Pre-preInit on server");
    }

    @SideOnly(Side.SERVER)
    public void init(FMLInitializationEvent event)
    {
        ModularTechnology.LOGGER.info("Init on server");
    }

    @SideOnly(Side.SERVER)
    public void postInit(FMLPostInitializationEvent event)
    {
        ModularTechnology.LOGGER.info("Post-preInit on server");
    }

    @Override
    public List<EntityPlayer> getPlayers()
    {
        WorldServer[] worlds = MinecraftServer.getServer().worldServers;
        List<EntityPlayer> players = Lists.newArrayList();

        for (WorldServer world : worlds)
        {
            for (Object player : world.playerEntities)
            {
                if (player instanceof EntityPlayer)
                {
                    players.add((EntityPlayer) player);
                }
            }
        }
        return players;
    }

    @Override
    public EntityPlayer getPlayer()
    {
        throw new RuntimeException("Unable to access client-side variables from the server");
    }

    @Override
    public World getWorld()
    {
        throw new RuntimeException("Unable to access client-side variables from the server");
    }

    @Override
    public void registerTESR(Class<? extends TileEntity> tile, TileEntitySpecialRenderer renderer)
    {
        throw new RuntimeException("Attempted to register a TESR on the server. This is bad.");
    }
}
