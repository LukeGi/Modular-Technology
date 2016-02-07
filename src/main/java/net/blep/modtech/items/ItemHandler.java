package net.blep.modtech.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.blep.modtech.items.item.ItemMetalType;
import net.blep.modtech.items.item.ItemSpawnParticle;
import net.blep.modtech.items.item.ItemWrench;
import net.minecraft.item.Item;

/**
 * Created by Kelan on 03/02/2016.
 */
public class ItemHandler
{
    public static final Item ITEM_METAL_TYPE = new ItemMetalType();
    public static final Item ITEM_WRENCH = new ItemWrench();
    public static final Item TEST = new ItemSpawnParticle();

    public static void registerItems()
    {
        GameRegistry.registerItem(ITEM_METAL_TYPE, "itemMetalType");
        GameRegistry.registerItem(ITEM_WRENCH, "itemWrench");
        GameRegistry.registerItem(TEST, "itemTest");
    }
}
