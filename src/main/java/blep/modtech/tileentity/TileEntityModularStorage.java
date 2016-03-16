package blep.modtech.tileentity;

import blep.modtech.util.LogHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class TileEntityModularStorage extends TileEntity implements ITickable, IItemHandler
{
    private static final int SLOTS_PER_BLOCK = 1;
    @CapabilityInject(IItemHandler.class)
    private ItemStackHandler inventory = new ItemStackHandler(SLOTS_PER_BLOCK);

    private TileEntityModularStorage master = null;
    private boolean isMaster;
    private boolean firstRun = true;

    public boolean isMaster()
    {
        return isMaster;
    }

    public TileEntityModularStorage getMaster()
    {
        return master;
    }

    private void setMaster(TileEntityModularStorage master, int storages)
    {
        this.master = master;
        boolean wasMaster = isMaster;
        isMaster = master == this;
        if (isMaster)
        {
            LogHelper.info("Master set to " + storages + " blocks");
            ItemStackHandler newInventory = new ItemStackHandler(SLOTS_PER_BLOCK * storages);
            for (int i = 0; i < inventory.getSlots(); i++)
                if (i < newInventory.getSlots())
                    newInventory.setStackInSlot(i, inventory.getStackInSlot(i));
                else if (inventory.getStackInSlot(i) != null)
                    worldObj.spawnEntityInWorld(new EntityItem(worldObj, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(i)));
            inventory = newInventory;
        } else if (!isMaster && wasMaster)
            for (int i = 0; i < inventory.getSlots(); i++)
                if (inventory.getStackInSlot(i) != null)
                    worldObj.spawnEntityInWorld(new EntityItem(worldObj, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(i)));
    }

    @Override
    public void update()
    {
        if (firstRun)
        {
            initializeMultiblockIfNecessary();
            firstRun = false;
        }
    }

    @Override
    public void invalidate()
    {
        super.invalidate();
        TileEntity tile;
        for (EnumFacing d : EnumFacing.VALUES)
        {
            tile = worldObj.getTileEntity(new BlockPos(pos).add(d.getDirectionVec()));
            if (tile instanceof TileEntityModularStorage)
            {
                ((TileEntityModularStorage) tile).master = null;
                ((TileEntityModularStorage) tile).initializeMultiblockIfNecessary();
            }
        }
        for (int i = 0; i < inventory.getSlots(); i++)
            if (inventory.getStackInSlot(i) != null)
                worldObj.spawnEntityInWorld(new EntityItem(worldObj, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(i)));
    }

    private void initializeMultiblockIfNecessary()
    {
        if (master == null || master.isInvalid())
        {
            List<TileEntityModularStorage> connectedStorages = new ArrayList<TileEntityModularStorage>();
            Stack<TileEntityModularStorage> traversingStorages = new Stack<TileEntityModularStorage>();
            TileEntityModularStorage master = this;
            traversingStorages.add(this);
            connectedStorages.add(this);
            while (!traversingStorages.isEmpty())
            {
                TileEntityModularStorage storage = traversingStorages.pop();
                if (storage.isMaster())
                {
                    master = storage;
                }
                for (EnumFacing b : EnumFacing.values())
                {
                    TileEntity te = worldObj.getTileEntity(new BlockPos(storage.pos).add(b.getDirectionVec()));
                    if (te instanceof TileEntityModularStorage && !connectedStorages.contains(te))
                    {
                        connectedStorages.add((TileEntityModularStorage) te);
                        traversingStorages.add((TileEntityModularStorage) te);
                    }
                }
            }
            LogHelper.info("Setting master to " + master.pos.toString().substring(8) + " for " + connectedStorages.size() + " blocks");
            for (TileEntityModularStorage storage : connectedStorages)
            {
                storage.setMaster(master, connectedStorages.size());
            }
        }

    }


    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) ? (T) this : super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) || super.hasCapability(capability, facing);
    }

    @Override
    public int getSlots()
    {
        return isMaster() ? inventory.getSlots() : master != null ? getMaster().getSlots() : 0;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return isMaster() ? inventory.getStackInSlot(slot) : getMaster().getStackInSlot(slot);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
    {
        return isMaster() ? inventory.insertItem(slot, stack, simulate) : getMaster().insertItem(slot, stack, simulate);
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {
        return isMaster() ? inventory.extractItem(slot, amount, simulate) : getMaster().extractItem(slot, amount, simulate);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setBoolean("isMaster", isMaster);
        compound.setTag("inventory", inventory.serializeNBT());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        isMaster = compound.getBoolean("isMaster");
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
    }
}
