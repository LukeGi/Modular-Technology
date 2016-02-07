package net.blep.modtech.client.rendering.blocks.tesr;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Kelan on 06/02/2016.
 */
public abstract class TESRModtechBase extends TileEntitySpecialRenderer
{
    public abstract void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f);

    public void renderStandardBlock(Block block, double distanceX, double distanceY, double distanceZ, int x, int y, int z, RenderBlocks renderBlocks)
    {
        glPushMatrix();
        glTranslated(distanceX, distanceY, distanceZ);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setTranslation(-x, -y, -z);
        renderBlocks.renderBlockByRenderType(block, x, y, z);
        tessellator.draw();
        tessellator.setTranslation(0, 0, 0);
        glPopMatrix();
    }


}
