package net.blep.modularTechnology.common.magic.blocks.tile.multiblocks.TE;

import com.blep.modularTechnology.core.common.ModularTechnology;
import net.blep.modularTechnology.common.magic.blocks.tile.TileMagic;
import net.blep.modularTechnology.common.magic.blocks.tile.multiblocks.Multiblock;
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
        if (!isFormed && worldObj.getWorldTime() % 10 == 0) ModularTechnology.LOGGER.info("not formed");
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
