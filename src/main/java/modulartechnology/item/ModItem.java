package modulartechnology.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import modulartechnology.ModularTechnology;
import modulartechnology.reference.ModInfo;
import modulartechnology.util.IconHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class ModItem extends Item
{
    public ModItem()
    {
        super();
    }

    public ModItem(String name)
    {
        this();
        this.setUnlocalizedName(name);
    }

    public ModItem(String name, CreativeTabs tab)
    {
        this(name);
        setCreativeTab(tab);
    }

    public Item setUnlocalizedName(String name)
    {
        GameRegistry.registerItem(this, name);
        return super.setUnlocalizedName(name);
    }

    public String getUnlocalizedNameInefficiently(ItemStack stack)
    {
        return super.getUnlocalizedNameInefficiently(stack).replaceAll("item\\.", "items." + ModInfo.MOD_ID + ":");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = IconHelper.forItem(iconRegister, this);
    }
}
