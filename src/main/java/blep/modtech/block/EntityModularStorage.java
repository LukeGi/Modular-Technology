package blep.modtech.block;

import blep.modtech.core.IRecipeProvider;
import blep.modtech.core.IRegisterTileEntity;
import blep.modtech.tileentity.TileEntityModularStorage;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class EntityModularStorage extends BlockMod implements ITileEntityProvider, IRegisterTileEntity, IRecipeProvider
{
    public EntityModularStorage()
    {
        super(Material.wood);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityModularStorage();
    }

    @Override
    public void registerTileEntity()
    {
        GameRegistry.registerTileEntity(TileEntityModularStorage.class, this.getUnlocalizedName());
    }

    @Override
    public void registerRecipe()
    {
        GameRegistry.addShapedRecipe(
                new ItemStack(ModtechBlocks.MODULAR_STORAGE.block, 6),
                "HIH",
                "ICH",
                "HIH",
                'H', new ItemStack(Blocks.hopper),
                'I', new ItemStack(Blocks.iron_block),
                'C', new ItemStack(Blocks.chest)
        );
    }
}
