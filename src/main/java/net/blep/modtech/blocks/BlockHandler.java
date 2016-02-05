package net.blep.modtech.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.blep.modtech.blocks.block.BlockBigFurnace;
import net.blep.modtech.blocks.block.BlockMetal;
import net.blep.modtech.blocks.block.BlockNuke;
import net.blep.modtech.blocks.block.BlockOre;
import net.blep.modtech.core.util.BlockTexture;
import net.blep.modtech.items.itemblock.ItemBlockMetalBlock;
import net.blep.modtech.items.itemblock.ItemBlockOre;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;

/**
 * Created by Kelan on 24/01/2016.
 */
public class BlockHandler
{
    public static final Block BLOCK_BIGFURNACE = new BlockBigFurnace().setTextureConfiguration(new BlockTexture(new String[]{"bottom", "top", "front", "back", "left", "right"}));
    public static final Block BLOCK_NUKE = new BlockNuke().setTextureConfiguration(new BlockTexture(new String[]{"nukeBottom", "nukeTop", "nuke", "nuke", "nuke", "nuke"}));
    public static final Block BLOCK_METAL_ORE = new BlockOre();
    public static final Block BLOCK_METAL_BLOCK = new BlockMetal();

    public static void registerBlocks()
    {
        GameRegistry.registerBlock(BLOCK_BIGFURNACE, "bigfurnace");
        GameRegistry.registerBlock(BLOCK_NUKE, "nuke");
        GameRegistry.registerBlock(BLOCK_METAL_ORE, ItemBlockMetalBlock.class, "blockOre");
        GameRegistry.registerBlock(BLOCK_METAL_BLOCK, ItemBlockMetalBlock.class, "blockMetal");
    }
}
