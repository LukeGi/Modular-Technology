package blep.modtech.machine;

import blep.modtech.machine.farm.cobblestone.ContainerCobbleFarm;
import blep.modtech.machine.farm.cobblestone.GuiCobbleFarm;
import blep.modtech.machine.farm.cobblestone.TileEntityCobbleFarm;
import blep.modtech.machine.farm.treefarm.ContainerTreeFarm;
import blep.modtech.machine.farm.treefarm.GuiTreeFarm;
import blep.modtech.machine.farm.treefarm.TileEntityTreeFarm;
import blep.modtech.machine.generator.lunar.ContainerGeneratorLunar;
import blep.modtech.machine.generator.lunar.GuiGeneratorLunar;
import blep.modtech.machine.generator.lunar.TileEntityGeneratorLunar;
import blep.modtech.machine.generator.solar.ContainerGeneratorSolar;
import blep.modtech.machine.generator.solar.GuiGeneratorSolar;
import blep.modtech.machine.generator.solar.TileEntityGeneratorSolar;
import blep.modtech.machine.generator.solarHybrid.ContainerGeneratorSolarLunarHybrid;
import blep.modtech.machine.generator.solarHybrid.TileEntityGeneratorSolarLunarHybrid;
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
    public static final int GUIID_LUNARGEN = 21;
    public static final int GUIID_SONARGEN = 22;

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
            case GUIID_LUNARGEN:
                if (tileEntity instanceof TileEntityGeneratorLunar)
                {
                    TileEntityGeneratorLunar tileEntityNew = (TileEntityGeneratorLunar) tileEntity;
                    return new ContainerGeneratorLunar(player.inventory, tileEntityNew);
                }
            case GUIID_SONARGEN:
                if (tileEntity instanceof TileEntityGeneratorSolarLunarHybrid)
                {
                    TileEntityGeneratorSolarLunarHybrid tileEntityNew = (TileEntityGeneratorSolarLunarHybrid) tileEntity;
                    return new ContainerGeneratorSolarLunarHybrid(player.inventory, tileEntityNew);
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
            case GUIID_LUNARGEN:
                if (tileEntity instanceof TileEntityGeneratorLunar)
                {
                    TileEntityGeneratorLunar tileEntityNew = (TileEntityGeneratorLunar) tileEntity;
                    return new GuiGeneratorLunar(player.inventory, tileEntityNew);
                }
            case GUIID_SONARGEN:
                if (tileEntity instanceof TileEntityGeneratorSolarLunarHybrid)
                {
                    TileEntityGeneratorSolarLunarHybrid tileEntityNew = (TileEntityGeneratorSolarLunarHybrid) tileEntity;
                    return new GuiGeneratorSolarLunarHybrid(player.inventory, tileEntityNew);
                }
        }
        return null;
    }
}
