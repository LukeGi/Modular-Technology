package net.blep.modularTechnology.common.core.gui.machines;

import net.blep.modularTechnology.common.core.gui.ContainerModtechBase;
import net.blep.modularTechnology.common.core.gui.SlotOutput;
import net.blep.modularTechnology.common.core.util.ShiftClickTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

import java.util.Collections;

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
