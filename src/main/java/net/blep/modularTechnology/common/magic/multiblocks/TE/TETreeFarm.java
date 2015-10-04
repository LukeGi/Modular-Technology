package net.blep.modularTechnology.common.magic.multiblocks.TE;

import com.google.common.collect.Lists;
import net.blep.modularTechnology.common.core.network.ModPacketHandler;
import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
import net.blep.modularTechnology.common.core.util.Int3;
import net.blep.modularTechnology.common.magic.ItemDesignator;
import net.blep.modularTechnology.common.magic.MagicBlockHandler;
import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODONE
 */
public class TETreeFarm extends TEMagicMutliblock implements ItemDesignator.IDesignatorReceiver
{
    private int x, y, z, nx = 5;
    private int xS = 0, zS = 0;
    private int radius = 10;
    private IInventory output;
    private List<Int3> cl = new ArrayList<Int3>();

    private int[][] aboveMultiBlocks = {{-1, 1, 0}, {-1, 1, 1}, {-1, 1, -1}, {0, 1, 0}, {0, 1, 1}, {0, 1, -1}, {1, 1, 0}, {1, 1, 1}, {1, 1, -1}};

    private Multiblock treefarm = new Multiblock(new Block[]
            {
                    MagicBlockHandler.treeFarm,
                    Blocks.lapis_block,
                    Blocks.iron_block
            }, new int[][][]
            {
                    {
                            {0, 0, 0}
                    },
                    {
                            {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                    },
                    {
                            {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                    }
            }
    );

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if (worldObj.getWorldTime() % 64 == 0) output = (IInventory) worldObj.getTileEntity(x, y, z);
        if (isFormed && output != null && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
        {
            if (worldObj.getWorldTime() % 4 == 0)
                cutTree();
            if (worldObj.getWorldTime() % 8 == 0)
                for (int i = 0; i < radius / 10; i++)
                    ScanTrees();
            if (worldObj.getWorldTime() % 16 == 0)
                scanWorld();
            if (worldObj.getWorldTime() % 32 == 0)
                for (int i = 0; i < radius / nx; i++)
                    plantSaplings();
        }
    }

    private void plantSaplings()
    {
        for (int i = -radius; i <= radius; i++)
            for (int j = -radius; j <= radius; j++)
            {
                Int3 pos = new Int3(i, 1, j);
                for (int[] offplant : aboveMultiBlocks)
                    if (offplant.equals(pos)) break;
                Int3 wpot = new Int3(xCoord, yCoord, zCoord);
                wpot.setX(wpot.getX() + pos.getX());
                wpot.setY(wpot.getY() + pos.getY());
                wpot.setZ(wpot.getZ() + pos.getZ());
                plantSapling(wpot);
            }
    }

    private void scanWorld()
    {
        for (ItemStack stack : getSaplingItemEntities())
            putBlockInOutputInventory(stack, 0);
    }

    public List<ItemStack> getSaplingItemEntities()
    {
        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord - radius - 10, yCoord - radius - 10, zCoord - radius - 10, xCoord + radius + 10, yCoord + radius + 10, zCoord + radius + 10);

        List result = worldObj.getEntitiesWithinAABB(EntityItem.class, aabb);
        List<ItemStack> stacks = Lists.newArrayList();
        for (Object o : result.toArray())
        {
            if (!(o instanceof EntityItem)) continue;

            EntityItem ei = (EntityItem) o;
            if (ei.delayBeforeCanPickup <= 2)
            {
                Item item = ei.getEntityItem().getItem();
                int meta = ei.getEntityItem().getItemDamage();
                int amount = ei.getEntityItem().stackSize;
                if (item == null) continue;
                stacks.add(new ItemStack(item, amount, meta));
                worldObj.removeEntity(ei);
            }
        }

        return stacks;
    }

    private void plantSapling(Int3 p)
    {
        Block block = worldObj.getBlock(p.getX(), p.getY(), p.getZ());

        if (block.isReplaceable(worldObj, p.getX(), p.getY(), p.getZ()) && (output != null))
            for (int i = 0; i < output.getSizeInventory(); i++)
            {
                ItemStack stack = output.getStackInSlot(i);
                if (stack != null && stack.getItem() instanceof ItemBlock)
                {
                    block = ((ItemBlock) stack.getItem()).field_150939_a;
                    if (block.getUnlocalizedName().toLowerCase().contains("sapling") && block.canBlockStay(worldObj, p.getX(), p.getY(), p.getZ()))
                    {
                        worldObj.setBlock(p.getX(), p.getY(), p.getZ(), block, stack.getItemDamage(), 3);
                        output.decrStackSize(i, 1);
                    }
                }
            }
    }

    private void ScanTrees()
    {
        xS++;
        if (xS > radius + 1)
        {
            xS = -radius;
            zS++;
            if (zS > radius + 1)
            {
                zS = -radius;
            }
        }
//        LogHelper.info(String.format("Scanning at: x: %s, y: %s, z: %s.", xCoord + xS, yCoord + 1, zCoord + zS));
        Block block = worldObj.getBlock(xCoord + xS, yCoord + 1, zCoord + zS);
        if (canCut(block))
            cl = pathFind(cl, xCoord + xS, yCoord + 1, zCoord + zS);
    }

    private List<Int3> pathFind(List<Int3> visitied, int x, int y, int z)
    {
        Queue<Int3> q = new PriorityQueue<Int3>();
        Int3 start = new Int3(x, y, z);
        q.add(start);

        while (!q.isEmpty())
        {
            Int3 element = q.poll();
            for (int j = -1; j <= 1; j++)
                for (int k = -1; k <= 1; k++)
                    for (int i = -1; i <= 1; i++)
                    {
                        Int3 target = new Int3(element.getX() + i, element.getY() + j, element.getZ() + k);
                        Block block = worldObj.getBlock(target.getX(), target.getY(), target.getZ());
                        if (canCut(block))
                            if (!visitied.contains(target))
                            {
                                visitied.add(target);
                                q.add(target);
                            }
                    }
        }

        return visitied;
    }

    public void cutTree()
    {
        do
        {
            ++nx;
            if (output == null) break;
            if (cl.isEmpty()) break;
            int fs = worldObj.rand.nextInt(cl.size()) / 2;
            Int3 pos = cl.get(fs);
            cl.remove(fs);
            Block currentBlock = worldObj.getBlock(pos.getX(), pos.getY(), pos.getZ());
            List<ItemStack> drops = currentBlock.getDrops(worldObj, pos.getX(), pos.getY(), pos.getZ(), worldObj.getBlockMetadata(pos.getX(), pos.getY(), pos.getZ()), 1);
            ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, pos.getX(), pos.getY(), pos.getZ()));
            worldObj.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, currentBlock.stepSound.getBreakSound(), (currentBlock.stepSound.getVolume() + 1.0F) / 2.0F, currentBlock.stepSound.getPitch() * 0.8F);

            for (ItemStack stack : drops) putBlockInOutputInventory(stack, 0);
        } while (cl.size() > Math.pow(2, nx));
        nx = 5;
    }

    private void putBlockInOutputInventory(ItemStack stack, int attempts)
    {
        if (stack == null) return;
        ItemStack leftovers = TileEntityHopper.func_145889_a(output, stack, 0);
        if (leftovers == null) return;
        if (attempts <= output.getSizeInventory())
        {
            attempts++;
            putBlockInOutputInventory(leftovers, attempts);
        } else
            return;//worldObj.spawnEntityInWorld(new EntityItem(worldObj, x + 0.5, y + 0.5, z + 0.5, stack));
    }

    public boolean canCut(Block block)
    {
        boolean flag = true;
        if (block == null) return false;
        if (!(block.getMaterial() == Material.wood || block.getMaterial() == Material.leaves)) flag = false;
        if (!(block instanceof BlockRotatedPillar || block instanceof BlockLeavesBase)) flag = false;
        return flag;
    }

    @Override
    protected Multiblock getMultiblock()
    {
        return treefarm;
    }

    @Override
    public void recieveData(int x, int y, int z)
    {
        TileEntity te = worldObj.getTileEntity(x, y, z);
        this.x = x;
        this.y = y;
        this.z = z;

        if (te instanceof IInventory)
        {
            this.output = (IInventory) te;
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt)
    {
        super.writeCustomNBT(nbt);
        NBTTagList TagList = new NBTTagList();
        for (Int3 v : cl)
        {
            NBTTagCompound tag1 = new NBTTagCompound();
            tag1.setInteger("x", v.getX());
            tag1.setInteger("y", v.getY());
            tag1.setInteger("z", v.getZ());

            TagList.appendTag(tag1);
        }
        nbt.setTag("cl", TagList);

        nbt.setInteger("ox", x);
        nbt.setInteger("oy", y);
        nbt.setInteger("oz", z);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        super.readCustomNBT(nbt);
        NBTTagList tagList = nbt.getTagList("cl", 10);
        for (int i = 0; i < tagList.tagCount(); i++)
        {
            NBTTagCompound tag1 = tagList.getCompoundTagAt(i);
            cl.add(new Int3(tag1.getInteger("x"), tag1.getInteger("y"), tag1.getInteger("z")));
        }
        x = nbt.getInteger("ox");
        y = nbt.getInteger("oy");
        z = nbt.getInteger("oz");
    }
}
