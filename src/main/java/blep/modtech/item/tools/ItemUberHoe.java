package blep.modtech.item.tools;

import blep.modtech.item.ItemMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ItemUberHoe extends ItemMod
{
    public ItemUberHoe()
    {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(1);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        boolean flagDidAnything = false;
        if (!worldIn.isRemote)
        {
            if (hoeIsValid(worldIn, pos))
            {
                flagDidAnything = true;
                for (int i = -4; i <= 4; i++)
                    for (int j = -4; j <= 4; j++)
                    {
                        if (pos.add(i, 0, j).equals(pos))
                            worldIn.setBlockState(pos, Blocks.water.getDefaultState());
                        else
                            worldIn.setBlockState(pos.add(i, 0, j), Blocks.farmland.getDefaultState(), 3);
                    }
                stack.damageItem(1, playerIn);
            }
        }
        return flagDidAnything ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
    }

    private boolean hoeIsValid(World world, BlockPos pos)
    {
        boolean flagIsValid = true;
        for (int i = -4; i <= 4; i++)
            for (int j = -4; j <= 4; j++)
            {
                Block scanned = world.getBlockState(pos.add(i, 0, j)).getBlock();
                flagIsValid = flagIsValid && (scanned instanceof BlockGrass || scanned instanceof BlockDirt);
                boolean isReplaceable = world.getBlockState(pos.add(i, 1, j)).getBlock().isReplaceable(world, pos);
                flagIsValid = flagIsValid && isReplaceable;
                world.setBlockToAir(pos.add(i, 1, j));
            }
        return flagIsValid;
    }
}
