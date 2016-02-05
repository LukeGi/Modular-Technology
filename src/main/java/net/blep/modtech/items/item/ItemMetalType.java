package net.blep.modtech.items.item;

import net.blep.modtech.core.util.EnumMetalMaterial;
import net.blep.modtech.core.util.EnumMetalType;
import net.blep.modtech.core.util.ItemTexture;
import net.blep.modtech.items.ModItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kelan on 03/02/2016.
 */
public class ItemMetalType extends ModItem
{
    private IIcon[] icons = new IIcon[EnumMetalType.values().length];

    public ItemMetalType()
    {
        super("metalType");
        setHasSubtypes(true);
        setCreativeTab(CreativeTabs.tabRedstone);
        setTextureConfiguration(new ItemTexture("ingot"));
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List items)
    {
        int meta = 0;
        for (int i = 0; i < EnumMetalMaterial.values().length; i++)
        {
            for (int j = 0; j < EnumMetalType.values().length; j++)
            {
                items.add(new ItemStack(item, 1, meta++));
            }
        }
    }

    @Override
    public IIcon getIconFromDamage(int meta)
    {
        IIcon icon = icons[meta % EnumMetalType.values().length];
        return icon;
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass)
    {
        IIcon icon = icons[stack.getItemDamage() % EnumMetalType.values().length];
        return icon;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        for (int i = 0; i < icons.length; i++)
        {
            icons[i] = iconRegister.registerIcon("modtech:" + EnumMetalType.values()[i].getUnlocalizedName());
        }
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int partialTick)
    {
        EnumMetalMaterial material = getMaterial(stack.getItemDamage());
        Vector3f colour = material.getColour();

        return ((int) (colour.x * 255.0F) << 16) | ((int) (colour.y * 255.0F) << 8) | (int) (colour.z * 255.0F);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return getUnlocalizedName() + "." + getMetalType(stack.getItemDamage()).getUnlocalizedName() + getMaterial(stack.getItemDamage()).getName();
    }

    public EnumMetalMaterial getMaterial(int meta)
    {
        int index = meta / EnumMetalType.values().length;
        return EnumMetalMaterial.values()[index];
    }

    public EnumMetalType getMetalType(int meta)
    {
        return EnumMetalType.values()[meta % EnumMetalType.values().length];
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List information, boolean flag)
    {
        information.add("Item Type: " + getMetalType(stack.getItemDamage()).getLocalizedName());
        information.add("Material: " + getMaterial(stack.getItemDamage()).getName());
    }
}
