package blep.modtech.block.metal;

import blep.modtech.block.BlockMod;
import blep.modtech.util.EnumMetalProperty;
import blep.modtech.util.EnumMetals;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class BlockOre extends BlockMod
{
    public static final PropertyEnum<EnumMetals> TYPE = PropertyEnum.create("name", EnumMetals.class);

    public BlockOre()
    {
        super(Material.rock);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumMetals.IRON));
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(TYPE, EnumMetals.byMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(TYPE).getMeta();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, TYPE);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return getMetaFromState(state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        for (int i = 0; i < EnumMetals.values().length; i++)
            if (!EnumMetals.byMeta(i).hasProperty(EnumMetalProperty.VANILLA))
                list.add(new ItemStack(itemIn, 1, i));
    }
}
