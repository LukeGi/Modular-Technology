package com.blep.modularTechnology.core.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.blep.modularTechnology.common.core.util.IconHelper;
import net.blep.modularTechnology.common.magic.MTMagic;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class ModItem extends Item
{
    public ModItem()
    {
        super();
    }

    public ModItem(String name)
    {
        this();
        this.setUnlocalizedName(name);
    }

    @Override
    public Item setUnlocalizedName(String name)
    {
        GameRegistry.registerItem(this, name);
        return super.setUnlocalizedName(name);
    }

    @Override
    public String getUnlocalizedNameInefficiently(ItemStack stack)
    {
        return super.getUnlocalizedNameInefficiently(stack).replaceAll("item\\.", "item." + MTMagic.MODID + ":");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = IconHelper.forItem(iconRegister, this);
    }
}