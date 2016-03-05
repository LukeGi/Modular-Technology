package blep.modtech.network;


import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Kelan on 07/02/2016.
 */
public abstract class ModtechMessageBase<REQ extends IMessage> implements IMessage, IMessageHandler<REQ, REQ>, IModtechPacket<REQ>
{
    public REQ onMessage(REQ message, MessageContext context)
    {
        if (context.side == Side.SERVER)
            handleServer(message, context.getServerHandler().playerEntity);
        else
            handleClient(message, Minecraft.getMinecraft().thePlayer);

        return null;
    }
}
