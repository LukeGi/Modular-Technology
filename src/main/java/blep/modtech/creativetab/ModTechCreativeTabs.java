package blep.modtech.creativetab;

import java.util.List;

import blep.modtech.block.BlockMod;
import blep.modtech.reference.ModInfo;
import blep.modtech.core.IModtechTabGrapple;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ModTechCreativeTabs extends CreativeTabs
{
    public ModTechCreativeTabs()
    {
        super(ModInfo.MOD_NAME);
    }

    @Override
    public Item getTabIconItem()
    {
        return Items.emerald;
    }

    @Override
    public void displayAllRelevantItems(List<ItemStack> itemsShownOnTab)
    {
        for (Object itemObject : Item.itemRegistry)
        {
            Item item = (Item) itemObject;
            if ((item != null) && (item instanceof IModtechTabGrapple))
                item.getSubItems(item, this, itemsShownOnTab);
        }

        for (Object blockObject : Block.blockRegistry)
        {
            Block block = (Block) blockObject;
            if (block != null)
            {
                Class blockClass = block.getClass();
                if (blockClass.getSuperclass().equals(BlockMod.class))
                {
                    BlockMod modBlock = (BlockMod) block;
                        modBlock.getSubBlocks(Item.getItemFromBlock(modBlock), this, itemsShownOnTab);
                }
            }
        }
    }
}
