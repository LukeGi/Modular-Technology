package com.blep.modularTechnology.core.common.items;

import com.blep.modularTechnology.core.common.ModularTechnology;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.blep.modularTechnology.core.common.util.IconHelper;
import net.blep.modularTechnology.common.magic.MTMagic;
import net.blep.modularTechnology.common.magic.items.MagicItemHandler;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
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

    public ModItem(String name, CreativeTabs tab)
    {
        this(name);
        setCreativeTab(tab);
    }

    public Item setUnlocalizedName(String name)
    {
        GameRegistry.registerItem(this, name);
        return super.setUnlocalizedName(name);
    }

    public String getUnlocalizedNameInefficiently(ItemStack stack)
    {
        return super.getUnlocalizedNameInefficiently(stack).replaceAll("item\\.", "items." + ModularTechnology.MOD_ID + ":");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = IconHelper.forItem(iconRegister, this);
    }
}
