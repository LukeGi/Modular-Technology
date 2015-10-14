package net.blep.modularTechnology.common.core.items;

import net.blep.modularTechnology.common.tech.tabs.CreativeTabHandler;
import net.blep.modularTechnology.common.core.ModContent;
import com.blep.modularTechnology.core.common.ModularTechnology;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public abstract class ModItem extends Item
{
    protected final String name;

    protected ModItem(String name, CreativeTabs tab)
    {
        this.name = name;
        setUnlocalizedName(ModularTechnology.RESOURCE_PREFIX + this.getName());
        setTextureName(ModularTechnology.RESOURCE_PREFIX + this.getName());
        if (tab != null)
        {
            setCreativeTab(tab);
        }
    }

    public String getName()
    {
        return name;
    }

    public static CreativeTabHandler getModTabs()
    {
        return ModContent.getModTabs();
    }

    public static ModItem newModItem(String name, CreativeTabs tab)
    {
        return new ModItem(name, tab)
        {
            @Override
            public String getName()
            {
                return name;
            }
        };
    }
}
