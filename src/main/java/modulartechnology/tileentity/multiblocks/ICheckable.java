package modulartechnology.tileentity.multiblocks;

import net.minecraft.world.World;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public interface ICheckable
{
    boolean check(World world, int x, int y, int z);
}
