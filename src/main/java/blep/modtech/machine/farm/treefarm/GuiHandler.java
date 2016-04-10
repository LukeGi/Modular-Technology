package blep.modtech.machine.farm.treefarm;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class GuiHandler implements IGuiHandler
{
    public static final int GUIID_TREEFARM = 0;

    public static GuiHandler INSTANCE = new GuiHandler();

    public static GuiHandler getInstance()
    {
        return INSTANCE;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case GUIID_TREEFARM:
                BlockPos xyz = new BlockPos(x, y, z);
                TileEntity tileEntity = world.getTileEntity(xyz);
                if (tileEntity instanceof TileEntityTreeFarm)
                {
                    TileEntityTreeFarm tileEntityInventoryBasic = (TileEntityTreeFarm) tileEntity;
                    return new ContainerTreeFarm(player.inventory, tileEntityInventoryBasic);
                }
                return null;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case GUIID_TREEFARM:
                BlockPos xyz = new BlockPos(x, y, z);
                TileEntity tileEntity = world.getTileEntity(xyz);
                if (tileEntity instanceof TileEntityTreeFarm)
                {
                    TileEntityTreeFarm tileEntityInventoryBasic = (TileEntityTreeFarm) tileEntity;
                    return new GuiTreeFarm(player.inventory, tileEntityInventoryBasic);
                }
                return null;
        }
        return null;
    }
}
