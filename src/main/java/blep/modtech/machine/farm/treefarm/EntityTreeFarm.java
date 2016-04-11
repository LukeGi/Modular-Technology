package blep.modtech.machine.farm.treefarm;

import blep.modtech.ModTech;
import blep.modtech.block.BlockMod;
import blep.modtech.machine.farm.GuiHandlerModTechFarm;
import blep.modtech.core.IRegisterTileEntity;
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
public class EntityTreeFarm extends BlockMod implements IRegisterTileEntity, ITileEntityProvider
{
    public EntityTreeFarm()
    {
        super(Material.iron);
        this.setHardness(10);
        this.setResistance(10);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) return true;

        playerIn.openGui(ModTech.INSTANCE, GuiHandlerModTechFarm.GUIID_TREEFARM, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityTreeFarm(4);
    }

    @Override
    public void registerTileEntity()
    {
        GameRegistry.registerTileEntity(TileEntityTreeFarm.class, this.getUnlocalizedName());
    }
}
