package modulartechnology;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import modulartechnology.config.ConfigHandler;
import modulartechnology.item.ItemBase;
import modulartechnology.proxy.IProxy;
import modulartechnology.reference.ModInfo;
import net.minecraft.item.Item;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, dependencies = ModInfo.DEPENDENCIES, guiFactory = ModInfo.GUI_FACTORY_CLASS)
public class ModularTechnology {
    @Mod.Instance
    public static ModularTechnology instance;

    @SidedProxy (clientSide = ModInfo.CLIENT_PROXY_CLASS, serverSide = ModInfo.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Deal with Configs
        ConfigHandler.init(event.getSuggestedConfigurationFile());

        // Deal with Items
        proxy.registerItems();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Deal with Blocks
        proxy.registerPackets();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
