package com.blep.modularTechnology.core.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

/**
 * @author TheEpicTekkit
 */
public abstract class PacketBase<T extends IMessage> implements IMessage, IMessageHandler<T, T>
{
//    public T onMessage(T message, MessageContext messageContext)
//    {
//        if (messageContext.side.equals(Side.SERVER))
//            handleServer(message, messageContext.getServerHandler().playerEntity);
//        else
//            handleClient(message, AbstractCommonProxy.proxy.getPlayer());
//        return null;
//    }
//
//    public abstract void handleServer(T message, Object... o);
//
//    public abstract void handleClient(T message, Object... o);
}
