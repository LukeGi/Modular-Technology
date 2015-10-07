package net.blep.modularTechnology.common.magic.multiblocks;

import com.google.common.collect.Lists;
import net.blep.modularTechnology.common.core.util.Int3;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class Multiblock implements ICreateable, ICheckable
{
    private List<MultiblockBlockCoordPair> multiblock = Lists.newArrayList();

    public Multiblock(MultiblockBlockCoordPair[] blockCoordPairs)
    {
        Collections.addAll(this.multiblock, blockCoordPairs);
    }

    /**
     * will spawn multiblock in world relative to give coords.
     *
     * @param world world you want to spawn it in.
     * @param x     x coord of spawning the center block of the multiblock
     *              all other blocks will use this and be spawned relatively.
     * @param y     y coord of spawning the center block of the multiblock
     *              all other blocks will use this and be spawned relatively.
     * @param z     z coord of spawning the center block of the multiblock
     *              all other blocks will use this and be spawned relatively.
     */
    @Override
    public void create(World world, int x, int y, int z)
    {
        for (MultiblockBlockCoordPair pair : this.multiblock)
            for (Int3 pos : pair.getPoses())
                world.setBlock(x + pos.getX(), y + pos.getY(), z + pos.getZ(), pair.getBlock());
    }

    /**
     * @param world world the multiblock is in
     * @param x     x position of the middle of the multiblock
     * @param y     y position of the middle of the multiblock
     * @param z     z position of the middle of the multiblock
     * @return returns true if all blocks are present or false if even just one block is missing.
     */
    @Override
    public boolean check(World world, int x, int y, int z)
    {
        boolean flag = true;
        for (MultiblockBlockCoordPair pair : this.multiblock)
            for (Int3 pos : pair.getPoses())
                flag = flag && (world.getBlock(x + pos.getX(), y + pos.getY(), z + pos.getZ()).equals(pair.getBlock()));
        return flag;
    }

    /**
     * WARNING, recursive method
     *
     * @param rotFac 0 if no rotation, 1 if 90 rotation, 2 if 180 rotation, 3 if 270 rotation... all clockwise
     * @return rotated Multiblock
     */
    public static Multiblock rotateMultiblock(int rotFac, Multiblock multiblock)
    {
        if (rotFac <= 0) return multiblock;
        MultiblockBlockCoordPair[] newPairs = new MultiblockBlockCoordPair[multiblock.multiblock.size()];
        List<MultiblockBlockCoordPair> multiblock1 = multiblock.multiblock;
        for (int i = 0; i < multiblock1.size(); i++)
        {
            MultiblockBlockCoordPair pair = multiblock1.get(i);
            List<Int3> poses = pair.getPoses();
            List<Int3> newposes = Lists.newArrayList();
            for (Int3 pos : poses)
                newposes.add(Int3.ni3(pos.getX() > 0 ? -pos.getX() : pos.getX() < 0 ? Math.abs(pos.getX()) : 0, pos.getY(), pos.getZ() > 0 ? -pos.getZ() : pos.getZ() < 0 ? Math.abs(pos.getZ()) : 0));
            Int3[] returnposes = (Int3[]) newposes.toArray();
            newPairs[i] = new MultiblockBlockCoordPair(pair.getBlock(), returnposes);
        }
        return rotateMultiblock(rotFac - 1, new Multiblock(newPairs));
    }
}

