package modulartechnology.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBase extends Item {
    private final String fullName;

    public ItemBase(String name) {
        this.fullName = name;
    }

    @Override
    public String toString() {
        return this.fullName;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void addInformation(final ItemStack stack, final EntityPlayer player, final List lines, final boolean displayMoreInfo) {
        this.addCheckedInformation(stack, player, lines, displayMoreInfo);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void getSubItems(final Item sameItem, final CreativeTabs creativeTab, final List itemStacks) {
        this.getCheckedSubItems(sameItem, creativeTab, itemStacks);
    }

    @Override
    public boolean isBookEnchantable(final ItemStack itemstack1, final ItemStack itemstack2) {
        return false;
    }

    protected void addCheckedInformation(final ItemStack stack, final EntityPlayer player, final List<String> lines, final boolean displayMoreInfo) {
        super.addInformation(stack, player, lines, displayMoreInfo);
    }

    protected void getCheckedSubItems(final Item sameItem, final CreativeTabs creativeTab, final List<ItemStack> itemStacks) {
        super.getSubItems(sameItem, creativeTab, itemStacks);
    }
}
