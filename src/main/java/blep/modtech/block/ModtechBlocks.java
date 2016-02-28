package blep.modtech.block;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public enum ModtechBlocks
{
    ;

    private String name;
    private Block block;
    private ItemBlock itemBlock;

    ModtechBlocks(String name, Block block, ItemBlock itemBlock, CreativeTabs tab)
    {
        this.name = name;
        this.block = block;
        this.itemBlock = itemBlock;
        block.setUnlocalizedName(name);
        block.setCreativeTab(tab);
    }

    ModtechBlocks(String name, Block block, CreativeTabs tab)
    {
        this(name, block, new ItemBlock(block), tab);
    }


    public static void initAllBlocks()
    {
        for (ModtechBlocks block : ModtechBlocks.values())
            GameRegistry.registerBlock(block.block, block.itemBlock.getClass(), block.name);
    }
}
