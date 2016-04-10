package blep.modtech.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ContainerBase<T extends TileEntityBaseGui> extends Container
{
    private final int HOTBAR_SLOT_COUNT = 9;
    private final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private final int VANILLA_FIRST_SLOT_INDEX = 0;
    private final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private final int TE_INVENTORY_ROW_COUNT;
    private final int TE_INVENTORY_COLUMN_COUNT;
    private final int TE_INVENTORY_SLOT_COUNT;

    public T tile;


    public ContainerBase(InventoryPlayer inventory, T tile, int inRowCount, int inColumnCount, int playerinvoffsetX, int playerinvoffsetY, int hotbarOffsetX, int hotbarOffsetY, int tileinvoffsetX, int tileinvoffsetY)
    {
        TE_INVENTORY_ROW_COUNT = inRowCount;
        TE_INVENTORY_COLUMN_COUNT = inColumnCount;
        TE_INVENTORY_SLOT_COUNT = TE_INVENTORY_COLUMN_COUNT * TE_INVENTORY_ROW_COUNT;

        this.tile = tile;

        final int SLOT_X_SPACING = 18;
        final int SLOT_Y_SPACING = 18;
        int slotnum = 0;

        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++)
        {
            addSlotToContainer(new Slot(inventory, slotnum++, hotbarOffsetX + SLOT_X_SPACING * x, hotbarOffsetY));
        }

        for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++)
        {
            for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++)
            {
                int xpos = playerinvoffsetX + x * SLOT_X_SPACING;
                int ypos = playerinvoffsetY + y * SLOT_Y_SPACING;
                addSlotToContainer(new Slot(inventory, slotnum++, xpos, ypos));
            }
        }

        if (TE_INVENTORY_SLOT_COUNT != tile.getSizeInventory())
        {
            System.err.println("Mismatched slot count in ContainerBasic(" + TE_INVENTORY_SLOT_COUNT
                    + ") and TileInventory (" + tile.getSizeInventory() + ")");
        }
        slotnum = 0;
        for (int y = 0; y < TE_INVENTORY_ROW_COUNT; y++)
        {
            for (int x = 0; x < TE_INVENTORY_COLUMN_COUNT; x++)
            {
                addSlotToContainer(new Slot(tile, slotnum++, tileinvoffsetX + SLOT_X_SPACING * x, tileinvoffsetY + SLOT_Y_SPACING * y));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return tile.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int sourceSlotIndex)
    {
        Slot sourceSlot = inventorySlots.get(sourceSlotIndex);
        if (sourceSlot == null || !sourceSlot.getHasStack()) return null;
        ItemStack sourceStack = sourceSlot.getStack();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (sourceSlotIndex >= VANILLA_FIRST_SLOT_INDEX && sourceSlotIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT)
        {
            if (!mergeItemStack(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT, false))
            {
                return null;
            }
        } else if (sourceSlotIndex >= TE_INVENTORY_FIRST_SLOT_INDEX && sourceSlotIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT)
        {
            if (!mergeItemStack(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false))
            {
                return null;
            }
        } else
        {
            System.err.print("Invalid slotIndex:" + sourceSlotIndex);
            return null;
        }

        if (sourceStack.stackSize == 0)
        {
            sourceSlot.putStack(null);
        } else
        {
            sourceSlot.onSlotChanged();
        }

        sourceSlot.onPickupFromSlot(player, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);
        this.tile.closeInventory(playerIn);
    }
}
