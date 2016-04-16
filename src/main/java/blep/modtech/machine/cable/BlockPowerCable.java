package blep.modtech.machine.cable;

import blep.modtech.block.BlockMod;
import blep.modtech.core.IRegisterTileEntity;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class BlockPowerCable extends BlockMod implements ITileEntityProvider, IRegisterTileEntity
{
    public BlockPowerCable()
    {
        super(Material.web);
    }

    @Override
    public void registerTileEntity()
    {
        GameRegistry.registerTileEntity(TileEntityPowerCable.class, getUnlocalizedName());
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityPowerCable(10000);
    }
}
