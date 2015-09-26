package net.blep.modularTechnology.common.tech.items;

import net.blep.modularTechnology.common.core.items.ModItem;
import net.blep.modularTechnology.common.tech.EnumMetalMaterial;
import net.blep.modularTechnology.common.tech.tabs.CreativeTabHandler;

/**
 * @author TheEpicTekkit
 */
public class ItemFineOre extends ModItem
{
    protected ItemFineOre(EnumMetalMaterial material)
    {
        super("fineDust" + material.getName(), CreativeTabHandler.tabMetals);
    }
}
