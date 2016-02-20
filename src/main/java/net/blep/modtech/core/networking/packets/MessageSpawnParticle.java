package net.blep.modtech.core.networking.packets;

import io.netty.buffer.ByteBuf;
import net.blep.modtech.client.particle.EntityBubbleLightningFX;
import net.blep.modtech.core.networking.PacketModtechBase;
import net.blep.modtech.core.proxy.ModHandler;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class MessageSpawnParticle extends PacketModtechBase<MessageSpawnParticle> {
    public MessageSpawnParticle() {
    }

    int particleId, amount;
    double x, y, z;

    public MessageSpawnParticle(ParticleTypes particleId, double x, double y, double z, int amount) {
        this.particleId = particleId.ordinal();
        this.x = x;
        this.y = y;
        this.z = z;
        this.amount = amount;
    }

    public EntityFX getParticleFromId(World world, double x, double y, double z, int particleId) {
        switch (particleId) {
            case 0:
                return new EntityBubbleLightningFX(world, x, y, z);
        }

        return null;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        particleId = buf.readByte();
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
        amount = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(particleId);
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeInt(amount);
    }

    @Override
    public void handleServer(MessageSpawnParticle message, EntityPlayer player) {

    }

    @Override
    public void handleClient(MessageSpawnParticle message, EntityPlayer player) {
        for (int i = 0; i < message.amount; i++)
            ModHandler.get().spawnParticle(message.getParticleFromId(player.worldObj, message.x, message.y, message.z, message.particleId));
    }

    public enum ParticleTypes {
        SHINYLIGHTNING
    }
}
