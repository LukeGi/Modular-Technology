package net.blep.modularTechnology.common.magic;

import cpw.mods.fml.common.registry.GameRegistry;
import net.blep.modularTechnology.common.core.ModContent;
import net.blep.modularTechnology.common.magic.multiblocks.TE.TETreeFarm;
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
        GameRegistry.registerTileEntity(TETreeFarm.class, MOD_ID + "tree_farm");
        return this;
    }
}

//public static Block blockGemInfuser;
//        GameRegistry.registerBlock(blockGemInfuser, "gem_infuser");
//        GameRegistry.registerTileEntity(TEGemInfuser.class, "modtech:gem_infuser");