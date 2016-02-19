package net.blep.modtech.core.networking.packets;

import io.netty.buffer.ByteBuf;
import net.blep.modtech.core.networking.PacketModtechBase;
import net.blep.modtech.core.proxy.ModHandler;
import net.blep.modtech.core.util.Vector3i;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class MessageBreakBlock extends PacketModtechBase<MessageBreakBlock> {
    public MessageBreakBlock() {
    }

    public int x, y, z;

    public MessageBreakBlock(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MessageBreakBlock(Vector3i v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    @Override
    public void handleClient(MessageBreakBlock message, EntityPlayer player) {

    }

    @Override
    public void handleServer(MessageBreakBlock message, EntityPlayer player) {
        ModHandler.get().getMinecraftClient().theWorld.setBlockToAir(message.x, message.y, message.z);
    }
}
