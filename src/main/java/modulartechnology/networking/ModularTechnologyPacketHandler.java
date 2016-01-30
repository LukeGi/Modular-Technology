package modulartechnology.networking;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import modulartechnology.reference.ModInfo;

public class ModularTechnologyPacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MOD_ID);
    public static int id = 0;

    public static void registerMessages(){
        INSTANCE.registerMessage(MyMessage.MyMessageHandler.class, MyMessage.class, id++, Side.SERVER);
    }
}
