package blep.modtech.item.tools;

import blep.modtech.item.ItemMod;
import blep.modtech.multiblock.MuPCobbleGen;
import blep.modtech.multiblock.MultiblockPattern;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ItemMultiblockBuilder extends ItemMod
{
    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        MultiblockPattern.treefarm.create(worldIn, pos.add(facing.getDirectionVec()));
        return EnumActionResult.SUCCESS;
    }
}
