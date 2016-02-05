package net.blep.modtech.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.blep.modtech.blocks.BlockHandler;
import net.blep.modtech.core.proxy.Proxy;
import net.blep.modtech.core.worldgen.WorldGeneratorModtech;
import net.blep.modtech.items.ItemHandler;

/**
 * Created by Kelan on 24/01/2016.
 */
@Mod(modid = ModTech.MOD_ID, name = ModTech.MOD_NAME, version = ModTech.MOD_VERSION)
public final class ModTech
{
    private static final int version = 1;
    private static final int subVersion = 0;
    private static final int releaseVersion = 0;
    private static final String state = "alpha";

    public static final String MOD_ID = "modtech";
    public static final String MOD_NAME = "Modular Technology";
    public static final String MOD_VERSION = version + "." + subVersion + " rv" + releaseVersion + " " + state;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        System.out.println("Preinit");
        BlockHandler.registerBlocks();
        ItemHandler.registerItems();
        Proxy.get().preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("Init");
        GameRegistry.registerWorldGenerator(new WorldGeneratorModtech(), 0);
        Proxy.get().init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        System.out.println("Postinit");
        Proxy.get().postInit(event);
    }
}
