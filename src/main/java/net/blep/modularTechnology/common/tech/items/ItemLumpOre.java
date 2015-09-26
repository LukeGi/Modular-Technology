package net.blep.modularTechnology.common.tech.items;

import net.blep.modularTechnology.common.core.items.ModItem;
import net.blep.modularTechnology.common.tech.EnumMetalMaterial;
import net.blep.modularTechnology.common.tech.tabs.CreativeTabHandler;

/**
 * @author TheEpicTekkit
 */
public class ItemLumpOre extends ModItem
{
    public final EnumMetalMaterial material;
    protected ItemLumpOre(EnumMetalMaterial material)
    {
        super("lump" + material.getName(), CreativeTabHandler.tabMetals);
        this.material = material;
    }
}
