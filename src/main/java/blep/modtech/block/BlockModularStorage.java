package blep.modtech.block;

import blep.modtech.tileentity.TileEntityModularStorage;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class BlockModularStorage extends BlockMod implements ITileEntityProvider
{
    public BlockModularStorage()
    {
        super(Material.wood);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityModularStorage();
    }
}
