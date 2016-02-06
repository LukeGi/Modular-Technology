package net.blep.modtech.core.util;

import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class HelpfulMethods {

    public static void removeBlockSafely(World world, int x, int y, int z, boolean dropItem) {
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        if (dropItem)
            block.dropBlockAsItem(world, x, y, z, meta, 0);
        block.breakBlock(world, x, y, z, block, meta);
        world.setBlockToAir(x, y, z);
    }
}
