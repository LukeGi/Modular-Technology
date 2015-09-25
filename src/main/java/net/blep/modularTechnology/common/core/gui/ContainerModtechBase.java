package net.blep.modularTechnology.common.core.gui;

import net.blep.modularTechnology.client.core.gui.GuiModtechBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * @author TheEpicTekkit
 */
public abstract class ContainerModtechBase extends Container
{
    protected Object holder;
    protected EntityPlayer user;

    public ContainerModtechBase(Object holder, EntityPlayer user)
    {
        this.holder = holder;
        this.user = user;
    }
}
