package net.blep.modularTechnology.common.tech.items;

import net.blep.modularTechnology.common.core.items.ModItem;
import net.blep.modularTechnology.common.tech.EnumMetalMaterial;

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
    }
}
