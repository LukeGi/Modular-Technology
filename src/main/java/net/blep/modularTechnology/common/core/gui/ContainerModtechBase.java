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
    protected int slots = 0;

    public ContainerModtechBase(IInventory holder, EntityPlayer user)
    {
        this.holder = holder;
        this.user = user;
        setupInventorySlots(8, 125, 8, 183, 18, 18, 0, 0);
    }

    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

    public void setupInventorySlots(int invX, int invY, int hotbarX, int hotbarY, int slotWidth, int slotHeight, int xSpace, int ySpace)
    {
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(user.inventory, j + i * 9 + 9, invX + xSpace+ j * slotWidth, invY + ySpace + i * slotHeight));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(user.inventory, i, hotbarX + xSpace + i * slotWidth, hotbarY));
        }
    }
}
