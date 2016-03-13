package blep.modtech.tileentity;

import blep.modtech.network.ModteckPacketHandler;
import blep.modtech.network.message.MessageSpawnParticle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class TileEntityTreeFarm extends TileEntity implements ITickable//, IInventory, IItemHandler
{
    public int scanx = 0, scany = 1, scanz = 0, radius = 4;

    public PriorityQueue<BlockPos> leaves = new PriorityQueue<BlockPos>();
    public PriorityQueue<BlockPos> wood = new PriorityQueue<BlockPos>();

    private int[][] around = {
            {0, 0, 1},
            {0, 1, 0},
            {0, 1, 1},
            {1, 0, 0},
            {1, 0, 1},
            {1, 1, 0},
            {1, 1, 1}
    };
    private ItemStackHandler internalInventory = new ItemStackHandler(36);

    @Override
    public void update()
    {
        if (!worldObj.isRemote)
        {
            this.markDirty();
            if (treefarmShouldFunction())
                if (worldObj.getTotalWorldTime() % 2 == 0)
                {
                    cutTrees();
                    speedUpHopper();
                }
            if (worldObj.getTotalWorldTime() % 2 == 1)
                scanForTrees();
        }
    }

    private boolean treefarmShouldFunction()
    {
        boolean isRoomInInvFlag = false;
        boolean doesChestExistFlag = false;
        boolean doesHopperExistFlag = false;
        TileEntity genericTileEntity = worldObj.getTileEntity(pos.add(0, 1, 0));
        if (genericTileEntity instanceof TileEntityChest)
        {
            TileEntityChest chest = (TileEntityChest) genericTileEntity;
            doesChestExistFlag = true;
            for (int i = 0; i < chest.getSizeInventory(); i++)
            {
                if (!isRoomInInvFlag)
                {
                    ItemStack stack = chest.getStackInSlot(i);
                    isRoomInInvFlag = isRoomInInvFlag || stack == null;
                }
            }
        }
        genericTileEntity = worldObj.getTileEntity(pos.add(0, 2, 0));
        if (genericTileEntity instanceof TileEntityHopper)
            doesHopperExistFlag = true;
        return isRoomInInvFlag && doesChestExistFlag && doesHopperExistFlag;
    }

    private void speedUpHopper()
    {
        TileEntity tileEntity = worldObj.getTileEntity(pos.add(0, 2, 0));
        if (!(tileEntity instanceof TileEntityHopper)) return;
        TileEntityHopper hopper = (TileEntityHopper) tileEntity;
        for (int i = 0; i < 8; i++)
            hopper.update();
    }

    private void plantSapling(BlockPos p)
    {
        Block block = worldObj.getBlockState(p).getBlock();
        TileEntity tile = worldObj.getTileEntity(pos.add(0, 1, 0));
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

    private void cutTrees()
    {
        breakBlock(pos.add(0, 3, 0));
        if (!leaves.isEmpty())
            breakBlock(leaves.poll());
        else if (!wood.isEmpty())
            breakBlock(wood.poll());
    }

    private boolean breakBlock(BlockPos p)
    {
        if (p == null) return false;

        Block block = worldObj.getBlockState(p).getBlock();
        if (block instanceof BlockAir) return true;
        IBlockState blockState = worldObj.getBlockState(p);
        block.dropBlockAsItem(worldObj, new BlockPos(pos.add(0.5, 3.5, 0.5)), blockState, 0);
        worldObj.setBlockToAir(p);
        worldObj.playAuxSFX(2001, pos, Block.getIdFromBlock(block));
        if (worldObj.rand.nextBoolean())
            worldObj.playSoundEffect(p.getX() + 0.5F, p.getY() + 0.5F, p.getZ() + 0.5F, block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getFrequency() * 0.8F);
        for (int i = 0; i < 8; i++)
            ModteckPacketHandler.INSTANCE.sendToDimension(new MessageSpawnParticle(p.getX() + 0.5F, p.getY() + 0.5F, p.getZ() + 0.5F, 1 - 2 * worldObj.rand.nextDouble(), 0, 1 - 2 * worldObj.rand.nextDouble(), EnumParticleTypes.BLOCK_DUST, new int[]{Block.getStateId(blockState)}), worldObj.provider.getDimensionId());
        return true;
    }

    public void scanForTrees()
    {
        scanx++;
        if (scanx > radius)
        {
            scanx = -radius;
            scanz++;
            if (scanz > radius)
                scanz = -radius;
        }
        BlockPos bpos = pos.add(scanx, scany, scanz);

        Block scanned;

        if (worldObj.isAirBlock(bpos))
        {
            ModteckPacketHandler.INSTANCE.sendToDimension(new MessageSpawnParticle(bpos.getX() + 0.5D, bpos.getY() + 0.5D, bpos.getZ() + 0.5D, 0, 0, 0, EnumParticleTypes.SMOKE_LARGE, new int[0]), worldObj.provider.getDimensionId());
            plantSapling(bpos);
        } else if (worldObj.isAirBlock(bpos.add(0, 1, 0)))
        {
            ModteckPacketHandler.INSTANCE.sendToDimension(new MessageSpawnParticle(bpos.getX() + 0.5D, bpos.getY() + 0.5D, bpos.getZ() + 0.5D, 0, 0.1, 0, EnumParticleTypes.WATER_WAKE, new int[0]), worldObj.provider.getDimensionId());
        } else
            for (int[] plus : around)
                for (int i = -radius; i <= radius; i++)
                    for (int k = -radius; k <= radius; k++)
                    {
                        BlockPos bpos1 = bpos.add(i * plus[0], plus[1], k * plus[2]);
                        scanned = worldObj.getBlockState(bpos1).getBlock();
                        if (worldObj.isAirBlock(bpos1)) continue;
                        if (scanned.isReplaceable(worldObj, bpos1))
                            worldObj.setBlockToAir(bpos1);
                    }
        scanned = worldObj.getBlockState(bpos).getBlock();
        boolean isInFlag = false;
        out:
        if (!isInFlag)
        {
            for (BlockPos leafPos : leaves)
                if (leafPos.equals(bpos))
                {
                    isInFlag = true;
                    break out;
                }
            for (BlockPos woodPos : wood)
                if (woodPos.equals(bpos))
                {
                    isInFlag = true;
                    break out;
                }
        }

        if (!isInFlag && scanned.getMaterial().equals(Material.wood) && scanned.getUnlocalizedName().toLowerCase().contains("log"))
            if (checkIfTree(bpos))
                scanTree(new BlockPos(bpos));
    }

    public void scanTree(BlockPos pos)
    {
        Stack<BlockPos> toScan = new Stack<BlockPos>();
        ArrayList<BlockPos> scanned = new ArrayList<BlockPos>();
        toScan.add(pos);
        while (!toScan.isEmpty())
        {
            BlockPos center = toScan.pop();
            for (int i = -1; i <= 1; i++)
                for (int k = -1; k <= 1; k++)
                    for (int j = -1; j <= 1; j++)
                    {
                        BlockPos target = center.add(i, j, k);
                        if (!scanned.contains(target))
                        {
                            scanned.add(target);
                            if (leaves.contains(target) || wood.contains(target)) break;
                            if (isWood(worldObj, target))
                            {
                                toScan.add(target);
                                wood.add(target);
                            } else if (isLeaf(worldObj, target))
                            {
                                toScan.add(target);
                                leaves.add(target);
                            }
                        }
                    }
        }
    }

    private boolean checkIfTree(BlockPos start)
    {
        Stack<BlockPos> q = new Stack<BlockPos>();
        q.add(start);

        ArrayList<BlockPos> visited = new ArrayList<BlockPos>();

        while (!q.isEmpty())
        {
            BlockPos element = q.pop();
            for (int j = -1; j <= 1; j++)
                for (int k = -1; k <= 1; k++)
                    for (int i = -1; i <= 1; i++)
                    {
                        BlockPos target = element.add(i, j, k);
                        if (!visited.contains(target))
                        {
                            visited.add(target);
                            if (isWood(worldObj, target))
                                q.add(target);
                            if (isLeaf(worldObj, target))
                                return true;
                        }
                    }
        }
        return false;
    }

    private boolean isLeaf(World world, BlockPos pos)
    {
        return world.getBlockState(pos).getBlock().isLeaves(world, pos);
    }

    private boolean isWood(World world, BlockPos pos)
    {
        return world.getBlockState(pos).getBlock().isWood(world, pos);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("ScanX", scanx);
        nbt.setInteger("ScanY", scany);
        nbt.setInteger("ScanZ", scanz);
        nbt.setInteger("leavesSize", leaves.size());
        NBTTagCompound leavesnbt = new NBTTagCompound();
        for (int i = 0; i < leaves.size(); i++)
        {
            BlockPos pos = leaves.poll();
            leavesnbt.setInteger("x:" + i, pos.getX());
            leavesnbt.setInteger("y:" + i, pos.getY());
            leavesnbt.setInteger("z:" + i, pos.getZ());
        }
        nbt.setTag("Leaves", leavesnbt);
        nbt.setInteger("woodSize", wood.size());
        NBTTagCompound woodnbt = new NBTTagCompound();
        for (int i = 0; i < wood.size(); i++)
        {
            BlockPos pos = wood.poll();
            woodnbt.setInteger("x:" + i, pos.getX());
            woodnbt.setInteger("y:" + i, pos.getY());
            woodnbt.setInteger("z:" + i, pos.getZ());
        }
        nbt.setTag("Wood", woodnbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        scanx = nbt.getInteger("ScanX");
        scany = nbt.getInteger("ScanY");
        scanz = nbt.getInteger("ScanZ");
        int leavesSize = nbt.getInteger("leavesSize");
        NBTTagCompound leavesnbt = nbt.getCompoundTag("Leaves");
        for (int i = 0; i < leavesSize; i++)
            leaves.add(new BlockPos(leavesnbt.getInteger("x:" + i), leavesnbt.getInteger("y:" + i), leavesnbt.getInteger("y:" + i)));
        int woodSize = nbt.getInteger("woodSize");
        NBTTagCompound woodnbt = nbt.getCompoundTag("Wood");
        for (int i = 0; i < woodSize; i++)
            wood.add(new BlockPos(woodnbt.getInteger("x:" + i), woodnbt.getInteger("y:" + i), woodnbt.getInteger("y:" + i)));
    }
//    Inventory Stuff
//    @Override
//    public int getSlots()
//    {
//        return internalInventory.getSlots();
//    }
//
//    @Override
//    public int getSizeInventory()
//    {
//        return internalInventory.getSlots();
//    }
//
//    @Override
//    public ItemStack getStackInSlot(int slot)
//    {
//        return internalInventory.getStackInSlot(slot);
//    }
//
//    @Override
//    public ItemStack decrStackSize(int index, int count)
//    {
//        if (internalInventory.getStackInSlot(index) != null)
//        {
//            if (internalInventory.getStackInSlot(index).stackSize <= count)
//            {
//                ItemStack itemstack1 = internalInventory.getStackInSlot(index);
//                internalInventory.setStackInSlot(index, null);
//                this.markDirty();
//                return itemstack1;
//            }
//            else
//            {
//                ItemStack itemstack = internalInventory.getStackInSlot(index).splitStack(count);
//
//                if (internalInventory.getStackInSlot(index).stackSize == 0)
//                {
//                    internalInventory.setStackInSlot(index,null);
//                }
//
//                this.markDirty();
//                return itemstack;
//            }
//        }
//        else
//        {
//            return null;
//        }
//    }
//
//    @Override
//    public ItemStack removeStackFromSlot(int index)
//    {
//        if (internalInventory.getStackInSlot(index) != null)
//        {
//            ItemStack itemstack = internalInventory.getStackInSlot(index);
//            internalInventory.setStackInSlot(index, null);
//            return itemstack;
//        }
//        else
//        {
//            return null;
//        }
//    }
//
//    @Override
//    public void setInventorySlotContents(int index, ItemStack stack)
//    {
//        internalInventory.setStackInSlot(index, stack);
//
//        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
//        {
//            stack.stackSize = this.getInventoryStackLimit();
//        }
//
//        this.markDirty();
//    }
//
//    @Override
//    public int getInventoryStackLimit()
//    {
//        return 64;
//    }
//
//    @Override
//    public boolean isUseableByPlayer(EntityPlayer player)
//    {
//        return true;
//    }
//
//    @Override
//    public void openInventory(EntityPlayer player)
//    {
//
//    }
//
//    @Override
//    public void closeInventory(EntityPlayer player)
//    {
//
//    }
//
//    @Override
//    public boolean isItemValidForSlot(int index, ItemStack stack)
//    {
//        return true;
//    }
//
//    @Override
//    public int getField(int id)
//    {
//        return 0;
//    }
//
//    @Override
//    public void setField(int id, int value)
//    {
//
//    }
//
//    @Override
//    public int getFieldCount()
//    {
//        return 0;
//    }
//
//    @Override
//    public void clear()
//    {
//        internalInventory = new ItemStackHandler(36);
//    }
//
//    @Override
//    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
//    {
//        return internalInventory.insertItem(slot, stack, simulate);
//    }
//
//    @Override
//    public ItemStack extractItem(int slot, int amount, boolean simulate)
//    {
//        return internalInventory.extractItem(slot, amount, simulate);
//    }
//
//    @Override
//    public String getName()
//    {
//        return "Tree Farm";
//    }
//
//    @Override
//    public boolean hasCustomName()
//    {
//        return false;
//    }
//
//    @Override
//    public IChatComponent getDisplayName()
//    {
//        return new ChatComponentText("Tree Farm");
//    }
}