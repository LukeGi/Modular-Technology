package net.blep.modularTechnology.client.core.renderer;

import net.blep.modularTechnology.common.core.Proxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author TheEpicTekkit
 */
public class ItemRendererDismountedTile implements IItemRenderer
{
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        try
        {
            Block block = null;
            int meta = 0;

            if (item.hasTagCompound())
            {
                block = Block.getBlockById(item.getTagCompound().getInteger("block"));
                meta = item.getTagCompound().getInteger("metadata");
            }

            if (block != null)
            {
                RenderBlocks rb = new RenderBlocks(Proxy.proxy.getWorld());
                block.setBlockBoundsForItemRender();

                if (type.equals(ItemRenderType.ENTITY))
                {
                    glPushMatrix();
                    glScalef(0.5F, 0.5F, 0.5F);
                    rb.renderBlockAsItem(block, meta, 1.0F);
                    glScalef(2.0F, 2.0F, 2.0F);
                    glPopMatrix();
                } else
                {
                    glPushMatrix();
                    glTranslatef(0.5F, 0.5F, 0.5F);
                    rb.renderBlockAsItem(block, meta, 1.0F);
                    glTranslatef(-0.5F, -0.5F, -0.5F);
                    glPopMatrix();
                }
            }
        } catch (Exception e)
        {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
}
