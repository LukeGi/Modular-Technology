package blep.modtech.tileentity;

import blep.modtech.entity.fx.EntityBlueFlameFX;
import blep.modtech.network.ModteckPacketHandler;
import blep.modtech.network.message.MessageSpawnParticle;
import blep.modtech.proxy.ClientProxy;
import com.google.common.collect.Lists;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
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
import net.minecraft.util.Vec3;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class TileEntityTreeFarm extends TileEntity implements ITickable
{
    public int scanx = 0, scany = 1, scanz = 0, radius = 4;

    public ArrayList<Tree> trees = Lists.newArrayList();

    private int[][] around = {
            {0, 0, 1},
            {0, 1, 0},
            {0, 1, 1},
            {1, 0, 0},
            {1, 0, 1},
            {1, 1, 0},
            {1, 1, 1}
    };

    @Override
    public void update()
    {
        if (!worldObj.isRemote)
        {
            if (treefarmShouldFunction())
                if (worldObj.getTotalWorldTime() % 2 == 0)
                {
                    cutTrees();
                    speedUpHopper();
                }
            if (worldObj.getTotalWorldTime() % 2 == 1)
            {
                cleanForest();
                scanForTrees();
            }
        }
    }

    private boolean treefarmShouldFunction()
    {
        TileEntity genericTileEntity = worldObj.getTileEntity(pos.add(0, 1, 0));
        if (genericTileEntity instanceof TileEntityChest)
        {
            TileEntityChest chest = (TileEntityChest) genericTileEntity;
            boolean isRoomInInvFlag = false;
            for (int i = 0; i < chest.getSizeInventory(); i++)
            {
                if (!isRoomInInvFlag)
                {
                    ItemStack stack = chest.getStackInSlot(i);
                    isRoomInInvFlag = isRoomInInvFlag || stack == null;
                }
            }
            return isRoomInInvFlag;
        }
        return false;
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
        for (int i = 0; i < trees.size(); i++)
            if (trees.get(i).leaves.isEmpty() && trees.get(i).wood.isEmpty())
                trees.remove(i);
    }

    private void cutTrees()
    {
        breakBlock(pos.add(0, 3, 0));
        for (Tree tree : trees)
        {
            if (!breakBlock(tree.getNextLeaf()))
                breakBlock(tree.getNextLog());
        }
        cleanForest();
    }

    private boolean breakBlock(BlockPos p)
    {
        if (p == null) return false;

        Block block = worldObj.getBlockState(p).getBlock();
        if (block instanceof BlockAir) return true;
        IBlockState blockState = worldObj.getBlockState(p);
        ArrayList<ItemStack> drops = (ArrayList<ItemStack>) block.getDrops(worldObj, p, blockState, 0);
        worldObj.setBlockToAir(p);
        worldObj.playSoundEffect(p.getX() + 0.5F, p.getY() + 0.5F, p.getZ() + 0.5F, block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getFrequency() * 0.8F);
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                for (int k = 0; k < 4; k++)
                    ModteckPacketHandler.INSTANCE.sendToDimension(new MessageSpawnParticle(p.getX() + 1F / i, p.getY() + 1F / j, p.getZ() + 1F / k, 1 - 2 * worldObj.rand.nextDouble(), 0, 1 - 2 * worldObj.rand.nextDouble(), EnumParticleTypes.BLOCK_DUST, new int[]{Block.getStateId(blockState)}), worldObj.provider.getDimensionId());
        for (ItemStack stack : drops)
        {
            EntityItem drop = new EntityItem(worldObj, pos.getX() + 0.5f, pos.getY() + 3.5f, pos.getZ() + 0.5f, stack);
            drop.setVelocity(0, 0, 0);
            worldObj.spawnEntityInWorld(drop);
        }
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
            ModteckPacketHandler.INSTANCE.sendToDimension(new MessageSpawnParticle(bpos.getX() + 0.5D, bpos.getY() + 0.5D, bpos.getZ() + 0.5D, 0, 0, 0, EnumParticleTypes.FLAME, new int[0]), worldObj.provider.getDimensionId());
            plantSapling(bpos);
        } else if (worldObj.isAirBlock(bpos.add(0, 1, 0)))
        { //TODO: Make it so that the particle goes to a place
            Vec3 dest = new Vec3(pos.getX() + 0.5, pos.getY() + 3.5, pos.getZ() + 0.5).subtract(new Vec3(bpos.getX(), bpos.getY(), bpos.getZ()).normalize());
            ClientProxy.spawnEntityFX(new EntityBlueFlameFX(worldObj, bpos.getX() + 0.5D, bpos.getY() + 1.5D, bpos.getZ() + 0.5D, 0.05 * dest.xCoord, 0.05 * dest.yCoord, 0.05 * dest.zCoord));
//            ModteckPacketHandler.INSTANCE.sendToDimension(new MessageSpawnParticle(bpos.getX() + 0.5D, bpos.getY() + 1.5D, bpos.getZ() + 0.5D, 0, 0, 0, EnumParticleTypes.FLAME, new int[0]), worldObj.provider.getDimensionId());
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
            for (Tree tree : trees)
            {
                for (BlockPos leafPos : tree.leaves)
                    if (leafPos.equals(bpos))
                    {
                        isInFlag = true;
                        break out;
                    }
                for (BlockPos woodPos : tree.wood)
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
        Tree tree = new Tree();
        Stack<BlockPos> toScan = new Stack<BlockPos>();
        ArrayList<BlockPos> scanned = new ArrayList<BlockPos>();
        toScan.add(pos);
        while (!toScan.isEmpty())
        {
            BlockPos center = toScan.pop();
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

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("ScanX", scanx);
        nbt.setInteger("ScanY", scany);
        nbt.setInteger("ScanZ", scanz);
        nbt.setInteger("TreeListSize", trees.size());
        for (int i = 0; i < trees.size(); i++)
            trees.get(i).writeToNBT(nbt, i);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        scanx = nbt.getInteger("ScanX");
        scany = nbt.getInteger("ScanY");
        scanz = nbt.getInteger("ScanZ");
        int treesToGet = nbt.getInteger("TreeListSize");
        for (int i = 0; i < treesToGet; i++)
            trees.add(new Tree().readFromNBT(nbt, i));
    }

    private class Tree
    {
        public Stack<BlockPos> wood = new Stack<BlockPos>();
        public Stack<BlockPos> leaves = new Stack<BlockPos>();

        public Tree()
        {
        }

        public void addWood(BlockPos p)
        {
            wood.push(p);
        }

        public void addLeaves(BlockPos p)
        {
            leaves.push(p);
        }

        public BlockPos getNextLeaf()
        {
            return leaves.size() == 0 ? null : leaves.pop();
        }

        public BlockPos getNextLog()
        {
            return wood.size() == 0 ? null : wood.pop();
        }

        public void writeToNBT(NBTTagCompound compound, int i)
        {
            NBTTagCompound compoundTree = new NBTTagCompound();
            compoundTree.setInteger("WoodListSize", wood.size());
            for (int j = 0; j < wood.size(); j++)
            {
                BlockPos pos = wood.pop();
                compoundTree.setDouble("Wood:" + j + ":X", pos.getX());
                compoundTree.setDouble("Wood:" + j + ":Y", pos.getY());
                compoundTree.setDouble("Wood:" + j + ":Z", pos.getZ());
            }
            compoundTree.setInteger("LeavesListSize", leaves.size());
            for (int j = 0; j < leaves.size(); j++)
            {
                BlockPos pos = leaves.pop();
                compoundTree.setDouble("Leaves:" + j + ":X", pos.getX());
                compoundTree.setDouble("Leaves:" + j + ":Y", pos.getY());
                compoundTree.setDouble("Leaves:" + j + ":Z", pos.getZ());
            }
            compound.setTag("Tree:" + i, compoundTree);
        }

        public Tree readFromNBT(NBTTagCompound nbt, int i)
        {
            NBTTagCompound compoundTree = (NBTTagCompound) nbt.getTag("Tree:" + i);
            int WoodListSize = compoundTree.getInteger("WoodListSize");
            int LeavesListSize = compoundTree.getInteger("LeavesListSize");
            for (int j = 0; j < WoodListSize; j++)
                wood.add(new BlockPos(compoundTree.getDouble("Wood:" + j + ":X"), compoundTree.getDouble("Wood:" + j + ":Y"), compoundTree.getDouble("Wood:" + j + ":Z")));
            for (int j = 0; j < LeavesListSize; j++)
                wood.add(new BlockPos(compoundTree.getDouble("Leaves:" + j + ":X"), compoundTree.getDouble("Leaves:" + j + ":Y"), compoundTree.getDouble("Leaves:" + j + ":Z")));
            return this;
        }
    }
}

