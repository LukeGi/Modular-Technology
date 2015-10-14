package net.blep.modularTechnology.common.tech.items.item;

import net.blep.modularTechnology.common.core.items.ModItem;
import net.blep.modularTechnology.common.tech.EnumMetalMaterial;
import net.blep.modularTechnology.common.tech.tabs.CreativeTabHandler;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author TheEpicTekkit
 */
public class ItemFineOre extends ModItem
{
    public ItemFineOre(EnumMetalMaterial material)
    {
        super("fineDust" + material.getName(), CreativeTabHandler.tabMetals);
        for (String s : material.getOreDictNames())
            OreDictionary.registerOre("dustFine" + s, this);
    }
}
