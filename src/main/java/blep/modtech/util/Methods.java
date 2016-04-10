package blep.modtech.util;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class Methods
{
    public static void spawnItemStillInWorld(World world, BlockPos pos, ItemStack toDrop)
    {
        if (world == null || world.isRemote || pos == null || toDrop == null) return;
        EntityItem itemdrop = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, toDrop);
        itemdrop.setVelocity(0, 0, 0);
        world.spawnEntityInWorld(itemdrop);
    }

    /**
     * This will break the block at the given position in the given world.
     *
     * @param world
     * @param pos
     * @return A list of ItemStacks that breaking the block would drop
     */
    public static List<ItemStack> breakBlock(World world, BlockPos pos)
    {
        List<ItemStack> returner = world.getBlockState(pos).getBlock().getDrops(world, pos, world.getBlockState(pos), 0);
        if (world.isRemote)
            world.playAuxSFX(2001, pos, Block.getIdFromBlock(world.getBlockState(pos).getBlock()));
        else
            world.setBlockState(pos, Blocks.air.getDefaultState(), 3);
        return returner;
    }

    public static Vec3d calcDir(Vec3d add, Vec3d add1)
    {
        return add.subtract(add1);
    }
}
