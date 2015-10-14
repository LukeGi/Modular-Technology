package com.blep.modularTechnology.tech.client.gui;



import com.blep.modularTechnology.core.common.energy.EnumEnergyDirection;
import com.blep.modularTechnology.core.common.util.MethodHelper;
import com.blep.modularTechnology.core.client.inventory.GuiModtechBase;
import com.blep.modularTechnology.tech.common.inventory.ContainerCrusher;
import com.blep.modularTechnology.tech.common.blocks.tileentity.TileEntityCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.EnumChatFormatting;

import java.text.DecimalFormat;

/**
 * @author TheEpicTekkit
 */
public class GuiCrusher extends GuiModtechBase
{
    public GuiCrusher(IInventory holder, EntityPlayer user)
    {
        super(new ContainerCrusher(holder, user), holder, user);
        setTexture("crusher");
        setDimensions(0, 0, 175, 207);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        super.drawGuiContainerForegroundLayer(x, y);

        TileEntityCrusher te = (TileEntityCrusher) holder;

        int powerStatus = te.getPowerStatus().ordinal();
        int machineProgress = te.getMachineProgress();
        double energyStored = te.getEnergyStored();
        double maxEnergy = te.getEnergyCapacity();
        EnumEnergyDirection energyDirection = te.getEnergyDirection();

        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(162, 88, 175, (8 * powerStatus) % 24, 8, 8);
        drawTexturedModalRect(43, 17, 175, 24, (int) MethodHelper.getValueScaled(machineProgress, te.getTicksToProcess(), 34), 18);
        drawTexturedModalRect(25, 89, 175, energyDirection.equals(EnumEnergyDirection.IN) ? 42 : 48, 11, 6);

        mc.getTextureManager().bindTexture(overlay);
        drawTexturedModalRect(38, 84, 0, 36, (int) MethodHelper.getValueScaled(energyStored, maxEnergy, 100), 16);

        DecimalFormat df = new DecimalFormat("000");
        DecimalFormat df1 = new DecimalFormat("#,###");
        DecimalFormat df2 = new DecimalFormat("#.##");
        String energyChange = (te.getEnergyChange() > 0 ? "+" : "") + df2.format(te.getEnergyChange()) + " RF/t";

        addTooltipToArea(162, 88, 170, 96, x, y, true, EnumChatFormatting.GOLD + "Power status", (powerStatus == 0 ? EnumChatFormatting.RED + "Loss" : powerStatus == 1 ? EnumChatFormatting.GREEN + "Gain" : powerStatus == 2 ? EnumChatFormatting.BLUE + "Stable" : EnumChatFormatting.DARK_RED + "Undefined") + ": " + energyChange);
        addTooltipToArea(38, 84, 138, 100, x, y, false, EnumChatFormatting.GOLD + "Energy stored", EnumChatFormatting.BLUE + df1.format(energyStored) + " / " + df1.format(maxEnergy) + " RF");
        float val = (((float) machineProgress / (float) te.getTicksToProcess()) * 100);

        addTooltipToArea(44, 18, 76, 34, x, y, false, EnumChatFormatting.GOLD + "Progress", EnumChatFormatting.BLUE + df.format(val) + "%");
    }
}
