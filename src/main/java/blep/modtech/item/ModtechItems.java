package blep.modtech.item;

import blep.modtech.item.tools.ItemMultiblockBuilder;
import blep.modtech.item.tools.ItemUberHoe;
import blep.modtech.reference.ModInfo;
import blep.modtech.util.LogHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public enum ModtechItems
{
    TEST_ITEM("testitem", new ItemMod()),
    UBER_HOE_1000("überhoe", new ItemUberHoe()),
    MULTIBLOCK_CREATOR("multiblockCreator", new ItemMultiblockBuilder()),
    ;

    private static boolean registeredItem = false;
    public final Item item;
    private final String internalName;
    private final CreativeTabs creativeTabs;

    ModtechItems(String internalName, Item item)
    {
        this(internalName, item, null);
    }

    ModtechItems(String internalName, Item item, CreativeTabs creativeTabs)
    {
        this.internalName = internalName;
        this.item = item;
        item.setUnlocalizedName(ModInfo.MOD_ID + "." + internalName);
        this.creativeTabs = creativeTabs;
    }

    public static void registerAll()
    {
        if (registeredItem)
            return;
        for (ModtechItems i : ModtechItems.values())
            i.registerItem();
        registeredItem = true;
    }

    public String getInternalName()
    {
        return internalName;
    }

    public String getStatName()
    {
        return I18n.translateToLocal(item.getUnlocalizedName());
    }

    private void registerItem()
    {
        GameRegistry.registerItem(item, internalName);

        LogHelper.info("Registered Item: " + internalName);
    }

    public ItemStack getStack(int damage, int size)
    {
        return new ItemStack(item, size, damage);
    }
}
