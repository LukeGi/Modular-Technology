package net.blep.modtech.client.rendering;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.blep.modtech.client.rendering.blocks.isbrh.ColouredBlockRenderer;

/**
 * Created by Kelan on 03/02/2016.
 */
public class RenderingHandler
{
    public int RENDERER_COLOURED_BLOCK = RenderingRegistry.getNextAvailableRenderId();

    public void registerRenderers()
    {
        System.out.println("Registering Renderers");
        RenderingRegistry.registerBlockHandler(RENDERER_COLOURED_BLOCK, new ColouredBlockRenderer());
    }
}
