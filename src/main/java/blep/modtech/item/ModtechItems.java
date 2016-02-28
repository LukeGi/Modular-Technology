package blep.modtech.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public enum ModtechItems
{
    ;

    private String name;
    private Item item;

    ModtechItems(String name, Item item, CreativeTabs tab)
    {
        this.name = name;
        this.item = item;
        item.setUnlocalizedName(name);
        item.setCreativeTab(tab);
    }

    public static void registerAllItems()
    {
        for (ModtechItems item : ModtechItems.values())
            GameRegistry.registerItem(item.item, item.name);
    }
}
