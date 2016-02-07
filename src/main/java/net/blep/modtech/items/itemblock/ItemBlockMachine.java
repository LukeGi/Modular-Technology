package net.blep.modtech.items.itemblock;

import net.blep.modtech.core.util.EnumMachineProperties;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by Kelan on 06/02/2016.
 */
public class ItemBlockMachine extends ItemBlock
{
    public ItemBlockMachine(Block block)
    {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName(stack) + "." + EnumMachineProperties.values()[stack.getItemDamage()].getName().toLowerCase();
    }

    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }
}
