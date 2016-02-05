package net.blep.modtech.blocks.tileentity;

import net.blep.modtech.blocks.multiblock.MultiblockPattern;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Kelan on 24/01/2016.
 */
public abstract class TileEntityMultiblock extends ModTileEntity
{
    public abstract int getMasterX();

    public abstract int getMasterY();

    public abstract int getMasterZ();

    public abstract boolean isMaster();

    public abstract boolean hasMaster();

    public abstract MultiblockPattern getPattern();
}
