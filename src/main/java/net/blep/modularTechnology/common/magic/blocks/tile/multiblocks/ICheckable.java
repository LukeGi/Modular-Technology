package net.blep.modularTechnology.common.magic.blocks.tile.multiblocks;

import net.minecraft.world.World;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public interface ICheckable
{
    boolean check(World world, int x, int y, int z);
}
