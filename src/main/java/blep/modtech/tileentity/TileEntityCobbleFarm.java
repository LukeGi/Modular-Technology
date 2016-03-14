package blep.modtech.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

import java.util.Arrays;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class TileEntityCobbleFarm extends TileEntity implements ITickable
{
    Block[] blocksAround;

    boolean firstTime = true;
    boolean shouldWork = false;

    @Override
    public void update()
    {
        // Do firstTime checks
        if (firstTime)
        {
            blocksAround = new Block[6];
            observeSurroundingBlocks();
            checkIfShouldWork();
        }
        // every few ticks check surrounding blocks
        if (worldObj.getTotalWorldTime() % 20 == 0)
            observeSurroundingBlocks();
        // tick after check if still should work
        if (worldObj.getTotalWorldTime() % 20 == 1)
            checkIfShouldWork();
        // If shouldWork, work
        if (shouldWork && worldObj.getTotalWorldTime() % 10 == 2)
        {
            work();
        }
    }

    private void work()
    {
        if (worldObj.isRemote) return;
        EntityItem toSpawn = new EntityItem(worldObj, pos.getX() + 0.5d, pos.getY() + 1, pos.getZ() + 0.5d, new ItemStack(Blocks.cobblestone, 1));
        toSpawn.setVelocity(0, 0, 0);
        worldObj.spawnEntityInWorld(toSpawn);
    }

    private void checkIfShouldWork()
    {
        shouldWork = Arrays.asList(blocksAround).contains(Blocks.water) && Arrays.asList(blocksAround).contains(Blocks.lava);
    }

    private void observeSurroundingBlocks()
    {
        for (EnumFacing d : EnumFacing.VALUES)
            blocksAround[d.ordinal()] = worldObj.getBlockState(new BlockPos(pos).add(d.getDirectionVec())).getBlock();
    }
}
