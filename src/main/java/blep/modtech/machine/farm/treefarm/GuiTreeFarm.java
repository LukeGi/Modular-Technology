package blep.modtech.machine.farm.treefarm;

import blep.modtech.reference.ModInfo;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
@SideOnly(Side.CLIENT)
public class GuiTreeFarm extends GuiContainer
{
    // This is the resource location for the background image for the GUI
    private static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/guiTreeFarm.png");
    private TileEntityTreeFarm tile;

    public GuiTreeFarm(InventoryPlayer invPlayer, TileEntityTreeFarm tile)
    {
        super(new ContainerTreeFarm(invPlayer, tile));
        this.tile = tile;
        // Set the width and height of the gui.  Should match the size of the texture!
        xSize = 225;
        ySize = 168;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        int energystoredpercentage = (int) (52F * (1F - (float) (tile.getEnergyStored(EnumFacing.DOWN)) / (float) (tile.getMaxEnergyStored(EnumFacing.DOWN))));
        this.drawTexturedModalRect(guiLeft + 191, guiTop + 23 + energystoredpercentage, 225, 23 + energystoredpercentage, 16, 53 - energystoredpercentage);
//        LogHelper.info(energystoredpercentage + "\n" + tile.getEnergyStored(EnumFacing.DOWN));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.tile.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        if (this.isPointInRegion(192, 24, 15, 50, mouseX, mouseY))
        {
            List<String> textLines = new ArrayList<>();
            textLines.add(tile.getEnergyStored(EnumFacing.DOWN) + "/" + tile.getMaxEnergyStored(EnumFacing.DOWN));
            this.drawHoveringText(textLines, 208, 76);
        }
    }
}
