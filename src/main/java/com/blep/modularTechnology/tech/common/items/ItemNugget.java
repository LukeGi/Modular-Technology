package com.blep.modularTechnology.tech.common.items;

import net.blep.modularTechnology.common.tech.EnumMetalMaterial;
import net.blep.modularTechnology.common.tech.tabs.CreativeTabHandler;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class ItemNugget extends ModItem
{
    public ItemNugget(EnumMetalMaterial material)
    {
        super("nugget" + material.getName(), CreativeTabHandler.tabMetals);
        for (String s : material.getOreDictNames())
            OreDictionary.registerOre("nugget" + s, this);
    }
}
