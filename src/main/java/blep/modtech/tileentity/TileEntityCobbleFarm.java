package blep.modtech.tileentity;

import blep.modtech.multiblock.MuPCobbleGen;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class TileEntityCobbleFarm extends TileEntity implements ITickable
{
    public TileEntityCobbleFarm master = null;
    boolean firstTime = true;
    boolean shouldWork = false;
    boolean isMaster = false;

    public boolean isMaster()
    {
        return isMaster;
    }

    public TileEntityCobbleFarm getMaster()
    {
        return master;
    }

    private void setMaster(TileEntityCobbleFarm master)
    {
        this.master = master;
        isMaster = master == this;
        if (isMaster)
        {
            Stack<TileEntityCobbleFarm> q = new Stack<TileEntityCobbleFarm>();
            List<TileEntityCobbleFarm> visited = new ArrayList<TileEntityCobbleFarm>();
            q.add(this);
            while (!q.isEmpty())
            {
                TileEntityCobbleFarm current = q.pop();
                visited.add(current);
                for (EnumFacing d : EnumFacing.VALUES)
                {
                    TileEntity tile = worldObj.getTileEntity(new BlockPos(pos).add(d.getDirectionVec()));
                    if (tile instanceof TileEntityCobbleFarm && !visited.contains(tile))
                    {
                        q.add((TileEntityCobbleFarm) tile);
                    }
                }
            }
//            for (TileEntityCobbleFarm f : visited)
//                f.setMaster(master);
        }
    }

    @Override
    public void update()
    {
        // Do firstTime checks
        if (firstTime)
        {
            checkIfShouldWork();
        }

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
        TileEntity tile = worldObj.getTileEntity(new BlockPos(MuPCobbleGen.cobblegen.getChest()).add(pos));
        if (tile == null) return;
        ItemStack stack = new ItemStack(Blocks.cobblestone, 1);
        if (tile instanceof TileEntityChest)
        {
            TileEntityHopper.putStackInInventoryAllSlots((IInventory) tile, stack, EnumFacing.UP);
        } else if (tile instanceof IItemHandler)
        {
            IItemHandler farm = (IItemHandler) tile;
            for (int i = 0; i < farm.getSlots(); i++)
                stack = ((IItemHandler) tile).insertItem(i, stack, false);
        }
        if (stack != null && stack.stackSize != 0)
        {
            this.shouldWork = false;
        }
    }

    private void checkIfShouldWork()
    {
        shouldWork = MuPCobbleGen.cobblegen.isFormed(worldObj, pos);
        if (shouldWork)
            setMaster(this);
    }
}
