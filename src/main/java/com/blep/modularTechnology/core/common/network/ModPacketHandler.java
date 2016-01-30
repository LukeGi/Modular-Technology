package com.blep.modularTechnology.core.common.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.blep.modularTechnology.common.core.ModContent;
import com.blep.modularTechnology.core.common.network.packets.MessageMoveEntity;
import com.blep.modularTechnology.core.common.network.packets.MessageSetBlock;
import com.blep.modularTechnology.core.common.network.packets.MessageSpawnEntity;
import com.blep.modularTechnology.core.common.network.packets.MessageTeleportEntity;

import static cpw.mods.fml.relauncher.Side.*;

/**
 * @author TheEpicTekkit
 */
public final class ModPacketHandler extends ModContent
{
//    private static final ModPacketHandler singleton = new ModPacketHandler();
//    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
//    private static int nextID = 0;
//
//    private ModPacketHandler() {}
//
//    public ModPacketHandler initPackets()
//    {
//        INSTANCE.registerMessage(MessageMoveEntity.class, MessageMoveEntity.class, nextID++, SERVER);
//        INSTANCE.registerMessage(MessageSpawnEntity.class, MessageSpawnEntity.class, nextID++, SERVER);
//        INSTANCE.registerMessage(MessageSetBlock.class, MessageSetBlock.class, nextID++, SERVER);
//        INSTANCE.registerMessage(MessageTeleportEntity.class, MessageTeleportEntity.class, nextID++, SERVER);
//
//        return this;
//    }
//
//    public static ModPacketHandler getSingleton()
//    {
//        return singleton;
//    }
}
