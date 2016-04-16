package blep.modtech.machine.generator;

import blep.modtech.block.BlockMod;
import blep.modtech.core.IRegisterTileEntity;
import blep.modtech.util.LogHelper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public abstract class BlockGenerator extends BlockMod implements IRegisterTileEntity, ITileEntityProvider
{
    protected BlockGenerator()
    {
        super(Material.iron);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        openGui(worldIn, playerIn, pos);
        return true;
    }

    protected abstract void openGui(World world, EntityPlayer player, BlockPos pos);
}
