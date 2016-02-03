package modulartechnology.networking;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import modulartechnology.reference.ModInfo;

public class ModularTechnologyPacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MOD_ID);
    public static int id = 0;

    public static void registerMessages(){
        // Fix that the packets dont have a proper handler TODO
//        INSTANCE.registerMessage(MessageMoveEntity.class, MessageMoveEntity.class, id++, Side.SERVER);
//        INSTANCE.registerMessage(MessageSpawnEntity.class, MessageSpawnEntity.class, id++, Side.SERVER);
//        INSTANCE.registerMessage(MessageSetBlock.class, MessageSetBlock.class, id++, Side.SERVER);
//        INSTANCE.registerMessage(MessageTeleportEntity.class, MessageTeleportEntity.class, id++, Side.SERVER);
    }
}
