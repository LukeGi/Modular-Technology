package blep.modtech.machine.farm.cobblestone;

import blep.modtech.core.GuiBase;
import blep.modtech.reference.ModInfo;
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
public class GuiCobbleFarm extends GuiBase<TileEntityCobbleFarm>
{
    private static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/guiCobbleFarm.png");

    public GuiCobbleFarm(InventoryPlayer inventory, TileEntityCobbleFarm tile)
    {
        super(inventory, tile, 176, 250, "CobbleFarm", 8, 8, new ContainerCobbleFarm(inventory, tile));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        int energystoredpercentage = (int) (142F * (1F - (float) (tile.getEnergyStored(EnumFacing.DOWN)) / (float) (tile.getMaxEnergyStored(EnumFacing.DOWN))));
        this.drawTexturedModalRect(guiLeft + 156, guiTop + 19 + energystoredpercentage, 177, 19 + energystoredpercentage, 16, 142 - energystoredpercentage);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        if (this.isPointInRegion(156, 19, 117, 142, mouseX, mouseY))
        {
            List<String> textLines = new ArrayList<>();
            textLines.add(tile.getEnergyStored(EnumFacing.DOWN) + "/" + tile.getMaxEnergyStored(EnumFacing.DOWN));
            this.drawHoveringText(textLines, mouseX - guiLeft, mouseY - guiTop);
        }
    }
}
