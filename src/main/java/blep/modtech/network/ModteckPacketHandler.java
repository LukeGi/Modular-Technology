package blep.modtech.network;

import blep.modtech.network.message.MessageSpawnParticle;
import blep.modtech.reference.ModInfo;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ModteckPacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MOD_ID);
    public static int id = 0;

    public static void registerMessages()
    {
        INSTANCE.registerMessage(MessageSpawnParticle.class, MessageSpawnParticle.class, id++, Side.CLIENT);
    }
}