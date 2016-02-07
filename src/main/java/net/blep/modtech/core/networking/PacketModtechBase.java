package net.blep.modtech.core.networking;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.blep.modtech.core.proxy.Proxy;

/**
 * Created by Kelan on 07/02/2016.
 */
public abstract class PacketModtechBase<REQ extends IMessage> implements IMessage, IMessageHandler<REQ, REQ>, IModtechPacket<REQ>
{
    public REQ onMessage(REQ message, MessageContext context)
    {
        if (context.side == Side.SERVER)
            handleServer(message, context.getServerHandler().playerEntity);
        else
            handleClient(message, Proxy.get().getPlayerSP());

        return null;
    }
}
