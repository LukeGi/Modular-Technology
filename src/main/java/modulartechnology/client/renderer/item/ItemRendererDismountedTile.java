package modulartechnology.client.renderer.item;

/**
 * @author TheEpicTekkit
 */
public class ItemRendererDismountedTile //implements IItemRenderer
{
//    @Override
//    public boolean handleRenderType(ItemStack item, ItemRenderType type)
//    {
//        return true;
//    }
//
//    @Override
//    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
//    {
//        return true;
//    }
//
//    @Override
//    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
//    {
//        try
//        {
//            Block block = null;
//            int meta = 0;
//
//            if (item.hasTagCompound())
//            {
//                block = Block.getBlockById(item.getTagCompound().getInteger("block"));
//                meta = item.getTagCompound().getInteger("metadata");
//            }
//
//            if (block != null)
//            {
//                RenderBlocks rb = new RenderBlocks(AbstractCommonProxy.proxy.getWorld());
//                block.setBlockBoundsForItemRender();
//
//                if (type.equals(ItemRenderType.ENTITY))
//                {
//                    glPushMatrix();
//                    glScalef(0.5F, 0.5F, 0.5F);
//                    rb.renderBlockAsItem(block, meta, 1.0F);
//                    glScalef(2.0F, 2.0F, 2.0F);
//                    glPopMatrix();
//                } else
//                {
//                    glPushMatrix();
//                    glTranslatef(0.5F, 0.5F, 0.5F);
//                    rb.renderBlockAsItem(block, meta, 1.0F);
//                    glTranslatef(-0.5F, -0.5F, -0.5F);
//                    glPopMatrix();
//                }
//            }
//        } catch (Exception e)
//        {
//            System.out.println("Error: " + e.getLocalizedMessage());
//        }
//    }
}
