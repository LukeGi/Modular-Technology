package blep.modtech.machine.farm.cobblestone;

import blep.modtech.core.TileEntityBaseGui;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class TileEntityCobbleFarm extends TileEntityBaseGui implements ITickable, IEnergyReceiver
{
    private ItemStackHandler inventory;
    private EnergyStorage energy;
    private IItemHandler itemHandler = new InvWrapper(this);

    public TileEntityCobbleFarm()
    {
        inventory = new ItemStackHandler(64);
        energy = new EnergyStorage(1000000);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        energy.writeToNBT(compound);
        compound.setTag("Inventory", inventory.serializeNBT());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        energy.readFromNBT(compound);
        inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
    }

    @Override
    public void update()
    {
        if (energy.getEnergyStored() >= 10)
        {
            energy.modifyEnergyStored(-10);
            ItemHandlerHelper.insertItem(this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN), new ItemStack(Blocks.cobblestone, 1), false);
        }
    }

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate)
    {
        return energy.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int getEnergyStored(EnumFacing from)
    {
        return energy.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from)
    {
        return energy.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(EnumFacing from)
    {
        return true;
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.getSlots();
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return inventory.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        return inventory.extractItem(index, count, false);
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        return inventory.extractItem(index, inventory.getStackInSlot(index).stackSize, false);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        inventory.setStackInSlot(index, stack);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player)
    {

    }

    @Override
    public void closeInventory(EntityPlayer player)
    {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return true;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {

    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear()
    {
        inventory = new ItemStackHandler(inventory.getSlots());
    }

    @Override
    public String getName()
    {
        return "Cobblestone Generator";
    }

    @Override
    public boolean hasCustomName()
    {
        return false;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentString(getName());
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) itemHandler : super.getCapability(capability, facing);
    }
}
