package com.blep.modularTechnology.core.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author Kelan
 */
public class SlotOutput extends Slot
{
    public SlotOutput(IInventory holder, int id, int x, int y)
    {
        super(holder, id, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return false;
    }
}
