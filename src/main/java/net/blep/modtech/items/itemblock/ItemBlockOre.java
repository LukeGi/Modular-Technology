package net.blep.modtech.items.itemblock;

import net.blep.modtech.blocks.BlockHandler;
import net.blep.modtech.core.util.EnumMetalMaterial;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

/**
 * Created by Kelan on 03/02/2016.
 */
public class ItemBlockOre extends ItemBlock
{
    public ItemBlockOre(Block block)
    {
        super(BlockHandler.BLOCK_METAL_ORE);
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List items)
    {
        for (int i = 0; i < EnumMetalMaterial.ORES.length; i++)
        {
            items.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName(stack) + "." + getMaterial(stack.getItemDamage()).getName();
    }

    private EnumMetalMaterial getMaterial(int meta)
    {
        return EnumMetalMaterial.values()[meta];
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
    {
        list.add("Ore: " + getMaterial(stack.getItemDamage()));
    }
}
