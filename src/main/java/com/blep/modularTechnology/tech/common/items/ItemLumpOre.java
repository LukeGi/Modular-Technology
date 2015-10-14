package com.blep.modularTechnology.tech.common.items;

import net.blep.modularTechnology.common.tech.EnumMetalMaterial;
import net.blep.modularTechnology.common.tech.tabs.CreativeTabHandler;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author TheEpicTekkit
 */
public class ItemLumpOre extends ModItem
{
    public final EnumMetalMaterial material;
    public ItemLumpOre(EnumMetalMaterial material)
    {
        super("lump" + material.getName(), CreativeTabHandler.tabMetals);
        this.material = material;
        for (String s : material.getOreDictNames())
            OreDictionary.registerOre("dust" + s, this);
    }
}
