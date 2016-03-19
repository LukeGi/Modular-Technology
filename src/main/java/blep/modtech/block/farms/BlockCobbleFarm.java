package blep.modtech.block.farms;

import blep.modtech.block.BlockMod;
import blep.modtech.tileentity.TileEntityCobbleFarm;
import blep.modtech.util.IModTechTileBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class BlockCobbleFarm extends BlockMod implements ITileEntityProvider, IModTechTileBlock
{
    public BlockCobbleFarm()
    {
        super(Material.rock);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityCobbleFarm();
    }

	@Override
	public Class<? extends TileEntity> getTileEntityClass() {
		return TileEntityCobbleFarm.class;
	}
}
