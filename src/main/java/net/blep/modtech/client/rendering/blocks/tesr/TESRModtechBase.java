package net.blep.modtech.client.rendering.blocks.tesr;

import net.blep.modtech.core.proxy.ModHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

/**
 * Created by Kelan on 06/02/2016.
 */
public abstract class TESRModtechBase extends TileEntitySpecialRenderer
{
    public abstract void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f);

    public void renderStandardBlock(Block block, int x, int y, int z, int meta, float scale)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
        IIcon icon;
        Tessellator tessellator = Tessellator.instance;
        int l = block.getMixedBrightnessForBlock(ModHandler.get().getWorld(), x, y, z);

        //bottom
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque(x, y, z);
        tessellator.setBrightness((int) (l * 0.50F));
        tessellator.setColorRGBA_F(1, 1, 1, 1);
        icon = block.getIcon(0, meta);
        tessellator.addVertexWithUV(0.0F * scale, 0.0F * scale, 0.0F * scale, icon.getMinU(), icon.getMinV());
        tessellator.addVertexWithUV(1.0F * scale, 0.0F * scale, 0.0F * scale, icon.getMaxU(), icon.getMinV());
        tessellator.addVertexWithUV(1.0F * scale, 0.0F * scale, 1.0F * scale, icon.getMaxU(), icon.getMaxV());
        tessellator.addVertexWithUV(0.0F * scale, 0.0F * scale, 1.0F * scale, icon.getMinU(), icon.getMaxV());
        tessellator.draw();

        //top
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque(x, y, z);
        tessellator.setBrightness((int) (l * 1.0F));
        tessellator.setColorRGBA_F(1, 1, 1, 1);
        icon = block.getIcon(1, meta);
        tessellator.addVertexWithUV(0.0F * scale, 1.0F * scale, 0.0F * scale, icon.getMinU(), icon.getMinV());
        tessellator.addVertexWithUV(0.0F * scale, 1.0F * scale, 1.0F * scale, icon.getMinU(), icon.getMaxV());
        tessellator.addVertexWithUV(1.0F * scale, 1.0F * scale, 1.0F * scale, icon.getMaxU(), icon.getMaxV());
        tessellator.addVertexWithUV(1.0F * scale, 1.0F * scale, 0.0F * scale, icon.getMaxU(), icon.getMinV());
        tessellator.draw();

        //front
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque(x, y, z);
        tessellator.setBrightness((int) (l * 0.80F));
        tessellator.setColorRGBA_F(1, 1, 1, 1);
        icon = block.getIcon(2, meta);
        tessellator.addVertexWithUV(0.0F * scale, 0.0F * scale, 0.0F * scale, icon.getMaxU(), icon.getMaxV());
        tessellator.addVertexWithUV(0.0F * scale, 1.0F * scale, 0.0F * scale, icon.getMaxU(), icon.getMinV());
        tessellator.addVertexWithUV(1.0F * scale, 1.0F * scale, 0.0F * scale, icon.getMinU(), icon.getMinV());
        tessellator.addVertexWithUV(1.0F * scale, 0.0F * scale, 0.0F * scale, icon.getMinU(), icon.getMaxV());
        tessellator.draw();

        //back
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque(x, y, z);
        tessellator.setBrightness((int) (l * 0.80F));
        tessellator.setColorRGBA_F(1, 1, 1, 1);
        icon = block.getIcon(3, meta);
        tessellator.addVertexWithUV(0.0F * scale, 0.0F * scale, 1.0F * scale, icon.getMinU(), icon.getMaxV());
        tessellator.addVertexWithUV(1.0F * scale, 0.0F * scale, 1.0F * scale, icon.getMaxU(), icon.getMaxV());
        tessellator.addVertexWithUV(1.0F * scale, 1.0F * scale, 1.0F * scale, icon.getMaxU(), icon.getMinV());
        tessellator.addVertexWithUV(0.0F * scale, 1.0F * scale, 1.0F * scale, icon.getMinU(), icon.getMinV());
        tessellator.draw();

        //right
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque(x, y, z);
        tessellator.setBrightness((int) (l * 0.60F));
        tessellator.setColorRGBA_F(1, 1, 1, 1);
        icon = block.getIcon(4, meta);
        tessellator.addVertexWithUV(0.0F * scale, 0.0F * scale, 0.0F * scale, icon.getMinU(), icon.getMaxV());
        tessellator.addVertexWithUV(0.0F * scale, 0.0F * scale, 1.0F * scale, icon.getMaxU(), icon.getMaxV());
        tessellator.addVertexWithUV(0.0F * scale, 1.0F * scale, 1.0F * scale, icon.getMaxU(), icon.getMinV());
        tessellator.addVertexWithUV(0.0F * scale, 1.0F * scale, 0.0F * scale, icon.getMinU(), icon.getMinV());
        tessellator.draw();

        //left
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque(x, y, z);
        tessellator.setBrightness((int) (l * 0.60F));
        tessellator.setColorRGBA_F(1, 1, 1, 1);
        icon = block.getIcon(5, meta);
        tessellator.addVertexWithUV(1.0F * scale, 0.0F * scale, 0.0F * scale, icon.getMaxU(), icon.getMaxV());
        tessellator.addVertexWithUV(1.0F * scale, 1.0F * scale, 0.0F * scale, icon.getMaxU(), icon.getMinV());
        tessellator.addVertexWithUV(1.0F * scale, 1.0F * scale, 1.0F * scale, icon.getMinU(), icon.getMinV());
        tessellator.addVertexWithUV(1.0F * scale, 0.0F * scale, 1.0F * scale, icon.getMinU(), icon.getMaxV());
        tessellator.draw();
    }


}
