package net.blep.modularTechnology.common.core.gui;

import net.blep.modularTechnology.client.core.gui.GuiModtechBase;
import net.blep.modularTechnology.common.core.util.Int2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 * @author TheEpicTekkit
 */
public abstract class ContainerModtechBase extends Container
{
    protected IInventory holder;
    protected EntityPlayer user;

    public ContainerModtechBase(IInventory holder, EntityPlayer user)
    {
        this.holder = holder;
        this.user = user;
    }

    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }
}
