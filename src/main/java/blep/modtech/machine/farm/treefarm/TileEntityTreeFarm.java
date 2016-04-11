package blep.modtech.machine.farm.treefarm;

import blep.modtech.core.TileEntityBaseGui;
import blep.modtech.util.LogHelper;
import blep.modtech.util.Methods;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import com.sun.jmx.remote.internal.ArrayQueue;
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

import java.util.List;
import java.util.Stack;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class TileEntityTreeFarm extends TileEntityBaseGui implements ITickable, IEnergyReceiver
{
    final int radius;
    boolean shouldWork = true;
    int scanx, scanz;
    EnergyStorage energy = new EnergyStorage(100000);
    ItemStackHandler inventory = new ItemStackHandler(3 * 9);
    private IItemHandler itemHandler = new InvWrapper(this);
    private List<BlockPos> queue;

    public TileEntityTreeFarm(int radius)
    {
        this.queue = new ArrayQueue<>(1000);
        this.radius = radius;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        energy.readFromNBT(compound);
        inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        energy.writeToNBT(compound);
        compound.setTag("Inventory", inventory.serializeNBT());
    }

    @Override
    public void update()
    {
        if (worldObj.getTotalWorldTime() % 2 == 0)
            scan();
        if (worldObj.getTotalWorldTime() % 8 == 3)
            cut(30);
        markDirty();
    }

    private void cut(int i)
    {
        if (shouldWork)
        {
            while (!queue.isEmpty() && i > 0)
            {
                i--;
                if (queue.isEmpty()) return;
                BlockPos currentPos = queue.get(0);
                queue.remove(0);
                float hardness = worldObj.getBlockState(currentPos).getBlock().getBlockHardness(null, null, null);
                if (energy.getEnergyStored() < 100 * (0 + hardness))
                {
                    queue.add(currentPos);
                    break;
                }
                energy.extractEnergy((int) (100 * (1 + hardness)), false);
                scanAround(currentPos);
                Methods.breakBlock(worldObj, currentPos).forEach(p -> Methods.spawnItemStillInWorld(worldObj, new BlockPos(pos).add(0, 2, 0), ItemHandlerHelper.insertItem(this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN), p, false)));
                worldObj.spawnParticle(EnumParticleTypes.DRAGON_BREATH, currentPos.getX() + worldObj.rand.nextFloat(), currentPos.getY() + worldObj.rand.nextFloat(), currentPos.getZ() + worldObj.rand.nextFloat(), worldObj.rand.nextGaussian() / 10, worldObj.rand.nextGaussian() / 10, worldObj.rand.nextGaussian() / 10, new int[0]);
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
            Vec3d dir;
            dir = Methods.calcDir(new Vec3d(pos).add(new Vec3d(0.5, 2.5, 0.5)), new Vec3d(pos).add(new Vec3d(scanx, 1.5, scanz))).scale(0.09F);
            worldObj.spawnParticle(EnumParticleTypes.END_ROD, pos.getX() + scanx, pos.getY() + 1.5, pos.getZ() + scanz, dir.xCoord, dir.yCoord, dir.zCoord, new int[0]);
            if (!queue.contains(offsetPosition) && isValidBlock(offsetPosition))
                queue.add(offsetPosition);
            if (worldObj.getBlockState(offsetPosition).getBlock().isReplaceable(worldObj, offsetPosition))
                worldObj.setBlockToAir(offsetPosition);
            if (worldObj.isAirBlock(offsetPosition))
                plantSapling(offsetPosition);
        }
        shouldWork = shouldWork();
    }

    private boolean shouldWork()
    {
        boolean isRoom = false;
        for (int i = 0; i < inventory.getSlots() && !isRoom; i++)
            isRoom |= inventory.getStackInSlot(i) == null;
        return isRoom;
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
                        break;
                    }
                }
            }
    }

    private void scanAround(BlockPos p)
    {
        for (EnumFacing d : EnumFacing.VALUES)
        {
            BlockPos np = new BlockPos(p).add(d.getDirectionVec());
            if (!queue.contains(np) && isValidBlock(np)) queue.add(np);
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