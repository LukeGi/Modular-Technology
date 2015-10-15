package com.blep.modularTechnology.tech.common.blocks.tileentity;

import com.blep.modularTechnology.core.common.blocks.tileentity.TileEntityMachineBase;
import com.blep.modularTechnology.core.common.energy.EnumEnergyDirection;
import javafx.util.Pair;
import com.blep.modularTechnology.core.common.energy.EnergyNetwork;
import com.blep.modularTechnology.core.common.energy.EnumEnergyirection;
import com.blep.modularTechnology.core.common.energy.EnumPowerStatus;
import com.blep.modularTechnology.core.common.energy.IMachine;
import net.blep.modularTechnology.common.tech.recipe.RecipesCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

/**
 * @author TheEpicTekkit
 */
public class TileEntityCrusher extends TileEntityMachineBase implements IMachine
{
    public ItemStack[] slots = new ItemStack[5];
    public boolean[] canShiftClick = {true, true, false, false, false};

    private double energyCapacity = 20000;
    private double energyStored = energyCapacity;
    private double energyRecieveRate = 200;
    private double previousEnergy = energyStored;
    private double energyChange = 0;
    private int machineProgress = 0;
    private int ticksToProcess = 60;
    private double energyPerOperation = ticksToProcess * 2.0D;

    private EnumEnergyDirection energyDirection = EnumEnergyDirection.IN;

    public void updateEntity()
    {
        super.updateEntity();

        processInput();
    }

    public void processInput()
    {
        energyChange = energyStored - previousEnergy;
        previousEnergy = energyStored;
        if (canProcessInput())
        {
            if (energyStored >= energyPerOperation && machineProgress < ticksToProcess)
            {
                machineProgress++;
                energyStored -= (energyPerOperation / (double) ticksToProcess);
            } else
            {
                machineProgress = 0;
                List<Pair<ItemStack, Float>> outputs = RecipesCrusher.getResultsFor(slots[0]);

                if (slots[0].stackSize-- <= 0) slots[0] = null;

                ItemStack out = null;
                ItemStack byp1 = null;
                ItemStack byp2 = null;

                if (outputs.size() >= 1) out = outputs.get(0).getKey();
                if (outputs.size() >= 2) byp1 = outputs.get(1).getKey();
                if (outputs.size() >= 3) byp2 = outputs.get(2).getKey();

                if (out != null && out.stackSize > 0 && worldObj.rand.nextFloat() <= outputs.get(0).getValue())
                {
                    System.out.println(out.stackSize + " items output");
                    if (getStackInSlot(2) == null || getStackInSlot(2).stackSize <= 0) slots[2] = out.copy();
                    else if (getStackInSlot(2).getItem() == out.getItem()) slots[2].stackSize += out.stackSize;
                }

                if (byp1 != null && byp1.stackSize > 0 && worldObj.rand.nextFloat() <= outputs.get(1).getValue())
                {
                    if (getStackInSlot(3) == null || getStackInSlot(3).stackSize <= 0) slots[3] = byp1.copy();
                    else if (getStackInSlot(3).getItem() == byp1.getItem()) slots[3].stackSize += byp1.stackSize;
                }

                if (byp2 != null && byp2.stackSize > 0 && worldObj.rand.nextFloat() <= outputs.get(2).getValue())
                {
                    if (getStackInSlot(4) == null || getStackInSlot(4).stackSize <= 0) slots[4] = byp2.copy();
                    else if (getStackInSlot(4).getItem() == byp2.getItem()) slots[4].stackSize += byp2.stackSize;
                }
            }
        } else
            machineProgress = 0;
    }

    public boolean canProcessInput()
    {
        if (getEnergyStored() < getEnergyPerOperation()) return false;
        if (getStackInSlot(0) == null || getStackInSlot(0).stackSize <= 0) return false;
        ItemStack input = getStackInSlot(0);

        List<Pair<ItemStack, Float>> outputs = RecipesCrusher.getResultsFor(input);

        if (outputs == null || outputs.size() <= 0) return false;

        if (outputs.size() >= 1 && (getStackInSlot(2) == null || (getStackInSlot(2).isItemEqual(outputs.get(0).getKey()) && getStackInSlot(2).stackSize + outputs.get(0).getKey().stackSize < getStackInSlot(2).getMaxStackSize() && getStackInSlot(2).stackSize + outputs.get(0).getKey().stackSize <= getInventoryStackLimit())))
        {
            if (outputs.size() >= 2 && (getStackInSlot(3) == null || (getStackInSlot(3).isItemEqual(outputs.get(1).getKey()) && getStackInSlot(3).stackSize + outputs.get(1).getKey().stackSize < getStackInSlot(3).getMaxStackSize() && getStackInSlot(3).stackSize + outputs.get(1).getKey().stackSize <= getInventoryStackLimit())))
            {
                if (outputs.size() >= 3 && (getStackInSlot(4) == null || (getStackInSlot(4).isItemEqual(outputs.get(2).getKey()) && getStackInSlot(4).stackSize + outputs.get(2).getKey().stackSize < getStackInSlot(4).getMaxStackSize() && getStackInSlot(4).stackSize + outputs.get(2).getKey().stackSize <= getInventoryStackLimit())))
                {
                    return true;
                }
                return true;
            }
            return true;
        }
        return false;
    }

    public int[] getAccessibleSlotsFromSide(int side)
    {
        if (side == 1)
            return new int[]{0};
        else if (side == 0)
            return new int[]{2, 3, 4};
        else if (side == 4 || side == 5)
            return new int[]{1};
        else
            return new int[]{0};
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side)
    {
        if (slot == 0)
            return RecipesCrusher.hasRecipeFor(stack);
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack item, int side)
    {
        if (slot == 2 || slot == 3 || slot == 4)
            return true;
        return false;
    }

    @Override
    public int getSizeInventory()
    {
        return slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return slots[slot];
    }

    public ItemStack decrStackSize(int slot, int amount)
    {
        if (slots[slot] != null)
        {
            ItemStack stack;
            if (slots[slot].stackSize <= amount)
            {
                stack = slots[slot];
                slots[slot] = null;
                return stack;
            } else
            {
                stack = slots[slot].splitStack(amount);
                if (slots[slot].stackSize == 0)
                {
                    slots[slot] = null;
                }
                return stack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        slots[slot] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return "container.crusher";
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return StatCollector.canTranslate(getInventoryName());
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void markDirty()
    {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        if (slot == 0)
            return RecipesCrusher.hasRecipeFor(stack);
        else
            return canShiftClick[slot]; //TODO: check if stack is an ore or a battery
    }

    @Override
    public double getEnergyCapacity()
    {
        return energyCapacity;
    }

    @Override
    public double getEnergyStored()
    {
        return energyStored;
    }

    @Override
    public double getEnergyRecieveRate()
    {
        return energyRecieveRate;
    }

    @Override
    public double requestEnergy(double amount)
    {
        return 0;
    }

    @Override
    public EnergyNetwork getEnergyNetwork()
    {
        return null;
    }

    @Override
    public EnumPowerStatus getPowerStatus()
    {
        if (energyChange < 0) return EnumPowerStatus.LOSS;
        else if (energyChange > 0) return EnumPowerStatus.GAIN;
        else return EnumPowerStatus.NEUTRAL;
    }

    @Override
    public int getMachineProgress()
    {
        return machineProgress;
    }

    public int getTicksToProcess()
    {
        return ticksToProcess;
    }

    @Override
    public EnumEnergyDirection getEnergyDirection()
    {
        if (getPowerStatus().equals(EnumPowerStatus.LOSS)) return EnumEnergyDirection.OUT;
        else if (getPowerStatus().equals(EnumPowerStatus.GAIN)) return EnumEnergyDirection.IN;
        return EnumEnergyDirection.INVALID;
    }

    @Override
    public double getEnergyPerOperation()
    {
        return energyPerOperation;
    }

    public double getEnergyChange()
    {
        return energyChange;
    }
}