package net.blep.modularTechnology.common.magic;

import net.blep.modularTechnology.common.core.util.LogHelper;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
public class TETreeFarm extends TileMagic
{
    boolean isFormed = false;

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if (worldObj.getWorldTime() % 5 == 0)
        {
            isFormed = Multiblock.checkMultiblock(worldObj, xCoord, yCoord, zCoord, Multiblock.treefarm);
            if (worldObj.rand.nextInt(100) == 0) LogHelper.info(isFormed ? "true" : "false");
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt)
    {
        if (nbt == null) return;

        nbt.setBoolean("formed?", isFormed);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        if (nbt == null) return;

        isFormed = nbt.getBoolean("formed?");
    }
}
