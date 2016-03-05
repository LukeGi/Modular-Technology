package blep.modtech.creativetab;

import blep.modtech.block.BlockMod;
import blep.modtech.block.metal.BlockOre;
import blep.modtech.reference.ModInfo;
import blep.modtech.util.IModtechObject;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

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
    public void displayAllReleventItems(List<ItemStack> itemsShownOnTab)
    {
        for (Object itemObject : Item.itemRegistry)
        {
            Item item = (Item) itemObject;
            if ((item != null) && (item instanceof IModtechObject))
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
                    if (blockClass.equals(BlockOre.class))
                    {
                        BlockOre oreBlock = (BlockOre) modBlock;
                        oreBlock.getSubBlocks(Item.getItemFromBlock(oreBlock), this, itemsShownOnTab);
                    } else
                    {
                        modBlock.getSubBlocks(Item.getItemFromBlock(modBlock), this, itemsShownOnTab);
                    }
                }
            }
        }
    }
}
