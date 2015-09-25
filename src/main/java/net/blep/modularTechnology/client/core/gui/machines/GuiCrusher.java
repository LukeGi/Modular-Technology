package net.blep.modularTechnology.client.core.gui.machines;

import net.blep.modularTechnology.client.core.gui.GuiModtechBase;
import net.blep.modularTechnology.common.core.gui.machines.ContainerCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * @author TheEpicTekkit
 */
public class GuiCrusher extends GuiModtechBase
{
    public GuiCrusher(Object holder, EntityPlayer user)
    {
        super(new ContainerCrusher(holder, user));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {

    }
}
