package net.blep.modtech.items.itemblock;

import net.blep.modtech.blocks.block.BlockMetal;
import net.blep.modtech.blocks.block.BlockOre;
import net.blep.modtech.core.util.EnumMetalMaterial;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Kelan on 05/02/2016.
 */
public class ItemBlockMetalBlock extends ItemBlock
{
    public ItemBlockMetalBlock(Block block)
    {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        EnumMetalMaterial material = null;

        if (field_150939_a instanceof BlockOre) material = ((BlockOre) field_150939_a).getMaterial(stack.getItemDamage());
        if (field_150939_a instanceof BlockMetal) material = ((BlockMetal) field_150939_a).getMaterial(stack.getItemDamage());

        if (material == null)
            return super.getUnlocalizedName(stack) + ".error";
        return super.getUnlocalizedName(stack) + material.getName();
    }

    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
    {

    }
}
