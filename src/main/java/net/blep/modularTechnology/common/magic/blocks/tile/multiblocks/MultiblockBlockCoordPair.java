package net.blep.modularTechnology.common.magic.blocks.tile.multiblocks;

import com.google.common.collect.Lists;
import com.blep.modularTechnology.core.common.util.Int3;
import net.minecraft.block.Block;

import java.util.Collections;
import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class MultiblockBlockCoordPair
{
    private Block block;
    private List<Int3> poses;

    public MultiblockBlockCoordPair(Block block, Int3[] inposes)
    {
        this.block = block;
        poses = Lists.newArrayList();
        Collections.addAll(poses, inposes);
    }

    public Block getBlock()
    {
        return block;
    }

    public List<Int3> getPoses()
    {
        return poses;
    }
}
