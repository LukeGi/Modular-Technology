package net.blep.modularTechnology.common.core.gui.machines;

import net.blep.modularTechnology.common.core.gui.ContainerModtechBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class ContainerCrusher extends ContainerModtechBase
{
    public ContainerCrusher(IInventory holder, EntityPlayer user)
    {
        super(holder, user);
        int slot = 0;
        addSlotToContainer(new Slot(holder, slot++, ))
    }
}
