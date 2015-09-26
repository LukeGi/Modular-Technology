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
        super(new ContainerCrusher(holder, user), holder, user);
    }
}
