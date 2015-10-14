package net.blep.modularTechnology.common.magic.items;


import cpw.mods.fml.common.registry.GameRegistry;
import net.blep.modularTechnology.common.magic.MTMagic;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class MagicItemHandler extends MTMagic
{
    public static Item raw_gem;
    public static Item polished_gem;
    public static Item gems;
    public static Item designator;
    public static Item chainsaw;

    public static void initItems()
    {
        raw_gem = new MagicItem("raw_gem");
        polished_gem = new MagicItem("polished_gem");
        gems = new ItemGem();
        designator = new ItemDesignator();
        chainsaw = new ItemChainsaw();
    }

    public static void registerMagicItem(MagicItem item, String name)
    {
        GameRegistry.registerItem(item, name);
        item.setCreativeTab(MagicTab);
    }
}
