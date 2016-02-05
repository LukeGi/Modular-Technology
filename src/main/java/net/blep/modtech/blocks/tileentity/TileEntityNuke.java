package net.blep.modtech.blocks.tileentity;

import net.blep.modtech.explosion.ExplosionNuke;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityTNTPrimed;

/**
 * Created by Kelan on 28/01/2016.
 */
public class TileEntityNuke extends ModTileEntity
{
    private boolean fuse = false;
    private int counter = 60;
    private float radius = 50;
    private float energy = 100000;

    @Override
    public void updateEntity()
    {
        if (!fuse && worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
        {
            fuse = true;
            EntityTNTPrimed tnt = new EntityTNTPrimed(worldObj, xCoord, yCoord, zCoord, Minecraft.getMinecraft().thePlayer);
            tnt.setDead();
            worldObj.spawnEntityInWorld(tnt);
            new ExplosionNuke(worldObj, tnt, xCoord, yCoord, zCoord, radius * energy, radius, energy).explode(xCoord, yCoord, zCoord);
//            worldObj.setBlockToAir(xCoord, yCoord, zCoord);
        }

    }

    private synchronized void setBlock(int x, int y, int z, Block block)
    {
        worldObj.setBlock(x, y, z, block);
    }
}
