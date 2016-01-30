package modulartechnology.networking;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class MyMessage implements IMessage {
    public MyMessage(){}

    private int toSend;
    public MyMessage(int toSend) {
        this.toSend = toSend;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        toSend = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(toSend);
    }

    public static class MyMessageHandler implements IMessageHandler<MyMessage, IMessage>{

        @Override
        public IMessage onMessage(MyMessage message, MessageContext ctx) {
            EntityPlayerMP serverplayer = ctx.getServerHandler().playerEntity;
            int amount = message.toSend;
            serverplayer.addChatMessage(new ChatComponentText("You are at y level: " + amount));
            return null;
        }
    }
}
