package net.blep.modularTechnology.common.tech.items;

import net.blep.modularTechnology.common.core.items.ModItem;
import net.blep.modularTechnology.common.tech.EnumMetalMaterial;
import net.blep.modularTechnology.common.tech.tabs.CreativeTabHandler;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class ItemNugget extends ModItem
{
    public ItemNugget(EnumMetalMaterial material)
    {
        super("nugget" + material.getName(), CreativeTabHandler.tabMetals);
    }
}
