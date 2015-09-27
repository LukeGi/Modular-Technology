package net.blep.modularTechnology.client.core.gui;

import net.blep.modularTechnology.common.core.ModularTechnology;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author TheEpicTekkit
 */
public abstract class GuiModtechBase extends GuiContainer
{
    protected ResourceLocation texture = new ResourceLocation(ModularTechnology.MOD_ID, "");
    protected ResourceLocation overlay = new ResourceLocation(ModularTechnology.MOD_ID, "textures/gui/overlay.png");

    protected IInventory holder;
    protected EntityPlayer user;

    public GuiModtechBase(Container container, IInventory holder, EntityPlayer user)
    {
        super(container);
        this.holder = holder;
        this.user = user;
        String name = holder.getInventoryName();
        setTexture(name.startsWith("container.") ? name.substring(10) : name);
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

        GL11.glPushMatrix();
        {
            String s = holder.hasCustomInventoryName() ? holder.getInventoryName() : I18n.format(holder.getInventoryName(), new Object[0]);
            this.fontRendererObj.drawString(s, 8, 4, 4210752);
            this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, 114, 4210752);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
        GL11.glPopMatrix();
    }

    public void addTooltipToArea(int minX, int minY, int maxX, int maxY, int mouseX, int mouseY, boolean lock, String... text)
    {
        int x = guiLeft + minX;
        int y = guiTop + minY;
        int X = guiLeft + maxX;
        int Y = guiTop + maxY;

        if (mouseX >= x && mouseX < X)
        {
            if (mouseY >= y && mouseY < Y)
            {
                int ttX = mouseX;
                int ttY = mouseY;

                if (lock)
                {
                    ttX = X;
                    ttY = Y;
                }

                drawHoveringText(Arrays.asList(text), ttX - guiLeft, ttY - guiTop, fontRendererObj);
            }
        }
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
