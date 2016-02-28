package blep.modtech.item;

import blep.modtech.creativetab.ModTechCreativeTabs;
import blep.modtech.reference.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public enum ModtechItems
{
    TEST_ITEM("testitem", new ItemMod(), ModTechCreativeTabs.getInstance()),;

    private String name;
    private Item item;
    private CreativeTabs tab;

    ModtechItems(String name, Item item, CreativeTabs tab)
    {
        this.name = name;
        this.item = item;
        this.tab = tab;
    }

    public static void registerAllItems()
    {
        for (ModtechItems item : ModtechItems.values())
            item.registerItem();
    }

    public static void registerAllItemRenders()
    {
        for (ModtechItems item : ModtechItems.values())
            item.registerItemRender();
    }

    private void registerItem()
    {
        GameRegistry.registerItem(item.setUnlocalizedName(name).setCreativeTab(tab), name);
    }

    private void registerItemRender()
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ModInfo.MOD_ID + ":" + name, "inventory"));
    }
}
