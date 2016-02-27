package blep.modtech;

import blep.modtech.proxy.IProxy;
import blep.modtech.reference.ModInfo;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION_BUILD, dependencies = ModInfo.DEPENDENCIES)
public class ModTech
{
    @Mod.Instance
    public static ModTech INSTANCE;

    @SidedProxy(clientSide = ModInfo.CLIENT_PROXY_CLASS, serverSide = ModInfo.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.registerConfigs(event.getSuggestedConfigurationFile());
        proxy.registerBlocks();
        proxy.registerItems();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }

}
