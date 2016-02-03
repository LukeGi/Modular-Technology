package modulartechnology.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;

public enum ModItems {
    ;

    private final Item item;

    ModItems(String name, Item item) {
        this(name, item, null);
    }

    ModItems(String name, Item item, IRecipe[] recipes) {
        this.item = item.setUnlocalizedName(name);
        for (IRecipe recipe : recipes)
            GameRegistry.addRecipe(recipe);
    }
}
