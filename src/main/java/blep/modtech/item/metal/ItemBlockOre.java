package blep.modtech.item.metal;

import blep.modtech.util.EnumMetals;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ItemBlockOre extends ItemBlock
{
    public ItemBlockOre(Block block)
    {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int metaIn)
    {
        return metaIn;
    }

    @Override
    public String getUnlocalizedName(ItemStack stackIn)
    {
        EnumMetals type = EnumMetals.byMeta(stackIn.getItemDamage());
        return super.getUnlocalizedName() + "." + type.toString();
    }
}
