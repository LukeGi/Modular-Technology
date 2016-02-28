package blep.modtech.tileentity;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class TileEntityTreeFarm extends TileEntity implements ITickable
{
    public int scanx = 0, scany = 1, scanz = 0, radius = 4;

    public ArrayList<Tree> trees = Lists.newArrayList();

    @Override
    public void update()
    {
        if (!worldObj.isRemote)
        {
            if (worldObj.getTotalWorldTime() % 8 == 0)
                cutTrees();
            if (worldObj.getTotalWorldTime() % 8 == 1)
                cleanForest();
            if (worldObj.getTotalWorldTime() % 8 == 2)
                speedUpHopper();
            if (worldObj.getTotalWorldTime() % 8 == 3)
                plantSaplings();
            if (worldObj.getTotalWorldTime() % 8 == 4)
                cutTrees();
            if (worldObj.getTotalWorldTime() % 8 == 5)
                breakBlock(pos.add(0, 3, 0));
            if (worldObj.getTotalWorldTime() % 8 == 6)
                for (int i = 0; i < radius * radius; i++) scanForTrees();
        }
    }

    private void speedUpHopper()
    {
        TileEntity tileEntity = worldObj.getTileEntity(pos.add(0, 2, 0));
        if (!(tileEntity instanceof TileEntityHopper)) return;
        TileEntityHopper hopper = (TileEntityHopper) tileEntity;
        for (int i = 0; i < 8; i++)
            hopper.update();
    }

    private void plantSaplings()
    {
        for (int i = -radius; i <= radius; i++)
            for (int j = -radius; j <= radius; j++)
            {
                plantSapling(pos.add(i, 1, j));
            }
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

    private void cleanForest()
    {
        for (int i = 0; i < trees.size() / 5; i++)
        {
            if (trees.get(i).leaves.isEmpty() && trees.get(i).wood.isEmpty()) trees.remove(i);
        }
    }

    private void cutTrees()
    {
        if (trees.isEmpty()) return;
        while (!trees.get(0).leaves.isEmpty()) breakBlock(trees.get(0).leaves.poll());
        breakBlock(trees.get(0).wood.poll());
    }

    private void breakBlock(BlockPos p)
    {
        try
        {
            Block block = worldObj.getBlockState(p).getBlock();
            if (block instanceof BlockAir) return;
            IBlockState blockstate = worldObj.getBlockState(p);
            ArrayList<ItemStack> drops = (ArrayList<ItemStack>) block.getDrops(worldObj, p, blockstate, 0);
            worldObj.setBlockToAir(p);
            worldObj.playSoundEffect(p.getX() + 0.5F, p.getY() + 0.5F, p.getZ() + 0.5F, block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getFrequency() * 0.8F);
//            TODO : NetworkManagerModtech.networkManager.sendToDimension(new MessageSpawnParticle(MessageSpawnParticle.ParticleTypes.BLOCKBREAK, p.x + 0.5f, p.y + 0.5f, p.z + 0.5f, 16, Block.getIdFromBlock(block)), worldObj.provider.dimensionId);
            for (ItemStack stack : drops)
            {
                EntityItem drop = new EntityItem(worldObj, pos.getX() + 0.5f, pos.getY() + 3.5f, pos.getZ() + 0.5f, stack);
                drop.setVelocity(0, 0, 0);
                worldObj.spawnEntityInWorld(drop);
            }
        } catch (Throwable ignore)
        {
        }
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

        Block scanned = worldObj.getBlockState(bpos).getBlock();
        if (scanned.isReplaceable(worldObj, bpos))
            worldObj.setBlockToAir(bpos);
        // TODO: Spawn particle
//        if (worldObj.isAirBlock(bpos))
//            ModHandler.get().spawnParticle(new EntityFlameFX(worldObj, posX + 0.5D, posY + 0.5D, posZ + 0.5D, 0, 0, 0));
        if (scanned.getMaterial().equals(Material.wood) && scanned.getUnlocalizedName().toLowerCase().contains("log"))
            if (checkIfTree(bpos))
                scanTree(new BlockPos(bpos));
    }

    public void scanTree(BlockPos pos)
    {
        Tree tree = new Tree();
        PriorityQueue<BlockPos> toScan = Queues.newPriorityQueue();
        ArrayList<BlockPos> scanned = Lists.newArrayList();
        toScan.add(pos);
        while (!toScan.isEmpty())
        {
            BlockPos center = toScan.poll();
            for (int i = -1; i <= 1; i++)
            {
                for (int k = -1; k <= 1; k++)
                {
                    out:
                    for (int j = -1; j <= 1; j++)
                    {
                        BlockPos target = center.add(i, j, k);
                        if (!scanned.contains(target))
                        {
                            scanned.add(target);
                            for (Tree t : trees)
                            {
                                if (t.leaves.contains(target) || t.wood.contains(target)) break out;
                            }
                            Block block = worldObj.getBlockState(target).getBlock();
                            if (isWood(block))
                            {
                                toScan.add(target);
                                tree.addWood(target);
                            }
                            if (isLeaf(block))
                            {
                                toScan.add(target);
                                tree.addLeaves(target);
                            }
                        }
                    }
                }
            }
        }
        trees.add(tree);
    }

    private boolean checkIfTree(BlockPos start)
    {
        PriorityQueue<BlockPos> q = new PriorityQueue<BlockPos>();
        q.add(start);

        ArrayList<BlockPos> visited = new ArrayList<BlockPos>();

        while (!q.isEmpty())
        {
            BlockPos element = q.poll();
            for (int j = -1; j <= 1; j++)
                for (int k = -1; k <= 1; k++)
                    for (int i = -1; i <= 1; i++)
                    {
                        BlockPos target = element.add(i, j, k);
                        if (!visited.contains(target))
                        {
                            visited.add(target);
                            Block block = worldObj.getBlockState(target).getBlock();
                            if (isWood(block))
                                q.add(target);
                            if (isLeaf(block))
                                return true;
                        }
                    }
        }
        return false;
    }

    private boolean isLeaf(Block block)
    {
        return block.getMaterial().equals(Material.leaves) && block instanceof BlockLeavesBase;
    }

    private boolean isWood(Block block)
    {
        return block.getMaterial().equals(Material.wood) && block instanceof BlockRotatedPillar;
    }


    private class Tree
    {
        public PriorityQueue<BlockPos> wood = Queues.newPriorityQueue();
        public PriorityQueue<BlockPos> leaves = Queues.newPriorityQueue();

        public Tree()
        {
        }

        public void addWood(BlockPos p)
        {
            wood.add(p);
        }

        public void addLeaves(BlockPos p)
        {
            leaves.add(p);
        }
    }
}

