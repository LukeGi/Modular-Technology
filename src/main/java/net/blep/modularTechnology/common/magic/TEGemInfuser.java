package net.blep.modularTechnology.common.magic;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class TEGemInfuser extends TileEntity
{
    private ItemStack[] slots = new ItemStack[4];
    private boolean isActive = false;

    @Override
    public void updateEntity()
    {
        if (isActive)
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 2);
        else
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
    }
}
