package net.blep.modtech.blocks.multiblock;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * Created by Kelan on 24/01/2016.
 */
public class MultiblockChecker
{
    public static boolean isFormed(World world, MultiblockPattern pattern, int masterX, int masterY, int masterZ, int x, int y, int z)
    {
        //master direction/distance
        int dmx = masterX - x;
        int dmy = masterY - y;
        int dmz = masterZ - z;

        for (int i = 0; i < pattern.getxSize(); i++)
        {
            for (int j = 0; j < pattern.getySize(); j++)
            {
                for (int k = 0; k < pattern.getzSize(); k++)
                {
                    int x0 = (x + i) - dmx;
                    int y0 = (y + j) - dmy;
                    int z0 = (z + k) - dmz;

                    Block block = world.getBlock(x0, y0, z0);

                    if (pattern.ignoreAirBlocks() && block == Blocks.air)
                        continue;

                    if (Block.getIdFromBlock(block) != pattern.getBlockMappings().get(pattern.getPattern()[i][j][k]))
                        return false;
                }
            }
        }

        return true;
    }
}
