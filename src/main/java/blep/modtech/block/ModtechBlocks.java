package blep.modtech.block;

import blep.modtech.DummyWorld;
import blep.modtech.block.farms.BlockTreeFarm;
import blep.modtech.block.metal.BlockOre;
import blep.modtech.item.metal.ItemBlockOre;
import blep.modtech.reference.ModInfo;
import blep.modtech.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public enum ModtechBlocks
{
    TREE_FARM("treefarm", new BlockTreeFarm()),
    METAL_ORE("ore", new BlockOre(), ItemBlockOre.class),
    MODULAR_STORAGE("modularStorage", new BlockModularStorage()),;

    private static boolean registeredBlock = false;
    public final Block block;
    private final String internalName;
    private final Class<? extends ItemBlock> itemBlockClass;
    private final CreativeTabs creativeTabs;

    ModtechBlocks(String internalName, Block block)
    {
        this(internalName, block, ItemBlock.class, null);
    }

    ModtechBlocks(String internalName, Block block, CreativeTabs creativeTabs)
    {
        this(internalName, block, ItemBlock.class, creativeTabs);
    }

    ModtechBlocks(String internalName, Block block, Class<? extends ItemBlock> itemBlockClass)
    {
        this(internalName, block, itemBlockClass, null);
    }

    ModtechBlocks(String internalName, Block block, Class<? extends ItemBlock> itemBlockClass, CreativeTabs creativeTabs)
    {
        this.internalName = internalName;
        this.block = block;
        this.itemBlockClass = itemBlockClass;
        this.creativeTabs = creativeTabs;
    }

    public static void registerAll()
    {
        LogHelper.info("Registering things");
        if (!registeredBlock)
        {
            for (ModtechBlocks b : ModtechBlocks.values())
            {
                b.registerBlock();
                if (b.block instanceof ITileEntityProvider)
                    b.registerTileEntity();
            }
            registeredBlock = true;
        }
    }

    private void registerTileEntity()
    {
        GameRegistry.registerTileEntity(((ITileEntityProvider) block).createNewTileEntity(new DummyWorld(), 0).getClass(), ModInfo.MOD_ID + ":" + internalName);

        LogHelper.info("Registered Tile Entity: " + internalName);
    }

    public String getInternalName()
    {
        return internalName;
    }

    public String getStatName()
    {
        return StatCollector.translateToLocal(block.getUnlocalizedName().replace("tileentity.", "block."));
    }

    private void registerBlock()
    {
        GameRegistry.registerBlock(block.setCreativeTab(creativeTabs).setUnlocalizedName(ModInfo.MOD_ID + "." + internalName),
                itemBlockClass, internalName);

        LogHelper.info("Registered Block: " + internalName);
    }
}
