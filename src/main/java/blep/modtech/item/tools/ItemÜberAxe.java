package blep.modtech.item.tools;

import blep.modtech.item.ItemMod;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ItemÜberAxe extends ItemMod
{
    public ItemÜberAxe()
    {
        setMaxStackSize(1);
        setMaxDamage(5120);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
    {
        return true;
    }
}
