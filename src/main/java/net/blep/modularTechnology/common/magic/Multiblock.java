package net.blep.modularTechnology.common.magic;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Map;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class Multiblock
{
    private Map<Block, int[][]> multiblock = Maps.newHashMap();
    public static Multiblock sugarfarm = new Multiblock(new Block[]{Blocks.flowing_water, Blocks.sand, Blocks.reeds}, new int[][][]{{{0, 0, 0}, {1, 0, 2}, {2, 0, -1}, {-1, 0, -2}, {-2, 0, 1}}, {{1, 0, 0}, {0, 0, 1}, {-1, 0, 0}, {0, 0, -1}, {2, 0, 2}, {1, 0, 3}, {0, 0, 2}, {1, 0, 1}, {3, 0, -1}, {2, 0, 0}, {1, 0, -1}, {2, 0, -2}, {0, 0, -2}, {-1, 0, -1}, {-2, 0, -2}, {-1, 0, -3}, {-1, 0, 1}, {-2, 0, 2}, {-3, 0, 1}, {-2, 0, 0},}, {{1, 1, 0}, {0, 1, 1}, {-1, 1, 0}, {0, 1, -1}, {2, 1, 2}, {1, 1, 3}, {0, 1, 2}, {1, 1, 1}, {3, 1, -1}, {2, 1, 0}, {1, 1, -1}, {2, 1, -2}, {0, 1, -2}, {-1, 1, -1}, {-2, 1, -2}, {-1, 1, -3}, {-1, 1, 1}, {-2, 1, 2}, {-3, 1, 1}, {-2, 1, 0},}});
    public static Multiblock treefarm = new Multiblock(new Block[]
            {
                    MagicBlockHandler.treeFarm,
                    Blocks.lapis_block,
                    Blocks.iron_block
            }, new int[][][]
            {
                    {
                            {0, 0, 0}
                    },
                    {
                            {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                    },
                    {
                            {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                    }
            }
    );

    public Multiblock(Block[] b, int[][][] p)
    {
        for (int i = 0; i < b.length; i++)
            multiblock.put(b[i], p[i]);
    }

    public static void createMultiblock(World world, int x, int y, int z, Multiblock multiblock)
    {
        for (Block b : multiblock.multiblock.keySet())
            for (int[] pos : multiblock.multiblock.get(b))
                world.setBlock(x + pos[0], y + pos[1], z + pos[2], b);
    }

    public static boolean checkMultiblock(World world, int x, int y, int z, Multiblock multiblock)
    {
        boolean flag = true;
        for (Block b : multiblock.multiblock.keySet())
            for (int[] pos : multiblock.multiblock.get(b))
                flag = flag && (world.getBlock(x + pos[0], y + pos[1], z + pos[2]).equals(b));
        return flag;
    }
}

