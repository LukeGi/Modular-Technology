package net.blep.modtech.blocks.tileentity;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import net.blep.modtech.core.networking.NetworkManagerModtech;
import net.blep.modtech.core.networking.packets.MessageSpawnParticle;
import net.blep.modtech.core.proxy.ModHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class TileEntityTreeFarm extends ModTileEntity
{
    public int scanx = 0, scany = 1, scanz = 0, radius = 10;

    public ArrayList<Tree> trees = Lists.newArrayList();

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if (!worldObj.isRemote)
        {
            breakBlock(new WorldPosition(xCoord, yCoord + 3, zCoord, 0));
            for (int i = 0; i < radius; i++) scanForTrees();
            cutTrees();
            cleanForest();
            speedUpHopper();
            plantSaplings();
        }
    }

    private void speedUpHopper()
    {
        TileEntity tileEntity = worldObj.getTileEntity(xCoord, yCoord + 2, zCoord);
        if (!(tileEntity instanceof TileEntityHopper)) return;
        TileEntityHopper hopper = (TileEntityHopper) tileEntity;
        for (int i = 0; i < 20 * 8; i++)
            hopper.updateEntity();
    }

    private void plantSaplings()
    {
        for (int i = -radius; i <= radius; i++)
            for (int j = -radius; j <= radius; j++)
            {
                WorldPosition pos = new WorldPosition(i, 1, j, 0);
                WorldPosition worldPos = new WorldPosition(xCoord, yCoord, zCoord, 0);
                worldPos.setX(worldPos.getX() + pos.getX());
                worldPos.setY(worldPos.getY() + pos.getY());
                worldPos.setZ(worldPos.getZ() + pos.getZ());
                plantSapling(worldPos);
            }
    }

    private void plantSapling(WorldPosition p)
    {
        Block block = worldObj.getBlock(p.getX(), p.getY(), p.getZ());
        TileEntity tile = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
        if (!(tile instanceof IInventory)) return;
        IInventory output = (IInventory) tile;
        if (block.isReplaceable(worldObj, p.getX(), p.getY(), p.getZ()))
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

    private void breakBlock(WorldPosition p)
    {
        try
        {
            Block block = worldObj.getBlock(p.x, p.y, p.z);
            if (block instanceof BlockAir) return;
            int metadata = worldObj.getBlockMetadata(p.x, p.y, p.z);
            ArrayList<ItemStack> drops = block.getDrops(worldObj, p.x, p.y, p.z, metadata, 0);
            worldObj.setBlockToAir(p.x, p.y, p.z);
            worldObj.playSoundEffect(p.getX() + 0.5F, p.getY() + 0.5F, p.getZ() + 0.5F, block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
            NetworkManagerModtech.networkManager.sendToDimension(new MessageSpawnParticle(MessageSpawnParticle.ParticleTypes.BLOCKBREAK, p.x + 0.5f, p.y + 0.5f, p.z + 0.5f, 16, Block.getIdFromBlock(block)), worldObj.provider.dimensionId);
            for (ItemStack stack : drops)
            {
                EntityItem drop = new EntityItem(worldObj, xCoord + 0.5f, yCoord + 3.5f, zCoord + 0.5f, stack);
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
        int posX = xCoord + scanx;
        int posY = yCoord + scany;
        int posZ = zCoord + scanz;

        Block scanned = worldObj.getBlock(posX, posY, posZ);
        if (scanned.isReplaceable(worldObj, posX, posY, posZ))
            worldObj.setBlockToAir(posX, posY, posZ);
        if (worldObj.isAirBlock(posX, posY, posZ))
            ModHandler.get().spawnParticle(new EntityFlameFX(worldObj, posX + 0.5D, posY + 0.5D, posZ + 0.5D, 0, 0, 0));
        if (scanned.getMaterial().equals(Material.wood) && scanned.getUnlocalizedName().toLowerCase().contains("log"))
            if (checkIfTree(posX, posY, posZ))
                scanTree(new WorldPosition(posX, posY, posZ, 0));
    }

    public void scanTree(WorldPosition pos)
    {
        Tree tree = new Tree();
        PriorityQueue<WorldPosition> toScan = Queues.newPriorityQueue();
        ArrayList<WorldPosition> scanned = Lists.newArrayList();
        toScan.add(pos);
        while (!toScan.isEmpty())
        {
            WorldPosition center = toScan.poll();
            for (int i = -1; i <= 1; i++)
            {
                for (int k = -1; k <= 1; k++)
                {
                    out:
                    for (int j = -1; j <= 1; j++)
                    {
                        WorldPosition target = new WorldPosition(center.x + i, center.y + j, center.z + k, center.distance + 1);
                        if (!scanned.contains(target))
                        {
                            scanned.add(target);
                            for (Tree t : trees)
                            {
                                if (t.leaves.contains(target) || t.wood.contains(target)) break out;
                            }
                            Block block = worldObj.getBlock(target.x, target.y, target.z);
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

    private boolean checkIfTree(int x, int y, int z)
    {
        PriorityQueue<WorldPosition> q = new PriorityQueue<>();
        WorldPosition start = new WorldPosition(x, y, z, 0);
        q.add(start);

        ArrayList<WorldPosition> visited = new ArrayList<>();

        while (!q.isEmpty())
        {
            WorldPosition element = q.poll();
            for (int j = -1; j <= 1; j++)
                for (int k = -1; k <= 1; k++)
                    for (int i = -1; i <= 1; i++)
                    {
                        WorldPosition target = new WorldPosition(element.getX() + i, element.getY() + j, element.getZ() + k, element.getDistance() + 1);
                        if (!visited.contains(target))
                        {
                            visited.add(target);
                            Block block = worldObj.getBlock(target.getX(), target.getY(), target.getZ());
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

    private class WorldPosition implements Comparable<WorldPosition>
    {
        public int distance, x, y, z;

        public WorldPosition(int x, int y, int z, int distance)
        {
            this.distance = distance;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getDistance()
        {
            return distance;
        }

        public int getX()
        {
            return x;
        }

        public int getY()
        {
            return y;
        }

        public int getZ()
        {
            return z;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WorldPosition that = (WorldPosition) o;
            return x == that.x && y == that.y && z == that.z;
        }

        @Override
        public int hashCode()
        {
            int result = distance;
            result = 31 * result + x;
            result = 31 * result + y;
            result = 31 * result + z;
            return result;
        }

        public int compareTo(WorldPosition o)
        {
            return this.distance > o.distance ? 1 : -1;
        }

        public void setDistance(int distance)
        {
            this.distance = distance;
        }

        public void setX(int x)
        {
            this.x = x;
        }

        public void setY(int y)
        {
            this.y = y;
        }

        public void setZ(int z)
        {
            this.z = z;
        }
    }

    private class Tree
    {
        public PriorityQueue<WorldPosition> wood = Queues.newPriorityQueue();
        public PriorityQueue<WorldPosition> leaves = Queues.newPriorityQueue();

        public Tree()
        {
        }

        public void addWood(WorldPosition p)
        {
            wood.add(p);
        }

        public void addLeaves(WorldPosition p)
        {
            leaves.add(p);
        }
    }
}
