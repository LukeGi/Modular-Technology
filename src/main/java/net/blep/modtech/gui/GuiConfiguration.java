package net.blep.modtech.gui;

import javafx.util.Pair;
import net.blep.modtech.gui.container.ContainerModtechBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.Slot;

import java.util.Map;

/**
 * Created by Kelan on 07/02/2016.
 */
public class GuiConfiguration
{
    private Map<Pair<Integer, Integer>, Slot> slots;

    public void addSlot(Slot slot, int x, int y)
    {
        this.slots.put(new Pair<Integer, Integer>(x, y), slot);
    }

    public void applySlots(ContainerModtechBase container)
    {
        for (Pair<Integer, Integer> pos : slots.keySet())
        {
            Slot slot = slots.get(pos);

            container.addSlotToContainer(slot);
        }
    }

    public void addPlayerInventory(ContainerModtechBase container, InventoryPlayer inventory)
    {
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                container.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            container.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }
}
