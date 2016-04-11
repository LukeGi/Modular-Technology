package blep.modtech.block;

import blep.modtech.core.ICustomItemBlock;
import blep.modtech.core.IRecipeProvider;
import blep.modtech.core.IRegisterTileEntity;
import blep.modtech.machine.farm.cobblestone.EntityCobbleFarm;
import blep.modtech.machine.farm.treefarm.EntityTreeFarm;
import blep.modtech.machine.generator.EntityGenerator;
import blep.modtech.reference.ModInfo;
import blep.modtech.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public enum ModtechBlocks
{
    TREE_FARM("treefarm", new EntityTreeFarm()),
    MODULAR_STORAGE("modularStorage", new EntityModularStorage()),
    COBBLE_GENERATOR("cobblegen", new EntityCobbleFarm()),
    GENERATOR("generator", new EntityGenerator());

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
                if (b.block instanceof IRegisterTileEntity)
                    ((IRegisterTileEntity)b.block).registerTileEntity();
                if (b.block instanceof IRecipeProvider)
                    ((IRecipeProvider)b.block).registerRecipe();
            }
            registeredBlock = true;
        }
    }

    public String getInternalName()
    {
        return internalName;
    }

    public String getStatName()
    {
        return I18n.translateToLocal(block.getUnlocalizedName().replace("tileentity.", "block."));
    }

    private void registerBlock()
    {
        GameRegistry.register(block.setCreativeTab(creativeTabs).setUnlocalizedName(ModInfo.MOD_ID + "." + internalName), new ResourceLocation(ModInfo.MOD_ID, internalName));
        if (block instanceof ICustomItemBlock)
            GameRegistry.register(((ICustomItemBlock)block).getCustomItemBlock(), new ResourceLocation(ModInfo.MOD_ID, internalName));
        else GameRegistry.register(new ItemBlock(block), new ResourceLocation(ModInfo.MOD_ID, internalName));

        LogHelper.info("Registered Block: " + internalName);
    }
}
