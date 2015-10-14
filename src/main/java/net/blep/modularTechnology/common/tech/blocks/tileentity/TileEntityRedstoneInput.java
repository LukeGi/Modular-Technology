package net.blep.modularTechnology.common.tech.blocks.tileentity;

import net.blep.modularTechnology.common.core.blocks.tileentity.SimpleTileEntity;
import net.blep.modularTechnology.common.core.util.IRedstoneReciever;
import net.blep.modularTechnology.common.core.util.Int3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Kelan
 */
public class TileEntityRedstoneInput extends SimpleTileEntity
{
    protected Int3 reciever;
    protected boolean hasOutput = false;

    public void updateEntity()
    {
        if (!hasOutput) return;
        TileEntity te = worldObj.getTileEntity(reciever.getX(), reciever.getY(), reciever.getZ());

        if (te instanceof IRedstoneReciever)
        {
            
        }
    }

    public void onBlockBroken()
    {
        TileEntity te = worldObj.getTileEntity(reciever.getX(), reciever.getY(), reciever.getZ());

        if (te instanceof IRedstoneReciever)
        {
            ((IRedstoneReciever) te).removeInput(new Int3(xCoord, yCoord, zCoord));
        }
    }
}
