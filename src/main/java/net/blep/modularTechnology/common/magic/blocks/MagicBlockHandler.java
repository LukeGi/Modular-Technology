package net.blep.modularTechnology.common.magic.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.blep.modularTechnology.common.magic.MTMagic;
import net.minecraft.block.Block;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class MagicBlockHandler extends MTMagic
{
    public static Block treeFarm;

    public static void initBlocks()
    {
        treeFarm = new BlockTreeFarm();
    }

    public static void registerMagicBlock(MagicBlock block, String name)
    {
        GameRegistry.registerBlock(block, name);
        block.setCreativeTab(MagicTab);
    }
    public static void registerMagicBlock(MagicBlockContainer block, String name)
    {
        GameRegistry.registerBlock(block, name);
        block.setCreativeTab(MagicTab);
    }
}