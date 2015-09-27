package net.blep.modularTechnology.common.core.gui.machines;

import net.blep.modularTechnology.common.core.gui.ContainerModtechBase;
import net.blep.modularTechnology.common.core.gui.SlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerCrusher extends ContainerModtechBase
{
    public ContainerCrusher(IInventory holder, EntityPlayer user)
    {
        super(holder, user);
        addSlotToContainer(new Slot(holder, slots++, 26, 18));
        addSlotToContainer(new Slot(holder, slots++, 7, 84));
        addSlotToContainer(new SlotOutput(holder, slots++, 82, 18));
        addSlotToContainer(new SlotOutput(holder, slots++, 62, 46));
        addSlotToContainer(new SlotOutput(holder, slots++, 82, 46));

    }
}
