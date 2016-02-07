package net.blep.modtech.blocks.block;

import net.blep.modtech.api.BlockProperties;
import net.blep.modtech.api.BlockTexture;
import net.blep.modtech.blocks.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by Kelan on 07/02/2016.
 */
public class BlockTest extends ModBlock
{
    public BlockTest()
    {
        super(BlockProperties.GENERIC_MACHINE, "test");
        setTextureConfiguration(new BlockTexture(new String[][] {{"bottom", "top", "front", "back", "right", "left"}}, null));
        setCreativeTab(CreativeTabs.tabBlock);
    }
}
