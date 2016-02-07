package net.blep.modtech.client.rendering;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.blep.modtech.blocks.tileentity.TileEntityMachine;
import net.blep.modtech.client.rendering.blocks.isbrh.RendererColouredBlock;
import net.blep.modtech.client.rendering.blocks.tesr.RendererMachine;
import net.blep.modtech.core.util.EnumMachineProperties;

/**
 * Created by Kelan on 03/02/2016.
 */
public class RenderingHandler
{
    public int RENDERER_COLOURED_BLOCK = RenderingRegistry.getNextAvailableRenderId();

    public void registerRenderers()
    {
        System.out.println("Registering Renderers");
        RenderingRegistry.registerBlockHandler(RENDERER_COLOURED_BLOCK, new RendererColouredBlock());

        for (int i = 0; i < EnumMachineProperties.values().length; i++)
        {
            ClientRegistry.bindTileEntitySpecialRenderer(EnumMachineProperties.values()[i].getTileClass(), new RendererMachine());
        }
    }
}
