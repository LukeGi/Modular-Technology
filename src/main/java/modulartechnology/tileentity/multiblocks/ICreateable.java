package modulartechnology.tileentity.multiblocks;

import net.minecraft.world.World;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public interface ICreateable
{
    void create(World world, int x, int y, int z);
}
