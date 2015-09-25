package net.blep.modularTechnology.common.core.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.blep.modularTechnology.common.core.ModContent;
import net.blep.modularTechnology.common.core.network.packets.MessageMoveEntity;
import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
import net.blep.modularTechnology.common.core.network.packets.MessageSpawnEntity;

import static cpw.mods.fml.relauncher.Side.SERVER;

/**
 * @author TheEpicTekkit
 */
public final class ModPacketHandler extends ModContent
{
    private static final ModPacketHandler singleton = new ModPacketHandler();
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
    private static int nextID = 0;

    private ModPacketHandler() {}

    public ModPacketHandler initPackets()
    {
        INSTANCE.registerMessage(MessageMoveEntity.class, MessageMoveEntity.class, nextID++, SERVER);
        INSTANCE.registerMessage(MessageSpawnEntity.class, MessageSpawnEntity.class, nextID++, SERVER);
        INSTANCE.registerMessage(MessageSetBlock.class, MessageSetBlock.class, nextID++, SERVER);

        return this;
    }

    public static ModPacketHandler getSingleton()
    {
        return singleton;
    }
}
