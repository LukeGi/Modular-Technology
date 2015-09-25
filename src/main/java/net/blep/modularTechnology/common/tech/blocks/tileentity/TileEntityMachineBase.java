package net.blep.modularTechnology.common.tech.blocks.tileentity;

import net.blep.modularTechnology.client.tech.ClientProxy;
import net.blep.modularTechnology.common.core.blocks.tileentity.SimpleTileEntity;
import net.blep.modularTechnology.common.core.blocks.tileentity.TileEntityContainerHolder;
import net.blep.modularTechnology.common.core.util.ITileRotatable;
import net.blep.modularTechnology.common.core.util.MethodHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author TheEpicTekkit
 */
public abstract class TileEntityMachineBase extends TileEntityContainerHolder implements ISidedInventory, ITileRotatable
{
    private boolean active;
    private boolean didUpdate;
    private ForgeDirection orientation;
    private boolean[] validOrientations = {false, false, true, true, true, true};

    public void updateEntity()
    {
        if (didUpdate)
        {
            validateState();
            didUpdate = false;
        }
    }

    public boolean getActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
        updateState();
    }

    public ForgeDirection getOrientation()
    {
        return orientation;
    }

    public void setOrientation(ForgeDirection dir)
    {
        orientation = dir;
        updateState();
    }

    public void updateState()
    {
        didUpdate = true;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    public void validateState()
    {
        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, orientation.ordinal()/* + (active ? 6 : 0)*/, 2);
    }

    public boolean onRotated(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        ForgeDirection orientation = ForgeDirection.values()[side];
        System.out.println("rotating to face side " + side + "(" + orientation + "). Meta is " + world.getBlockMetadata(x, y, z));

        if (getValidOrientations()[side])
        {
            setOrientation(orientation);
        }
        updateState();
        return true;
    }

    public boolean[] getValidOrientations()
    {
        return validOrientations;
    }
}