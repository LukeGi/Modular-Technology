package com.blep.modularTechnology.core.common;
//
//import cpw.mods.fml.common.FMLCommonHandler;
//import cpw.mods.fml.common.Mod;
//import cpw.mods.fml.common.event.FMLInitializationEvent;
//import cpw.mods.fml.common.event.FMLPostInitializationEvent;
//import cpw.mods.fml.common.event.FMLPreInitializationEvent;
//import cpw.mods.fml.common.network.NetworkRegistry;
//import modulartechnology.inventory.ModGuiHandler;
//import com.blep.modularTechnology.core.common.util.LogHelper;
//import modulartechnology.reference.ModInfo;
//
//import static net.blep.modularTechnology.common.core.ModContent.*;
//
///**
// * @author TheEpicTekkit
// * @author bluemonster122 <boo122333@gmail.com>
// */
//
////@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, dependencies = ModInfo.DEPENDENCIES, guiFactory = ModularTechnology.GUI_FACTORY_CLASS)
public class ModularTechnology //implements IInitializable
{
//
//
//
//    @Mod.Instance(ModularTechnology.MOD_ID)
//    public static ModularTechnology instance;
//
//    public static LogHelper LOGGER = new LogHelper(MOD_ID);
//
//    public ModularTechnology instance()
//    {
//        return instance;
//    }
//
//    @Mod.EventHandler
//    public void preInit(FMLPreInitializationEvent event)
//    {
//        ConfigHandler.init(event.getSuggestedConfigurationFile());
//        FMLCommonHandler.instance().bus().register(new ConfigHandler());
//
//        getModContent().preInit(event);
//        AbstractCommonProxy.proxy.preInit(event);
//    }
//
//    @Mod.EventHandler
//    public void init(FMLInitializationEvent event)
//    {
//        getModContent().init(event);
//        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new ModGuiHandler());
//        AbstractCommonProxy.proxy.init(event);
//    }
//
//    @Mod.EventHandler
//    public void postInit(FMLPostInitializationEvent event)
//    {
//        getModContent().postInit(event);
////        LogHelper.logRegisteredObjects();
//        AbstractCommonProxy.proxy.postInit(event);
//    }
}