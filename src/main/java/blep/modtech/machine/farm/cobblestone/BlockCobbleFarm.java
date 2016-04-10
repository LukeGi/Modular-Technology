package blep.modtech.machine.farm.cobblestone;

import blep.modtech.ModTech;
import blep.modtech.block.BlockMod;
import blep.modtech.machine.farm.GuiHandlerModTechFarm;
import blep.modtech.machine.farm.cobblestone.TileEntityCobbleFarm;
import blep.modtech.util.IModTechTileBlock;
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
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) return true;

        playerIn.openGui(ModTech.INSTANCE, GuiHandlerModTechFarm.GUIID_COBBLEFARM, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
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
