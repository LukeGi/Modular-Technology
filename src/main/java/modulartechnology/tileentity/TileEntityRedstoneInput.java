package modulartechnology.tileentity;

import com.blep.modularTechnology.core.common.blocks.tileentity.SimpleTileEntity;
import com.blep.modularTechnology.core.common.util.IRedstoneReciever;
import com.blep.modularTechnology.core.common.util.Int3;
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
