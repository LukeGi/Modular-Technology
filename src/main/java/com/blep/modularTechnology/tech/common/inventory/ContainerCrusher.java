package com.blep.modularTechnology.tech.common.inventory;

import modulartechnology.inventory.ContainerModtechBase;
import modulartechnology.inventory.SlotOutput;
import com.blep.modularTechnology.core.common.util.ShiftClickTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

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

    @Override
    public ShiftClickTarget getShiftClickTarget(ItemStack stack, EntityPlayer player)
    {
        return ShiftClickTarget.of(0, 1);
    }
}
