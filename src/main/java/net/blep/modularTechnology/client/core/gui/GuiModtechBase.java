package net.blep.modularTechnology.client.core.gui;

import net.blep.modularTechnology.common.core.ModularTechnology;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * @author TheEpicTekkit
 */
public abstract class GuiModtechBase extends GuiContainer
{
    protected ResourceLocation texture = new ResourceLocation(ModularTechnology.MOD_ID, "");
    protected Object holder;
    protected EntityPlayer user;

    public GuiModtechBase(Container container, Object holder, EntityPlayer user)
    {
        super(container);
        this.holder = holder;
        this.user = user;
    }

    public GuiModtechBase setTexture(String file)
    {
        this.texture = new ResourceLocation(ModularTechnology.MOD_ID, "textures/gui" + (file.startsWith("/") ? "" : "/") + file + (file.endsWith(".png") ? "" : ".png"));
        return this;
    }

    public GuiModtechBase setDimensions(int x, int y, int width, int height)
    {
        this.guiLeft = x;
        this.guiTop = y;
        this.xSize = width;
        this.ySize = height;
        return this;
    }

    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        super.drawGuiContainerForegroundLayer(x, y);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int centreX = (this.width - this.xSize) / 2;
        int centreY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(centreX, centreY, 0, 0, this.xSize, this.ySize);
    }
}
