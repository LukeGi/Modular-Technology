package net.blep.modtech.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.blep.modtech.blocks.BlockHandler;
import net.blep.modtech.core.proxy.Proxy;
import net.blep.modtech.core.reference.ModInfo;
import net.blep.modtech.core.worldgen.WorldGeneratorModtech;
import net.blep.modtech.items.ItemHandler;

/**
 * Created by Kelan on 24/01/2016.
 */
@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, dependencies = ModInfo.DEPENDENCIES)
public final class ModTech
{
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
