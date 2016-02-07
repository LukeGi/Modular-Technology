package net.blep.modtech.core.networking;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.blep.modtech.core.networking.packets.MessageSpawnParticle;
import net.blep.modtech.core.networking.packets.PacketTest;
import net.blep.modtech.core.reference.ModInfo;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by Kelan on 07/02/2016.
 */
public class NetworkManagerModtech
{
    public static final SimpleNetworkWrapper networkManager = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MOD_ID);
    private static int descriminator = 0;

    public void registerPackets()
    {
        networkManager.registerMessage(PacketTest.class, PacketTest.class, getDescr(), Side.CLIENT);
        networkManager.registerMessage(MessageSpawnParticle.class, MessageSpawnParticle.class, getDescr(), Side.CLIENT);
    }

    private int getDescr()
    {
        return descriminator++;
    }

    public static void sendTo(EntityPlayerMP player, IMessage message)
    {
        networkManager.sendToAll(message);
    }
}
