package net.blep.modularTechnology.common.core;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.blep.modularTechnology.common.core.handler.ConfigHandler;

import static net.blep.modularTechnology.common.core.ModContent.*;

/**
 * @author TheEpicTekkit
 * @author bluemonster122 <boo122333@gmail.com>
 */

@Mod(modid = ModularTechnology.MOD_ID, name = ModularTechnology.MOD_NAME, version = ModularTechnology.MOD_VERSION, dependencies = ModularTechnology.DEPENDENCIES, guiFactory = ModularTechnology.GUI_FACTORY_CLASS)
public class ModularTechnology implements IInitializable
{
    public static final String MOD_NAME = "Modular Technology";
    public static final String MOD_ID = "modtech";
    public static final boolean isAlpha = true, isBeta = false, preRelease = true, isPatch = false;
    public static final String MOD_VERSION = "1.7.10-" + (preRelease ? "pre-" : "") + ((isAlpha && !isBeta) ? "alpha-" : (isBeta && !isAlpha) ? "beta-" : "RV-") + "0.0.1" + (isPatch ? "-patch" : ""); //RV is release version
    public static final String DEPENDENCIES = "required-after:FML@[7.10.0.1205,7.11);required-after:Forge@[10.13.0.1205,10.14);";
    public static final String RESOURCE_PREFIX = MOD_ID + ":";

    public static final String GUI_FACTORY_CLASS = "net.blep.modularTechnology.client.core.gui.GuiFactory";

    @Mod.Instance(ModularTechnology.MOD_ID)
    private static ModularTechnology instance;

    public ModularTechnology instance()
    {
        return instance;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigHandler());

        getModContent().preInit(event);
        Proxy.proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        getModContent().init(event);
        Proxy.proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        getModContent().postInit(event);
//        LogHelper.logRegisteredObjects();
        Proxy.proxy.postInit(event);
    }
}