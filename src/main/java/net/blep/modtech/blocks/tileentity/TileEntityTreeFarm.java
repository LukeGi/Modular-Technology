package net.blep.modtech.blocks.tileentity;

import com.google.common.collect.Lists;
import net.blep.modtech.blocks.multiblock.MultiblockPattern;
import net.blep.modtech.core.util.Vector3i;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class TileEntityTreeFarm extends TileEntityMultiblock {

    private int x, y, z, nx = 5;
    private int xS = 0, zS = 0;
    private int radius = 2;
    private IInventory output;
    private PriorityQueue<Vector3i> cuttingList = new PriorityQueue<Vector3i>();

    //private int[][] aboveMultiBlocks = {{-1, 1, 0}, {-1, 1, 1}, {-1, 1, -1}, {0, 1, 0}, {0, 1, 1}, {0, 1, -1}, {1, 1, 0}, {1, 1, 1}, {1, 1, -1}};

    //private Multiblock treefarm = new Multiblock(new MultiblockBlockCoordPair[]{new MultiblockBlockCoordPair(MagicBlockHandler.treeFarm, Vector3i.ni3a(0, 0, 0)), new MultiblockBlockCoordPair(Blocks.lapis_block, Vector3i.ni3a(1, 0, 0, -1, 0, 0, 0, 0, 1, 0, 0, -1)), new MultiblockBlockCoordPair(Blocks.iron_block, Vector3i.ni3a(1, 0, 1, 1, 0, -1, -1, 0, 1, -1, 0, -1))});

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if (!isMaster())
            return;
        if (!worldObj.isRemote)
        {
            if (worldObj.getTotalWorldTime() % 64 == 0) output = (IInventory) worldObj.getTileEntity(x, y, z);
            if (/*isFormed && */output != null && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
            {
                if (worldObj.getTotalWorldTime() % 32 == 0)
                    cutTree();
                if (worldObj.getTotalWorldTime() % 32 == 29)
                    for (int i = 0; i < radius; i++)
                        ScanTrees(0);
                if (worldObj.getTotalWorldTime() % 32 == 30)
                    scanWorld();
                if (worldObj.getTotalWorldTime() % 32 == 31)
                    plantSaplings();
            }
        }
    }

    private void plantSaplings()
    {
        for (int i = -radius; i <= radius; i++)
            for (int j = -radius; j <= radius; j++)
            {
                Vector3i pos = new Vector3i(i, 1, j);
                Vector3i wpot = new Vector3i(xCoord, yCoord, zCoord);
                wpot.setX(wpot.getX() + pos.getX());
                wpot.setY(wpot.getY() + pos.getY());
                wpot.setZ(wpot.getZ() + pos.getZ());
                plantSapling(wpot);
            }
    }

    private void scanWorld()
    {
        for (ItemStack stack : getEntitiesSpawnedByFarm())
            putBlockInOutputInventory(stack, 0);
    }

    public List<ItemStack> getEntitiesSpawnedByFarm()
    {
        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord - radius - 10, yCoord, zCoord - radius - 10, xCoord + radius + 10, yCoord + 10, zCoord + radius + 10);

        List result = worldObj.getEntitiesWithinAABB(EntityItem.class, aabb);
        List<ItemStack> stacks = Lists.newArrayList();
        for (Object o : result.toArray())
        {
            if (!(o instanceof EntityItem)) continue;

            EntityItem ei = (EntityItem) o;
            if (ei.delayBeforeCanPickup > 1000000 || ei.delayBeforeCanPickup <= 2)
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

    private void plantSapling(Vector3i p)
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

    private void ScanTrees(int counter)
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
        if (isWood(worldObj.getBlock(xCoord + xS, yCoord + 1, zCoord + zS)))
        {
            pathFind(xCoord + xS, yCoord + 1, zCoord + zS);
            return;
        }
        if (counter < radius)
            ScanTrees(++counter);
    }

    private void pathFind(int x, int y, int z)
    {
        Queue<Vector3i> q = new PriorityQueue<Vector3i>();
        Vector3i start = new Vector3i(x, y, z);
        q.add(start);

        ArrayList<Vector3i> visited = new ArrayList<Vector3i>();

        while (!q.isEmpty())
        {
            Vector3i element = q.poll();
            for (int j = -1; j <= 1; j++)
                for (int k = -1; k <= 1; k++)
                    for (int i = -1; i <= 1; i++)
                    {
                        Vector3i target = new Vector3i(element.getX() + i, element.getY() + j, element.getZ() + k);
                        Block block = worldObj.getBlock(target.getX(), target.getY(), target.getZ());
                        if (isWood(block) || isLeaf(block))
                            if (!cuttingList.contains(target) && !visited.contains(target))
                            {
                                visited.add(target);
                                q.add(target);
                                cuttingList.add(target);
                            }
                    }
        }
    }

    private boolean isLeaf(Block block)
    {
        return block.getMaterial().equals(Material.leaves) && block instanceof BlockLeavesBase;
    }

    private boolean isWood(Block block)
    {
        return block.getMaterial().equals(Material.wood) && block instanceof BlockRotatedPillar;
    }

    public void cutTree()
    {
        if (output == null) return;
        if (cuttingList.isEmpty()) return;
        Vector3i pos = cuttingList.poll();
        Block currentBlock = worldObj.getBlock(pos.getX(), pos.getY(), pos.getZ());
        boolean yon = currentBlock.isLeaves(worldObj, pos.getX(), pos.getY(), pos.getZ());
        if (currentBlock.getMaterial().equals(Material.air))
        {
            cutTree();
            return;
        }
        List<ItemStack> drops = currentBlock.getDrops(worldObj, pos.getX(), pos.getY(), pos.getZ(), worldObj.getBlockMetadata(pos.getX(), pos.getY(), pos.getZ()), 1);
        //ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, pos.getX(), pos.getY(), pos.getZ())); TODO
        worldObj.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, currentBlock.stepSound.getBreakSound(), (currentBlock.stepSound.getVolume() + 1.0F) / 2.0F, currentBlock.stepSound.getPitch() * 0.8F);

        for (ItemStack stack : drops)
        {
            EntityItem e = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, stack);
            e.delayBeforeCanPickup = 1080654;
            worldObj.spawnEntityInWorld(e);
        }
        if (yon) cutTree();
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
        } else if (!worldObj.isRemote)
            worldObj.spawnEntityInWorld(new EntityItem(worldObj, x + 0.5, y + 0.5, z + 0.5, stack));
    }

    //---------------------------------TODO-----------------------------------------

    @Override
    public int getMasterX() {
        return 0;
    }

    @Override
    public int getMasterY() {
        return 0;
    }

    @Override
    public int getMasterZ() {
        return 0;
    }

    @Override
    public boolean isMaster() {
        return false;
    }

    @Override
    public boolean hasMaster() {
        return false;
    }

    @Override
    public MultiblockPattern getPattern() {
        return null;
    }
}
