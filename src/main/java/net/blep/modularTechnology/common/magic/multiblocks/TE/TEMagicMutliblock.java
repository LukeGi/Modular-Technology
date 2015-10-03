package net.blep.modularTechnology.common.magic.multiblocks.TE;

import net.blep.modularTechnology.common.magic.TileMagic;
import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public abstract class TEMagicMutliblock extends TileMagic
{
    boolean isFormed;

    @Override
    public void updateEntity()
    {
        if (worldObj.getWorldTime() % 5 == 0)
            isFormed = getMultiblock().check(worldObj, xCoord, yCoord, zCoord);
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
