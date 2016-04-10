package blep.modtech.machine.generator;

import blep.modtech.block.BlockMod;
import blep.modtech.util.IModTechTileBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class BlockGenerator extends BlockMod implements IModTechTileBlock, ITileEntityProvider
{
    public BlockGenerator()
    {
        super(Material.iron);
    }

    @Override
    public Class<? extends TileEntity> getTileEntityClass()
    {
        return TileEntityGeneratorBase.class;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return null;
    }
}
