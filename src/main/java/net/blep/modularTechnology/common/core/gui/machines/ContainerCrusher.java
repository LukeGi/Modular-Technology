package net.blep.modularTechnology.common.core.gui.machines;

import net.blep.modularTechnology.client.core.gui.GuiModtechBase;
import net.blep.modularTechnology.common.core.gui.ContainerModtechBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author TheEpicTekkit
 */
public class ContainerCrusher extends ContainerModtechBase
{
    public ContainerCrusher(Object holder, EntityPlayer user)
    {
        super(holder, user);
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return true;
    }
}
