package net.blep.modtech.client.rendering.blocks.tesr;

import net.blep.modtech.blocks.BlockHandler;
import net.blep.modtech.blocks.tileentity.TileEntityMachine;
import net.blep.modtech.blocks.tileentity.machines.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Kelan on 06/02/2016.
 */
public class RendererMachine extends TESRModtechBase
{
    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
    {
        glPushMatrix();
        glTranslated(x, y, z);
        RenderBlocks renderBlocks = new RenderBlocks(tile.getWorldObj());
        if (tile instanceof TileEntityFurnace)
        {
            doRenderFurnace((TileEntityFurnace) tile, x, y, z, f, renderBlocks);
        }
        else if (tile instanceof TileEntityCrusher)
        {
            doRenderCrusher((TileEntityCrusher) tile, x, y, z, f, renderBlocks);
        }
        else if (tile instanceof TileEntityCompressor)
        {
            doRenderCompressor((TileEntityCompressor) tile, x, y, z, f, renderBlocks);
        }
        else if (tile instanceof TileEntityCentrifuge)
        {
            doRenderCentrifuge((TileEntityCentrifuge) tile, x, y, z, f, renderBlocks);
        }
        else if (tile instanceof TileEntityElectrolyser)
        {
            doRenderElecrtolyyser((TileEntityElectrolyser) tile, x, y, z, f, renderBlocks);
        }
        else if (tile instanceof TileEntityPurifier)
        {
            doRenderPurifier((TileEntityPurifier) tile, x, y, z, f, renderBlocks);
        }
        else if (tile instanceof TileEntityEnricher)
        {
            doRenderEnricher((TileEntityEnricher) tile, x, y, z, f, renderBlocks);
        }
        else if (tile instanceof TileEntityChemicalReactor)
        {
            doRenderChemicalReactor((TileEntityChemicalReactor) tile, x, y, z, f, renderBlocks);
        }
        else if (tile instanceof TileEntityMetalBender)
        {
            doRenderMetalBender((TileEntityMetalBender) tile, x, y, z, f, renderBlocks);
        }
        else if (tile instanceof TileEntitySlagFurnace)
        {
            doRenderSlagFurnace((TileEntitySlagFurnace) tile, x, y, z, f, renderBlocks);
        }
        glTranslated(-x, -y, -z);
        glPopMatrix();
    }

    private void doRenderFurnace(TileEntityFurnace tile, double x, double y, double z, float f, RenderBlocks renderBlocks)
    {
        renderStandardBlock(tile.blockType, tile.xCoord, tile.yCoord, tile.zCoord, tile.getBlockMetadata(), 1.0F);
    }

    private void doRenderCrusher(TileEntityCrusher tile, double x, double y, double z, float f, RenderBlocks renderBlocks)
    {
//        getRenderblocks().renderStandardBlock(Blocks.stone, tile.xCoord, tile.yCoord, tile.zCoord);
    }

    private void doRenderCompressor(TileEntityCompressor tile, double x, double y, double z, float f, RenderBlocks renderBlocks)
    {
//        getRenderblocks().renderStandardBlock(Blocks.stone, tile.xCoord, tile.yCoord, tile.zCoord);
    }

    private void doRenderCentrifuge(TileEntityCentrifuge tile, double x, double y, double z, float f, RenderBlocks renderBlocks)
    {
//        getRenderblocks().renderStandardBlock(Blocks.stone, tile.xCoord, tile.yCoord, tile.zCoord);
    }

    private void doRenderElecrtolyyser(TileEntityElectrolyser tile, double x, double y, double z, float f, RenderBlocks renderBlocks)
    {
//        getRenderblocks().renderStandardBlock(Blocks.stone, tile.xCoord, tile.yCoord, tile.zCoord);
    }

    private void doRenderPurifier(TileEntityPurifier tile, double x, double y, double z, float f, RenderBlocks renderBlocks)
    {
//        getRenderblocks().renderStandardBlock(Blocks.stone, tile.xCoord, tile.yCoord, tile.zCoord);
    }

    private void doRenderEnricher(TileEntityEnricher tile, double x, double y, double z, float f, RenderBlocks renderBlocks)
    {
//        getRenderblocks().renderStandardBlock(Blocks.stone, tile.xCoord, tile.yCoord, tile.zCoord);
    }

    private void doRenderChemicalReactor(TileEntityChemicalReactor tile, double x, double y, double z, float f, RenderBlocks renderBlocks)
    {
//        getRenderblocks().renderStandardBlock(Blocks.stone, tile.xCoord, tile.yCoord, tile.zCoord);
    }

    private void doRenderMetalBender(TileEntityMetalBender tile, double x, double y, double z, float f, RenderBlocks renderBlocks)
    {
//        getRenderblocks().renderStandardBlock(Blocks.stone, tile.xCoord, tile.yCoord, tile.zCoord);
    }

    private void doRenderSlagFurnace(TileEntitySlagFurnace tile, double x, double y, double z, float f, RenderBlocks renderBlocks)
    {
//        getRenderblocks().renderStandardBlock(Blocks.stone, tile.xCoord, tile.yCoord, tile.zCoord);
    }
}
