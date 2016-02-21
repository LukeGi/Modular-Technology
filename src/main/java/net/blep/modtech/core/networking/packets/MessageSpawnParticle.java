package net.blep.modtech.core.networking.packets;

import io.netty.buffer.ByteBuf;
import net.blep.modtech.client.particle.EntityBubbleLightningFX;
import net.blep.modtech.core.networking.PacketModtechBase;
import net.blep.modtech.core.proxy.ModHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class MessageSpawnParticle extends PacketModtechBase<MessageSpawnParticle> {
    public MessageSpawnParticle() {
    }

    int particleId, blockId, amount;
    double x, y, z;

    public MessageSpawnParticle(ParticleTypes particleId, double x, double y, double z, int amount, int blockId) {
        this.particleId = particleId.ordinal();
        this.x = x;
        this.y = y;
        this.z = z;
        this.amount = amount;
        this.blockId = blockId;
    }

    public MessageSpawnParticle(ParticleTypes particleId, double x, double y, double z, int amount) {
        this(particleId, x, y, z, amount, -1);
    }

    public EntityFX getParticleFromId(World world, double x, double y, double z, int particleId, int blockId) {
        switch (particleId) {
            case 0:
                return new EntityBubbleLightningFX(world, x, y, z);
            case 1:
                return new EntityBlockDustFX(world, x, y, z, 1 - 2 * world.rand.nextFloat(), 0, 1 - 2 * world.rand.nextFloat(), Block.getBlockById(blockId), 0);
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
        blockId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(particleId);
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeInt(amount);
        buf.writeInt(blockId);
    }

    @Override
    public void handleServer(MessageSpawnParticle message, EntityPlayer player) {

    }

    @Override
    public void handleClient(MessageSpawnParticle message, EntityPlayer player) {
        for (int i = 0; i < message.amount; i++)
            ModHandler.get().spawnParticle(message.getParticleFromId(player.worldObj, message.x, message.y, message.z, message.particleId, message.blockId));
    }

    public enum ParticleTypes {
        SHINYLIGHTNING,
        BLOCKBREAK;
    }
}
