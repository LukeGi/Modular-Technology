package modulartechnology.client.renderer.block.isbrh;

/**
 * @author TheEpicTekkit
 */
public class ColouredBlockRenderer //implements ISimpleBlockRenderingHandler
{
//    private int renderPass;
//
//    public ColouredBlockRenderer(int renderPass)
//    {
//        this.renderPass = renderPass;
//        System.out.println("Initializing Coloured block renderer");
//    }
//
//    @Override
//    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
//    {
//        Tessellator tessellator = Tessellator.instance;
//
//        IIcon[] blockTextures = new IIcon[6];
//
//        for (int i = 0; i < 6; i++)
//        {
//            blockTextures[i] = block.getBlockTextureFromSide(i) != null ? block.getBlockTextureFromSide(i) : Blocks.stone.getBlockTextureFromSide(i);
//        }
//
//        block.setBlockBoundsForItemRender();
//        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
//        float f2 = 0.0F;
//
//        int r = 255, g = 255, b = 255;
//
//        if (block != null)
//        {
//            if (block instanceof BlockOre)
//            {
//                BlockOre ore = (BlockOre) block;
//                r = (int) (ore.material.getColour().getX() * 255);
//                g = (int) (ore.material.getColour().getY() * 255);
//                b = (int) (ore.material.getColour().getZ() * 255);
//                GL11.glScalef(100F / 99F, 100F / 99F, 100F / 99F);
//            }
//
//            if (block instanceof BlockMetal)
//            {
//                BlockMetal metal = (BlockMetal) block;
//                r = (int) (metal.material.getColour().getX() * 255);
//                g = (int) (metal.material.getColour().getY() * 255);
//                b = (int) (metal.material.getColour().getZ() * 255);
//
//                tessellator.startDrawingQuads();
//                tessellator.setColorRGBA(255, 255, 255, 255);
//                tessellator.setNormal(0.0F, 1.0F, 0.0F);
//                renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, Blocks.stone.getBlockTextureFromSide(1));
//                tessellator.draw();
//                tessellator.startDrawingQuads();
//                tessellator.setColorRGBA(r, g, b, 255);
//                tessellator.setNormal(0.0F, 1.0F, 0.0F);
//                renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, blockTextures[1]);
//                tessellator.draw();
//
//                tessellator.startDrawingQuads();
//                tessellator.setColorRGBA(255, 255, 255, 255);
//                tessellator.setNormal(0.0F, 0.0F, -1F);
//                tessellator.setTranslation(0.0F, 0.0F, f2);
//                renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, Blocks.stone.getBlockTextureFromSide(2));
//                tessellator.setTranslation(0.0F, 0.0F, -f2);
//                tessellator.draw();
//                tessellator.startDrawingQuads();
//                tessellator.setColorRGBA(r, g, b, 255);
//                tessellator.setNormal(0.0F, 0.0F, -1F);
//                tessellator.setTranslation(0.0F, 0.0F, f2);
//                renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, blockTextures[2]);
//                tessellator.setTranslation(0.0F, 0.0F, -f2);
//                tessellator.draw();
//
//                tessellator.startDrawingQuads();
//                tessellator.setColorRGBA(255, 255, 255, 255);
//                tessellator.setNormal(0.0F, 0.0F, 1.0F);
//                tessellator.setTranslation(0.0F, 0.0F, -f2);
//                renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, Blocks.stone.getBlockTextureFromSide(3));
//                tessellator.setTranslation(0.0F, 0.0F, f2);
//                tessellator.draw();
//                tessellator.startDrawingQuads();
//                tessellator.setColorRGBA(r, g, b, 255);
//                tessellator.setNormal(0.0F, 0.0F, 1.0F);
//                tessellator.setTranslation(0.0F, 0.0F, -f2);
//                renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, blockTextures[3]);
//                tessellator.setTranslation(0.0F, 0.0F, f2);
//                tessellator.draw();
//
//                tessellator.startDrawingQuads();
//                tessellator.setColorRGBA(255, 255, 255, 255);
//                tessellator.setNormal(-1F, 0.0F, 0.0F);
//                tessellator.setTranslation(f2, 0.0F, 0.0F);
//                renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, Blocks.stone.getBlockTextureFromSide(4));
//                tessellator.setTranslation(-f2, 0.0F, 0.0F);
//                tessellator.draw();
//                tessellator.startDrawingQuads();
//                tessellator.setColorRGBA(r, g, b, 255);
//                tessellator.setNormal(-1F, 0.0F, 0.0F);
//                tessellator.setTranslation(f2, 0.0F, 0.0F);
//                renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, blockTextures[4]);
//                tessellator.setTranslation(-f2, 0.0F, 0.0F);
//                tessellator.draw();
//
//                tessellator.startDrawingQuads();
//                tessellator.setColorRGBA(255, 255, 255, 255);
//                tessellator.setNormal(1.0F, 0.0F, 0.0F);
//                tessellator.setTranslation(-f2, 0.0F, 0.0F);
//                renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, Blocks.stone.getBlockTextureFromSide(5));
//                tessellator.setTranslation(f2, 0.0F, 0.0F);
//                tessellator.draw();
//                tessellator.startDrawingQuads();
//                tessellator.setColorRGBA(r, g, b, 255);
//                tessellator.setNormal(1.0F, 0.0F, 0.0F);
//                tessellator.setTranslation(-f2, 0.0F, 0.0F);
//                renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, blockTextures[5]);
//                tessellator.setTranslation(f2, 0.0F, 0.0F);
//                tessellator.draw();
//
//                tessellator.setTranslation(0.0F, 0.0F, 0.0F);
//
//                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
//
//                if (block instanceof BlockMetal)
//                {
//                    GL11.glScalef(99F / 100F, 99F / 100F, 99F / 100F);
//                    renderer.renderBlockAsItem(Blocks.stone, 1, 1);
//                }
//            }
//        }
//    }
//
//    @Override
//    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
//    {
//        if (block instanceof BlockOre)
//        {
//            renderer.renderStandardBlock(Blocks.stone, x, y, z);
//        }
//        renderer.renderStandardBlock(block, x, y, z);
//        return true;
//    }
//
//    @Override
//    public boolean shouldRender3DInInventory(int modelId)
//    {
//        return true;
//    }
//
//    @Override
//    public int getRenderId()
//    {
//        return renderPass;
//    }
}