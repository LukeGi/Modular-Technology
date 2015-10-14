package net.blep.modularTechnology.common.magic.blocks.tile.multiblocks;

import net.minecraft.world.World;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public interface ICreateable
{
    void create(World world, int x, int y, int z);
}
