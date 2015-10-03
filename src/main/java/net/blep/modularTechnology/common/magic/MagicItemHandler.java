package net.blep.modularTechnology.common.magic;


import net.blep.modularTechnology.common.core.ModContent;
import net.minecraft.item.Item;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class MagicItemHandler extends ModContent
{
    public static Item raw_gem;
    public static Item polished_gem;
    public static Item gems;
    public static Item designator;

    public MagicItemHandler initItems()
    {
        raw_gem = new MagicItem("raw_gem");
        polished_gem = new MagicItem("polished_gem");
        gems = new ItemGem();
        designator = new ItemDesignator();

        return this;
    }
}
