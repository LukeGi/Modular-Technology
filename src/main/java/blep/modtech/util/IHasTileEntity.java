package blep.modtech.util;

import net.minecraft.tileentity.TileEntity;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public interface IHasTileEntity
{
    Class<? extends TileEntity> getTileClass();
}
