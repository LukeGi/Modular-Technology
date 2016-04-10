package blep.modtech.machine.farm.treefarm;

import blep.modtech.util.LogHelper;
import blep.modtech.util.Methods;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.Stack;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class TileEntityTreeFarm extends TileEntity implements ITickable, IInventory, IEnergyReceiver
{
    final int radius;
    boolean shouldWork = true;
    int scanx, scanz;
    EnergyStorage energy = new EnergyStorage(100000);
    ItemStackHandler inventory = new ItemStackHandler(3 * 9);
    private IItemHandler itemHandler = new InvWrapper(this);
    private Stack<BlockPos> queue;

    public TileEntityTreeFarm(int radius)
    {
        this.queue = new Stack<>();
        this.radius = radius;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        if (worldObj.isRemote) return;
        energy.readFromNBT(compound);
        inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
        super.readFromNBT(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        if (worldObj.isRemote) return;
        energy.writeToNBT(compound);
        compound.setTag("Inventory", inventory.serializeNBT());
        LogHelper.info("Saved to nbt");
        super.writeToNBT(compound);
    }

    @Override
    public void update()
    {
        if (worldObj.getTotalWorldTime() % 2 == 0)
            scan();
        if (worldObj.getTotalWorldTime() % 2 == 1)
            cut(1);
        this.energy.receiveEnergy(50, false);
        markDirty();
    }

    private void cut(int i)
    {
        if (shouldWork)
        {
            for (int j = 0; j < i; j++)
            {
                if (queue.isEmpty()) return;
                BlockPos currentPos = queue.pop();
                float hardness = worldObj.getBlockState(currentPos).getBlock().getBlockHardness(null, null, null);
                if (energy.getEnergyStored() < 100 * (0 + hardness))
                {
                    queue.push(currentPos);
                    return;
                }
                energy.extractEnergy((int) (100 * (1 + hardness)), false);
                scanAround(currentPos);
                Methods.breakBlock(worldObj, currentPos).forEach(p -> Methods.spawnItemStillInWorld(worldObj, new BlockPos(pos).add(0, 2, 0), ItemHandlerHelper.insertItem(this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN), p, false)));
            }
        }
    }

    private void scan()
    {
        scanx++;
        if (scanx > radius)
        {
            scanx = -radius;
            scanz++;
            if (scanz > radius)
                scanz = -radius;
        }
        BlockPos offsetPosition = pos.add(scanx, 1, scanz);
        if (shouldWork)
        {
            Vec3d dir = Methods.calcDir(new Vec3d(pos).add(new Vec3d(0.5, 2.5, 0.5)), new Vec3d(pos).add(new Vec3d(scanx, 1.5, scanz))).scale(0.09F);
            worldObj.spawnParticle(EnumParticleTypes.END_ROD, pos.getX() + scanx, pos.getY() + 1.5, pos.getZ() + scanz, dir.xCoord, dir.yCoord, dir.zCoord, new int[0]);
            if (!queue.contains(offsetPosition) && isValidBlock(offsetPosition))
            {
                queue.push(offsetPosition);
            } else if (worldObj.isAirBlock(offsetPosition)) plantSapling(offsetPosition);
        }

    }

    private void plantSapling(BlockPos p)
    {
        Block block = worldObj.getBlockState(p).getBlock();
        TileEntity tile = worldObj.getTileEntity(pos);
        if (!(tile instanceof IInventory)) return;
        IInventory output = (IInventory) tile;
        if (block.isReplaceable(worldObj, p))
            for (int i = 0; i < output.getSizeInventory(); i++)
            {
                ItemStack stack = output.getStackInSlot(i);
                if (stack != null && stack.getItem() instanceof ItemBlock)
                {
                    block = ((ItemBlock) stack.getItem()).getBlock();
                    if (block.getUnlocalizedName().toLowerCase().contains("sapling") && ((BlockSapling) block).canBlockStay(worldObj, p, block.getStateFromMeta(stack.getItemDamage())))
                    {
                        worldObj.setBlockState(p, block.getStateFromMeta(stack.getItemDamage()), 3);
                        output.decrStackSize(i, 1);
                    }
                }
            }
    }

    private void scanAround(BlockPos p)
    {
        for (EnumFacing d : EnumFacing.VALUES)
        {
            BlockPos np = new BlockPos(p).add(d.getDirectionVec());
            if (!queue.contains(np) && isValidBlock(np)) queue.push(np);
        }
    }

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate)
    {
        this.notifyAll();
        this.markDirty();
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
        return 9 * 3;
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

    }

    @Override
    public String getName()
    {
        return null;
    }

    @Override
    public boolean hasCustomName()
    {
        return false;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentString("Tree Farm");
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

    boolean isValidBlock(BlockPos pos)
    {
        return isWood(pos) || isLeaves(pos);
    }

    boolean isWood(BlockPos pos)
    {
        return worldObj.getBlockState(pos).getBlock().isWood(worldObj, pos);
    }

    boolean isLeaves(BlockPos pos)
    {
        return worldObj.getBlockState(pos).getBlock().isLeaves(worldObj.getBlockState(pos), worldObj, pos);
    }
}