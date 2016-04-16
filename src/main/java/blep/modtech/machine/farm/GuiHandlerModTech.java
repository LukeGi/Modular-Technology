package blep.modtech.machine.farm;

import blep.modtech.machine.farm.cobblestone.ContainerCobbleFarm;
import blep.modtech.machine.farm.cobblestone.GuiCobbleFarm;
import blep.modtech.machine.farm.cobblestone.TileEntityCobbleFarm;
import blep.modtech.machine.farm.treefarm.ContainerTreeFarm;
import blep.modtech.machine.farm.treefarm.GuiTreeFarm;
import blep.modtech.machine.farm.treefarm.TileEntityTreeFarm;
import blep.modtech.machine.generator.solar.ContainerGeneratorSolar;
import blep.modtech.machine.generator.solar.GuiGeneratorSolar;
import blep.modtech.machine.generator.solar.TileEntityGeneratorSolar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class GuiHandlerModTech implements IGuiHandler
{
    // FARMS RESERVE 0 - 19
    public static final int GUIID_TREEFARM = 0;
    public static final int GUIID_COBBLEFARM = 1;
    // GENERATORS RESERVE 20 - 39
    public static final int GUIID_SOLARGEN = 20;

    public static GuiHandlerModTech INSTANCE = new GuiHandlerModTech();

    public static GuiHandlerModTech getInstance()
    {
        return INSTANCE;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos xyz = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(xyz);
        switch (ID)
        {
            case GUIID_TREEFARM:
                if (tileEntity instanceof TileEntityTreeFarm)
                {
                    TileEntityTreeFarm tileEntityNew = (TileEntityTreeFarm) tileEntity;
                    return new ContainerTreeFarm(player.inventory, tileEntityNew);
                }
                return null;
            case GUIID_COBBLEFARM:
                if (tileEntity instanceof TileEntityCobbleFarm)
                {
                    TileEntityCobbleFarm tileEntityNew = (TileEntityCobbleFarm) tileEntity;
                    return new ContainerCobbleFarm(player.inventory, tileEntityNew);
                }
            case GUIID_SOLARGEN:
                if (tileEntity instanceof TileEntityGeneratorSolar)
                {
                    TileEntityGeneratorSolar tileEntityNew = (TileEntityGeneratorSolar) tileEntity;
                    return new ContainerGeneratorSolar(player.inventory, tileEntityNew);
                }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos xyz = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(xyz);
        switch (ID)
        {
            case GUIID_TREEFARM:
                if (tileEntity instanceof TileEntityTreeFarm)
                {
                    TileEntityTreeFarm tileEntityNew = (TileEntityTreeFarm) tileEntity;
                    return new GuiTreeFarm(player.inventory, tileEntityNew);
                }
            case GUIID_COBBLEFARM:
                if (tileEntity instanceof TileEntityCobbleFarm)
                {
                    TileEntityCobbleFarm tileEntityNew = (TileEntityCobbleFarm) tileEntity;
                    return new GuiCobbleFarm(player.inventory, tileEntityNew);
                }
            case GUIID_SOLARGEN:
                if (tileEntity instanceof TileEntityGeneratorSolar)
                {
                    TileEntityGeneratorSolar tileEntityNew = (TileEntityGeneratorSolar) tileEntity;
                    return new GuiGeneratorSolar(player.inventory, tileEntityNew);
                }
        }
        return null;
    }
}
