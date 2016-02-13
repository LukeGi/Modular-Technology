package net.blep.modtech.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Kelan on 07/02/2016.
 */
public abstract class ContainerModtechBase extends Container
{
    protected TileEntity tile;

    public ContainerModtechBase(TileEntity tile)
    {
        this.tile = tile;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

    @Override
    public Slot addSlotToContainer(Slot slot)
    {
        return super.addSlotToContainer(slot);
    }
}
