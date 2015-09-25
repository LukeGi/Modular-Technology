package net.blep.modularTechnology.common.magic.multiblocks;

import com.google.common.collect.Maps;
import net.blep.modularTechnology.common.magic.MagicBlockHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Map;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class Multiblock implements ICreateable, ICheckable
{
    private Map<Block, int[][]> multiblock = Maps.newHashMap();
    public static Multiblock sugarfarm = new Multiblock(new Block[]{Blocks.flowing_water, Blocks.sand, Blocks.reeds}, new int[][][]{{{0, 0, 0}, {1, 0, 2}, {2, 0, -1}, {-1, 0, -2}, {-2, 0, 1}}, {{1, 0, 0}, {0, 0, 1}, {-1, 0, 0}, {0, 0, -1}, {2, 0, 2}, {1, 0, 3}, {0, 0, 2}, {1, 0, 1}, {3, 0, -1}, {2, 0, 0}, {1, 0, -1}, {2, 0, -2}, {0, 0, -2}, {-1, 0, -1}, {-2, 0, -2}, {-1, 0, -3}, {-1, 0, 1}, {-2, 0, 2}, {-3, 0, 1}, {-2, 0, 0},}, {{1, 1, 0}, {0, 1, 1}, {-1, 1, 0}, {0, 1, -1}, {2, 1, 2}, {1, 1, 3}, {0, 1, 2}, {1, 1, 1}, {3, 1, -1}, {2, 1, 0}, {1, 1, -1}, {2, 1, -2}, {0, 1, -2}, {-1, 1, -1}, {-2, 1, -2}, {-1, 1, -3}, {-1, 1, 1}, {-2, 1, 2}, {-3, 1, 1}, {-2, 1, 0},}});
    public static Multiblock deathChamber = new Multiblock(new Block[]{Blocks.obsidian, Blocks.flowing_water, Blocks.glass}, new int[][][]{{{-1, 0, 1}, {0, 0, 1}, {1, 0, 1},{-1, 0, 0}, {0, 0, 0}, {1, 0, 0},{-1, 0, -1}, {0, 0, -1}, {1, 0, -1}, {-1, 1, 1}, {0, 1, 1}, {1, 1, 1},{-1, 1, 0}, {1, 1, 0},{-1, 1, -1}, {0, 1, -1}, {1, 1, -1}, {-1, 2, 1}, {1, 2, 1},{-1, 2, -1}, {1, 2, -1}, {-1, 3, 1}, {0, 3, 1}, {1, 3, 1},{-1, 3, 0}, {0, 3, 0}, {1, 3, 0},{-1, 3, -1}, {0, 3, -1}, {1, 3, -1},},{{0, 1, 0}, {0, 2, 0}},{{0, 2, 1}, {0, 2, -1}, {1, 2, 0}, {-1, 2, 0}}});


    public Multiblock(Block[] b, int[][][] p)
    {
        for (int i = 0; i < b.length; i++)
            multiblock.put(b[i], p[i]);
    }

    @Override
    public void create(World world, int x, int y, int z)
    {
        for (Block b : this.multiblock.keySet())
            for (int[] pos : this.multiblock.get(b))
                world.setBlock(x + pos[0], y + pos[1], z + pos[2], b);
    }

    @Override
    public boolean check(World world, int x, int y, int z)
    {
        boolean flag = true;
        for (Block b : this.multiblock.keySet())
            for (int[] pos : this.multiblock.get(b))
                flag = flag && (world.getBlock(x + pos[0], y + pos[1], z + pos[2]).equals(b));
        return flag;
    }
}

