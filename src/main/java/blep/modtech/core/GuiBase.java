package blep.modtech.core;

import blep.modtech.reference.ModInfo;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
@SideOnly(Side.CLIENT)
public class GuiBase<T extends TileEntityBaseGui> extends GuiContainer
{
    public T tile;
    public ResourceLocation texture;

    protected GuiBase(T tile, int xSize, int ySize, String fileName, int rows, int columns, ContainerBase<T> container)
    {
        super(container);
        this.xSize = xSize;
        this.ySize = ySize;
        this.tile = tile;
        texture = new ResourceLocation(ModInfo.MOD_ID, String.format("textures/gui/gui%s.png", fileName));
    }

    public static <T extends TileEntityBaseGui> GuiBase<T> create(InventoryPlayer inventory, T tile, int xSize, int ySize, String fileName, int rows, int columns, ContainerBase<T> container)
    {
        return new GuiBase<T>(tile, xSize, ySize, fileName, rows, columns, container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(texture);
        int i = (width - xSize) / 2;
        int j = (height - ySize) / 2;
        drawTexturedModalRect(i, j, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = tile.getDisplayName().getUnformattedText();
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
    }
}
