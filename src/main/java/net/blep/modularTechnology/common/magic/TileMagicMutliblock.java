package net.blep.modularTechnology.common.magic;

import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public abstract class TileMagicMutliblock extends TileMagic
{
    boolean isFormed;

    @Override
    public void updateEntity()
    {
        if (worldObj.getWorldTime() % 5 == 0)
        {
            isFormed = getMultiblock().check(worldObj, xCoord, yCoord, zCoord);
//            if (worldObj.getWorldTime() % 100 == 0) LogHelper.info(isFormed ? "true" : "false");
        }
    }

    protected abstract Multiblock getMultiblock();

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
