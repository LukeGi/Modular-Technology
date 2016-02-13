package net.blep.modtech.client.gui;

import net.blep.modtech.gui.container.ContainerModtechBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Kelan on 07/02/2016.
 */
public abstract class GuiModtechBase extends GuiContainer
{
    protected ResourceLocation backgroundTexture;

    public GuiModtechBase(ContainerModtechBase container, ResourceLocation backgroundTexture)
    {
        super(container);
        this.backgroundTexture = backgroundTexture;
    }

    @Override
    protected abstract void drawGuiContainerBackgroundLayer(float f, int x, int y);
}
