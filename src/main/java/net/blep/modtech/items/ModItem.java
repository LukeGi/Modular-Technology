package net.blep.modtech.items;

import net.blep.modtech.core.util.ItemTexture;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Kelan on 03/02/2016.
 */
public abstract class ModItem extends Item
{
    protected ItemTexture textureConfiguration;

    public ModItem(String itemName)
    {
        this.setUnlocalizedName(itemName);
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass)
    {
        if (textureConfiguration != null)
            return textureConfiguration.getIcon(stack, pass);
        else
            return super.getIcon(stack, pass);
    }

    /**
     * This is here so that you dont have to type out the params every time.
     */
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        return super.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        if (textureConfiguration != null)
            textureConfiguration.setupIcons(iconRegister);
    }

    public Item setTextureConfiguration(ItemTexture textureConfiguration)
    {
        this.textureConfiguration = textureConfiguration;
        return this;
    }
}
