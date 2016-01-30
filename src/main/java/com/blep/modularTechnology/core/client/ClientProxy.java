package com.blep.modularTechnology.core.client;

import com.blep.modularTechnology.core.common.CommonProxy;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import com.blep.modularTechnology.core.client.renderer.item.ItemRendererDismountedTile;
import com.blep.modularTechnology.core.client.renderer.block.isbrh.ColouredBlockRenderer;
import net.blep.modularTechnology.common.core.ModContent;
import com.blep.modularTechnology.core.common.ModularTechnology;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

import java.util.List;

/**
 * @author TheEpicTekkit
 */
public class ClientProxy extends CommonProxy
{
//    /**
//     * A rotation matrix for machine textures.
//     * Because machines are rotated by changing the metadata, it goes [meta][side]
//     */
//    public static int[][] MACHINE_ICON_MATRIX = new int[][]
//            {
//                    //TODO: setup matrix for up/down orientation
//                    {2, 0, 0, 0, 0, 0},
//                    {0, 2, 0, 0, 0, 0},
//
//                    {0, 1, 2, 3, 4, 5},
//                    {0, 1, 3, 2, 5, 4},
//                    {0, 1, 5, 4, 2, 3},
//                    {0, 1, 4, 5, 3, 2},
//            };
//
//    public static int renderID_ColouredBlock = RenderingRegistry.getNextAvailableRenderId();
//
//    public void preInit(FMLPreInitializationEvent event)
//    {
//        ModularTechnology.LOGGER.info("Pre-preInit on client");
//    }
//
//    public void init(FMLInitializationEvent event)
//    {
//        ModularTechnology.LOGGER.info("Init on client");
//
//        RenderingRegistry.registerBlockHandler(new ColouredBlockRenderer(renderID_ColouredBlock));
//
//        MinecraftForgeClient.registerItemRenderer(ModContent.getTechModItems().dismountedMachine, new ItemRendererDismountedTile());
//    }
//
//    public void postInit(FMLPostInitializationEvent event)
//    {
//        ModularTechnology.LOGGER.info("Post-preInit on client");
//    }
//
//    public List<EntityPlayer> getPlayers()
//    {
//        throw new RuntimeException("Unable to access server-side variables from the client");
//    }
//
//    public EntityPlayer getPlayer()
//    {
//        return FMLClientHandler.instance().getClient().thePlayer;
//    }
//
//    public World getWorld()
//    {
//        return FMLClientHandler.instance().getClient().theWorld;
//    }
//
//    public void registerTESR(Class<? extends TileEntity> tile, TileEntitySpecialRenderer renderer)
//    {
//        ClientRegistry.bindTileEntitySpecialRenderer(tile, renderer);
//    }
}
