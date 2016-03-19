package blep.modtech.util;

import net.minecraft.tileentity.TileEntity;

public interface IModTechTileBlock {

	Class<? extends TileEntity> getTileEntityClass();
}
