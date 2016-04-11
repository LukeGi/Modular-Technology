package blep.modtech.machine.farm.cobblestone;

import blep.modtech.ModTech;
import blep.modtech.block.BlockMod;
import blep.modtech.block.ModtechBlocks;
import blep.modtech.core.IRecipeProvider;
import blep.modtech.core.IRegisterTileEntity;
import blep.modtech.machine.farm.GuiHandlerModTechFarm;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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
public class EntityCobbleFarm extends BlockMod implements ITileEntityProvider, IRegisterTileEntity, IRecipeProvider
{
    public EntityCobbleFarm()
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
    public void registerRecipe()
    {
        GameRegistry.addShapedRecipe(
                new ItemStack(ModtechBlocks.COBBLE_GENERATOR.block, 1),
                "RLR",
                "PMP",
                "RWR",
                'R', Items.redstone,
                'L', Items.lava_bucket,
                'P', Items.iron_pickaxe,
                'W', Items.water_bucket
        );
    }

    @Override
    public void registerTileEntity()
    {
        GameRegistry.registerTileEntity(TileEntityCobbleFarm.class, this.getUnlocalizedName());
    }
}
