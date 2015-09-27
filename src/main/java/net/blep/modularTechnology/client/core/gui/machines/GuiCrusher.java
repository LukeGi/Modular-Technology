package net.blep.modularTechnology.client.core.gui.machines;

import net.blep.modularTechnology.client.core.gui.GuiModtechBase;
import net.blep.modularTechnology.common.core.gui.machines.ContainerCrusher;
import net.blep.modularTechnology.common.core.util.MethodHelper;
import net.blep.modularTechnology.common.tech.blocks.tileentity.TileEntityCrusher;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.EnumChatFormatting;

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
        boolean energyDirection = te.getEnergyDirection();

        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(162, 88, 175, (8 * powerStatus) % 24, 8, 8);
        drawTexturedModalRect(43, 17, 175, 24, (int) MethodHelper.getValueScaled(machineProgress, 100, 34), 18);
        drawTexturedModalRect(25, 89, 175, energyDirection ? 42 : 48, 11, 6);

        mc.getTextureManager().bindTexture(overlay);
        drawTexturedModalRect(38, 84, 0, 36, (int) MethodHelper.getValueScaled(energyStored, maxEnergy, 100), 16);

        addTooltipToArea(162, 88, 170, 96, x, y, true, EnumChatFormatting.GREEN + "Power status", EnumChatFormatting.GRAY + (powerStatus == 0 ? "Loss" : powerStatus == 1 ? "Gain" : powerStatus == 2 ? "Stable" : EnumChatFormatting.RED + "Undefined"));
        addTooltipToArea(38, 84, 138, 100, x, y, false, energyStored + " / " + maxEnergy + " RF");
        addTooltipToArea(44, 18, 76, 34, x, y, false, machineProgress + "%");
    }
}
