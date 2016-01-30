package net.blep.modularTechnology.common.magic;

import com.blep.modularTechnology.core.common.CommonProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import com.blep.modularTechnology.core.common.ModularTechnology;
import com.blep.modularTechnology.core.common.util.LogHelper;
import net.blep.modularTechnology.common.magic.blocks.MagicBlockHandler;
import net.blep.modularTechnology.common.magic.items.MagicItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */

//@Mod(modid = MTMagic.MODID, name = MTMagic.MODNAME, version = MTMagic.VERSION, dependencies = MTMagic.DEPENDANCIES)
public class MTMagic extends ModularTechnology //implements IInitializable
{
    public static final String MODID = "modtechmagic";
    public static final String MODNAME = "Modular Technology Magic";
    public static final String VERSION = "PRC 0.1a";
    public static final String DEPENDANCIES = ""; // TODO "Modtech, Forge";
//    public static final LogHelper LOGGER = new LogHelper(MODID);
//
//    @Mod.Instance(MTMagic.MODID)
//    public static MTMagic instance;
//
//    @SidedProxy(clientSide = "net.blep.modularTechnology.core.magic.ClientProxy", serverSide = "net.blep.modularTechnology.core.magic.CommonProxy")
//    public static CommonProxy proxy;
//
//    public static CreativeTabs MagicTab = new CreativeTabs(MODID)
//    {
//        @Override
//        public Item getTabIconItem()
//        {
//            return new ItemStack(MagicItemHandler.gems, 3, 1).getItem();
//        }
//    };
//
//    public MTMagic instance()
//    {
//        return new MTMagic();
//    }
//
//    @Mod.EventHandler
//    public void preInit(FMLPreInitializationEvent event)
//    {
//        MagicBlockHandler.initBlocks();
//        MagicItemHandler.initItems();
//        stageComplete(1);
//    }
//
//    @Mod.EventHandler
//    public void init(FMLInitializationEvent event)
//    {
//
//        FMLCommonHandler.instance().bus().register(new CommonProxy());
//        stageComplete(2);
//    }
//
//    @Mod.EventHandler
//    public void postInit(FMLPostInitializationEvent event)
//    {
//        stageComplete(3);
//    }
//
//    private void stageComplete(int i)
//    {
//        LOGGER.info(String.format("%s has finished %s", MODNAME, i == 1 ? "pre-initializing" : i == 2 ? "initializing" : i == 3 ? "post-initializing" : "doing things"));
//    }
}
