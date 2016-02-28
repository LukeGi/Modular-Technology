package blep.modtech.creativetab;

import blep.modtech.reference.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ModTechCreativeTabs extends CreativeTabs
{
    private static final ModTechCreativeTabs instance = new ModTechCreativeTabs();

    public ModTechCreativeTabs()
    {
        super(ModInfo.MOD_NAME);
    }

    public static ModTechCreativeTabs getInstance()
    {
        return instance;
    }

    @Override
    public Item getTabIconItem()
    {
        return Items.emerald;
    }
}
