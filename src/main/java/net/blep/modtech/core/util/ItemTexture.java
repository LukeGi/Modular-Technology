package net.blep.modtech.core.util;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Kelan on 03/02/2016.
 */
public class ItemTexture
{
    private Vector3f[] metaColours;
    private IIcon[] metaIcons;
    private String[] metaFiles;

    public ItemTexture(String... metaFiles)
    {
        this.metaFiles = metaFiles;
        metaColours = new Vector3f[metaFiles.length];
        metaIcons = new IIcon[metaFiles.length];

        for (int i = 0; i < metaFiles.length; i++)
        {
            metaColours[i] = new Vector3f(1.0F, 1.0F, 1.0F);
        }
    }

    public IIcon getIcon(ItemStack stack, int pass)
    {
        return metaIcons[stack.getItemDamage()];
    }

    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
        return metaIcons[stack.getItemDamage()];
    }

    public void setupIcons(IIconRegister iconRegister)
    {
        for (int i = 0; i < metaFiles.length; i++)
            this.metaIcons[i] = iconRegister.registerIcon("modtech:" + metaFiles[i]);
    }

    public void setMetaColours(Vector3f[] colours)
    {
        this.metaColours = colours;
    }

    public void setMetaColour(int meta, Vector3f colour)
    {
        if (meta >= 0 && meta < metaColours.length)
            metaColours[meta] = colour;
    }

    public int getColourFromItemStack(ItemStack stack, int partialTick)
    {
        int m = stack.getItemDamage();
        Vector3f c;

        if (m < 0 || m >= metaColours.length)
            c = new Vector3f(1.0F, 1.0F, 1.0F);
        else
            c = metaColours[m];

        return ((int) (c.x * 255) << 16) | ((int) (c.y * 255) << 8) | (int) (c.z * 255);
    }
}
