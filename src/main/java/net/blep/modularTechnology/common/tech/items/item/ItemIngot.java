package net.blep.modularTechnology.common.tech.items.item;

import net.blep.modularTechnology.common.core.items.ModItem;
import net.blep.modularTechnology.common.tech.EnumMetalMaterial;
import net.minecraftforge.oredict.OreDictionary;

/**
 * This Item is like a Metal bar which can be used for crafting.
 *
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class ItemIngot extends ModItem
{
    public final EnumMetalMaterial material;

    public ItemIngot(EnumMetalMaterial material)
    {
        super("ingot" + material.getName(), getModTabs().tabMetals);
        this.material = material;
        for (String s : material.getOreDictNames())
            OreDictionary.registerOre("ingot" + s, this);
    }
}
