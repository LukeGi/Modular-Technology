package net.blep.modularTechnology.common.magic;

import net.blep.modularTechnology.common.core.ModContent;
import net.minecraft.block.Block;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class MagicBlockHandler extends ModContent
{
    public static Block treeFarm;

    public MagicBlockHandler initBlocks()
    {
        treeFarm = new BlockTreeFarm().setBlockName("tree_farm");
        return this;
    }
}

//public static Block blockGemInfuser;
//        GameRegistry.registerBlock(blockGemInfuser, "gem_infuser");
//        GameRegistry.registerTileEntity(TEGemInfuser.class, "modtech:gem_infuser");