package blep.modtech.machine.farm;

import blep.modtech.machine.farm.cobblestone.ContainerCobbleFarm;
import blep.modtech.machine.farm.cobblestone.GuiCobbleFarm;
import blep.modtech.machine.farm.cobblestone.TileEntityCobbleFarm;
import blep.modtech.machine.farm.treefarm.ContainerTreeFarm;
import blep.modtech.machine.farm.treefarm.GuiTreeFarm;
import blep.modtech.machine.farm.treefarm.TileEntityTreeFarm;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class GuiHandlerModTechFarm implements IGuiHandler
{
    public static final int GUIID_TREEFARM = 0;
    public static final int GUIID_COBBLEFARM = 1;

    public static GuiHandlerModTechFarm INSTANCE = new GuiHandlerModTechFarm();

    public static GuiHandlerModTechFarm getInstance()
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
                    TileEntityTreeFarm tileEntityTreeFarm = (TileEntityTreeFarm) tileEntity;
                    return new ContainerTreeFarm(player.inventory, tileEntityTreeFarm);
                }
                return null;
            case GUIID_COBBLEFARM:
                if (tileEntity instanceof TileEntityCobbleFarm)
                {
                    TileEntityCobbleFarm tileEntityCobbleFarm = (TileEntityCobbleFarm) tileEntity;
                    return new ContainerCobbleFarm(player.inventory, tileEntityCobbleFarm);
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
                    TileEntityTreeFarm tileEntityInventoryBasic = (TileEntityTreeFarm) tileEntity;
                    return new GuiTreeFarm(player.inventory, tileEntityInventoryBasic);
                }
            case GUIID_COBBLEFARM:
                if (tileEntity instanceof TileEntityCobbleFarm)
                {
                    TileEntityCobbleFarm tileEntityCobbleFarm = (TileEntityCobbleFarm) tileEntity;
                    return new GuiCobbleFarm(player.inventory, tileEntityCobbleFarm);
                }
        }
        return null;
    }
}
