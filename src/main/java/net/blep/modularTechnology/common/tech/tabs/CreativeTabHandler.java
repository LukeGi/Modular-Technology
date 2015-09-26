package net.blep.modularTechnology.common.tech.tabs;

import net.blep.modularTechnology.common.core.ModContent;
import net.blep.modularTechnology.common.core.ModularTechnology;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * @author TheEpicTekkit
 */
public class CreativeTabHandler extends ModContent
{
    public static final TabModtech tabModtech = new TabModtech("tabModtech", Items.apple);
    public static final TabModtech tabMetals = new TabModtech("tabMetals", Items.iron_ingot);
    public static final TabModtech tabMachines = new TabModtech("tabMachines", Blocks.furnace);
    public static final TabModtech tabMagic = new TabModtech("tabMagic", Items.stick);

    public static class TabModtech extends CreativeTabs
    {
        public final Item icon;

        public TabModtech(String name, Item icon)
        {
            super(ModularTechnology.MOD_ID + ":" + name);
            this.icon = icon;
        }

        public TabModtech(String name, Block icon)
        {
            this(name, Item.getItemFromBlock(icon));
        }

        @Override
        public Item getTabIconItem()
        {
            return icon;
        }
    }
}
