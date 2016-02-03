package modulartechnology.inventory;

import com.blep.modularTechnology.core.common.util.MethodHelper;
import com.blep.modularTechnology.core.common.util.ShiftClickTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.List;

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
                this.addSlotToContainer(new Slot(user.inventory, j + i * 9 + 9, invX + xSpace + j * slotWidth, invY + ySpace + i * slotHeight));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(user.inventory, i, hotbarX + xSpace + i * slotWidth, hotbarY));
        }
    }

    public abstract ShiftClickTarget getShiftClickTarget(ItemStack stack, EntityPlayer player);

    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        return handleShiftClick(this, player, slotIndex);
    }

    /**
     * An implementation of transferStackInSlot(EntityPlayer, int) that should work universally for any container.
     *
     * @param container The Container that the player is in
     * @param player The player performing the shift-click
     * @param slotIndex The slot being shift-clicked
     * @return null if no transfer is possible, else the ItemStack that was transferred
     */
    public static ItemStack handleShiftClick(Container container, EntityPlayer player, int slotIndex)
    {
        List<Slot> slots = container.inventorySlots;
        if (slots == null || slots.size() <= 0)
            return null;

        Slot slot = slots.get(slotIndex);
        ItemStack inputStack = slot.getStack();

        if (inputStack == null || inputStack.stackSize <= 0)
            return null;

        boolean isPlayer = slot.inventory == player.inventory;

        ItemStack copy = inputStack.copy();

        if (isPlayer)
        {
            if (container instanceof ContainerModtechBase)
            {
                ShiftClickTarget target = ((ContainerModtechBase) container).getShiftClickTarget(inputStack, player);
                if (!target.isStandard())
                {
                    if (target.isNone() || !mergeToTarget(player.inventory, slot, slots, target))
                        return null;
                    else
                        return copy;
                }
            }
            if (!mergeStack(player.inventory, false, slot, slots, false))
                return null;
            else
                return copy;
        } else
        {
            if (!mergeStack(player.inventory, true, slot, slots, slot.isItemValid(inputStack)))
                return null;
            else
                return copy;
        }
    }

    /**
     * This method is the same as mergeStack, but it uses a ShiftClickTarget
     *
     * @param playerInv The player's inventory
     * @param sourceSlot The slot the itemstack being merged came from
     * @param slots A list of slots that this itemstack should attempt to merge with
     * @param target The shiftClickTarget to use
     * @return true if a merge is possible
     */
    private static boolean mergeToTarget(InventoryPlayer playerInv, Slot sourceSlot, List<Slot> slots, ShiftClickTarget target)
    {
        ItemStack sourceStack = sourceSlot.getStack();
        int originalSize = sourceStack.stackSize;

        target.reset();
        while (sourceStack.stackSize > 0 && target.hasNext())
        {
            Slot targetSlot = slots.get(target.next());
            if (targetSlot.inventory != playerInv)
            {
                ItemStack targetStack = targetSlot.getStack();
                if (MethodHelper.areItemStacksEqual(targetStack, sourceStack))
                {
                    int targetMax = Math.min(targetSlot.getSlotStackLimit(), targetStack.getMaxStackSize());
                    int toTransfer = Math.min(sourceStack.stackSize, targetMax - targetStack.stackSize);

                    if (toTransfer > 0)
                    {
                        targetStack.stackSize += toTransfer;
                        sourceStack.stackSize -= toTransfer;
                        targetSlot.onSlotChanged();
                    }
                }
            }
        }
        if (sourceStack.stackSize == 0)
        {
            sourceSlot.putStack(null);
            return true;
        }

        target.reset();
        while (target.hasNext())
        {
            Slot targetSlot = slots.get(target.next());

            if (targetSlot.inventory != playerInv && !targetSlot.getHasStack() && targetSlot.isItemValid(sourceStack))
            {
                targetSlot.putStack(sourceStack);
                sourceSlot.putStack(null);
                return true;
            }
        }
        if (originalSize != sourceStack.stackSize)
        {
            sourceSlot.onSlotChanged();
            return true;
        } else
            return false;
    }

    /**
     * Merges the stacks in the slots provided.
     *
     * @param playerInv The player's inventory
     * @param mergeIntoPlayer Should the player's inventory be considered when merging?
     * @param sourceSlot The slot that the itemstack being merged came from.
     * @param slots The slots that the itemstack should attempt to merge with.
     * @param reverse Should the iteration of slots go from end to beginning?
     * @return true if a target was found
     */
    private static boolean mergeStack(InventoryPlayer playerInv, boolean mergeIntoPlayer, Slot sourceSlot, List<Slot> slots, boolean reverse)
    {
        ItemStack sourceStack = sourceSlot.getStack();

        int originalSize = sourceStack.stackSize;
        int len = slots.size();
        int idx;

        if (sourceStack.isStackable())
        {
            idx = reverse ? len - 1 : 0;

            while (sourceStack.stackSize > 0 && (reverse ? idx >= 0 : idx < len))
            {
                Slot targetSlot = slots.get(idx);
                if ((targetSlot.inventory == playerInv) == mergeIntoPlayer)
                {
                    ItemStack target = targetSlot.getStack();
                    if (MethodHelper.areItemStacksEqual(sourceStack, target))
                    {
                        int targetMax = Math.min(targetSlot.getSlotStackLimit(), target.getMaxStackSize());
                        int toTransfer = Math.min(sourceStack.stackSize, targetMax - target.stackSize);

                        if (toTransfer > 0)
                        {
                            target.stackSize += toTransfer;
                            sourceStack.stackSize -= toTransfer;
                            targetSlot.onSlotChanged();
                        }
                    }
                }

                if (reverse)
                    idx--;
                else
                    idx++;
            }
            if (sourceStack.stackSize == 0)
            {
                sourceSlot.putStack(null);
                return true;
            }
        }

        idx = reverse ? len - 1 : 0;

        while (reverse ? idx >= 0 : idx < len)
        {
            Slot targetSlot = slots.get(idx);
            if ((targetSlot.inventory == playerInv) == mergeIntoPlayer && !targetSlot.getHasStack() && targetSlot.isItemValid(sourceStack))
            {
                targetSlot.putStack(sourceStack);
                sourceSlot.putStack(null);
                return true;
            }

            if (reverse)
                idx--;
            else
                idx++;
        }

        if (sourceStack.stackSize != originalSize)
        {
            sourceSlot.onSlotChanged();
            return true;
        }
        return false;
    }
}
