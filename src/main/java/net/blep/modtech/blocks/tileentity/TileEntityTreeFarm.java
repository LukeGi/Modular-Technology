package net.blep.modtech.blocks.tileentity;

import net.blep.modtech.core.networking.NetworkManagerModtech;
import net.blep.modtech.core.networking.packets.MessageSpawnParticle;
import net.blep.modtech.core.proxy.ModHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class TileEntityTreeFarm extends ModTileEntity {
    public int scanx = 0, scany = 1, scanz = 0, radius = 4;

    public PriorityQueue<WorldPosition> cutQ = new PriorityQueue<>();

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote) {
            if (worldObj.getTotalWorldTime() % 10 == 0)
                scanForTrees();
            if (worldObj.getTotalWorldTime() % 10 > 8)
                chopTree(0);
        }
    }

    private ArrayList<EntityItem> chopTree(int recursiveIndex) {
        if (cutQ.isEmpty()) return null;
        ArrayList<EntityItem> items = new ArrayList<>();
        WorldPosition blockPosition = cutQ.poll();
        Block block = worldObj.getBlock(blockPosition.x, blockPosition.y, blockPosition.z);
        int metadata = worldObj.getBlockMetadata(blockPosition.x, blockPosition.y, blockPosition.z);
        ArrayList<ItemStack> ISIS = block.getDrops(worldObj, blockPosition.x, blockPosition.y, blockPosition.z, metadata, 0);
        worldObj.setBlockToAir(blockPosition.x, blockPosition.y, blockPosition.z);
        worldObj.playSoundEffect(blockPosition.getX() + 0.5F, blockPosition.getY() + 0.5F, blockPosition.getZ() + 0.5F, block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
        for (ItemStack stack : ISIS)
            items.add(new EntityItem(worldObj, xCoord + 0.5f, yCoord + 3.5f, zCoord + 0.5f, stack));
        NetworkManagerModtech.networkManager.sendToDimension(new MessageSpawnParticle(MessageSpawnParticle.ParticleTypes.BLOCKBREAK, blockPosition.x + 0.5f, blockPosition.y + 0.5f, blockPosition.z + 0.5f, 16, Block.getIdFromBlock(block)), worldObj.provider.dimensionId);
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                for (int k = -1; k <= 1; k++)
                    if (Math.abs(i) + Math.abs(j) + Math.abs(k) != 3) {
                        WorldPosition target = new WorldPosition(blockPosition.x + i, blockPosition.y + j, blockPosition.z + k, 0);
                        Block nblock = worldObj.getBlock(target.x, target.y, target.z);
                        if (!cutQ.contains(target)) {
                            if (isWood(nblock)) {
                                target.setDistance(target.y);
                                cutQ.add(target);
                            }
                            if (isLeaf(nblock)) {
                                target.setDistance(-1 * target.y);
                                cutQ.add(target);
                            }
                        }
                    }
        if (isLeaf(block)) {
            ArrayList<EntityItem> nitems = chopTree(recursiveIndex + 1);
            if (nitems != null)
                for (EntityItem item : nitems)
                    items.add(item);
        }
        if (recursiveIndex == 0) {
            for (EntityItem item : items) {
                item.setVelocity(0, 0, 0);
                worldObj.spawnEntityInWorld(item);
            }
            return null;
        } else
            return items;
    }

    public void scanForTrees() {
        scanx++;
        if (scanx > radius) {
            scanx = -radius;
            scanz++;
            if (scanz > radius)
                scanz = -radius;
        }
        int posX = xCoord + scanx;
        int posY = yCoord + scany;
        int posZ = zCoord + scanz;

        Block scanned = worldObj.getBlock(posX, posY, posZ);
        if (worldObj.isAirBlock(posX, posY, posZ))
            ModHandler.get().spawnParticle(new EntityFlameFX(worldObj, posX + 0.5D, posY + 0.5D, posZ + 0.5D, 0, 0, 0));
        if (scanned.getMaterial().equals(Material.wood) && scanned.getUnlocalizedName().contains("log"))
            if (checkIfTree(posX, posY, posZ))
                cutQ.add(new WorldPosition(posX, posY, posZ, 0));
    }

    private boolean checkIfTree(int x, int y, int z) {
        PriorityQueue<WorldPosition> q = new PriorityQueue<>();
        WorldPosition start = new WorldPosition(x, y, z, 0);
        q.add(start);

        ArrayList<WorldPosition> visited = new ArrayList<>();

        while (!q.isEmpty()) {
            WorldPosition element = q.poll();
            for (int j = -1; j <= 1; j++)
                for (int k = -1; k <= 1; k++)
                    for (int i = -1; i <= 1; i++) {
                        WorldPosition target = new WorldPosition(element.getX() + i, element.getY() + j, element.getZ() + k, element.getDistance() + 1);
                        if (!visited.contains(target)) {
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

    private boolean isLeaf(Block block) {
        return block.getMaterial().equals(Material.leaves) && block instanceof BlockLeavesBase;
    }

    private boolean isWood(Block block) {
        return block.getMaterial().equals(Material.wood) && block instanceof BlockRotatedPillar;
    }

    private class WorldPosition implements Comparable<WorldPosition> {
        public int distance, x, y, z;

        public WorldPosition(int x, int y, int z, int distance) {
            this.distance = distance;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getDistance() {
            return distance;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WorldPosition that = (WorldPosition) o;
            return x == that.x && y == that.y && z == that.z;
        }

        @Override
        public int hashCode() {
            int result = distance;
            result = 31 * result + x;
            result = 31 * result + y;
            result = 31 * result + z;
            return result;
        }

        public int compareTo(WorldPosition o) {
            return this.distance > o.distance ? 1 : -1;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
}
