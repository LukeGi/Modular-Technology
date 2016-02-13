package net.blep.modtech.core.networking.packets;

import io.netty.buffer.ByteBuf;
import net.blep.modtech.core.networking.PacketModtechBase;
import net.blep.modtech.core.util.LogHelper;
import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class MessageSpawnEntity extends PacketModtechBase<MessageSpawnEntity> {

    public MessageSpawnEntity() {
    }

    public int type;
    public double x, y, z;

    public MessageSpawnEntity(EntityType type, double x, double y, double z) {
        this.type = type.ordinal();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(type);
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        type = buf.readInt();
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
    }

    @Override
    public void handleServer(MessageSpawnEntity message, EntityPlayer player) {
        Entity entity;
        switch (message.type) {
            case 0:
                player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, message.x, message.y, message.z));
                return;
            default:
                entity = null;
                LogHelper.error("EntitySpawning packet has invalid EntityType provided");
                return;
        }
    }

    @Override
    public void handleClient(MessageSpawnEntity message, EntityPlayer player) {

    }

    public enum EntityType {
        LIGHTNING;
    }
}
