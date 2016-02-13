package net.blep.modtech.client.gui;

import net.blep.modtech.core.reference.ModInfo;
import net.blep.modtech.gui.container.ContainerMachineFurnace;
import net.blep.modtech.gui.container.ContainerModtechBase;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Kelan on 07/02/2016.
 */
public class GuiMachineFurnace extends GuiModtechBase
{
    public GuiMachineFurnace(ContainerModtechBase container)
    {
        super(container, new ResourceLocation(ModInfo.MOD_ID, ""));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
    {

    }
}
