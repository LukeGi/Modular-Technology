package blep.modtech.block;

import blep.modtech.creativetab.ModTechCreativeTabs;
import blep.modtech.reference.ModInfo;
import blep.modtech.util.IHasTileEntity;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public enum ModtechBlocks
{
    TREE_FARM("treefarm", new BlockTreeFarm(), ModTechCreativeTabs.getInstance()),
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


    public static void regsiterAllBlocks()
    {
        for (ModtechBlocks block : ModtechBlocks.values())
            block.registerBlock();
    }

    public static void registerAllBlockRenders()
    {
        for (ModtechBlocks block : ModtechBlocks.values())
            block.registerRenderer();
    }

    private void registerBlock()
    {
        GameRegistry.registerBlock(block, itemBlock.getClass(), name);
        if (block instanceof IHasTileEntity)
            GameRegistry.registerTileEntity(((IHasTileEntity) block).getTileClass(), ModInfo.MOD_ID + name);
    }

    private void registerRenderer()
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(ModInfo.MOD_ID + ":" + name, "inventory"));
    }

}
