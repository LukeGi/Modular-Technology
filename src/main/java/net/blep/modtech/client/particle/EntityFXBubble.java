package net.blep.modtech.client.particle;

import net.blep.modtech.core.networking.NetworkManagerModtech;
import net.blep.modtech.core.networking.packets.MessageSpawnEntity;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class EntityFXBubble extends EntityFX {
    int baseTextureIndex = 160;
    double ox, oy, oz;

    public EntityFXBubble(World world, double x, double y, double z, double xMotion, double yMotion, double zMotion) {
        super(world, x, y, z, xMotion, 100, zMotion);
        this.particleMaxAge = 210 + (int) (55 * world.rand.nextFloat());
        this.particleGravity = 10;
        this.motionX = 1 - rand.nextFloat() * 2;
        this.motionZ = 1 - rand.nextFloat() * 2;
        ox = x;
        oy = y;
        oz = z;
    }

    public EntityFXBubble(World world, double x, double y, double z) {
        this(world, x, y, z, 0, 0, 0);
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge) {
            NetworkManagerModtech.networkManager.sendToServer(new MessageSpawnEntity(MessageSpawnEntity.EntityType.LIGHTNING, prevPosX, prevPosY, prevPosZ));
            NetworkManagerModtech.networkManager.sendToDimension(new MessageSpawnEntity(MessageSpawnEntity.EntityType.LIGHTNING, prevPosX, prevPosY, prevPosZ), worldObj.provider.dimensionId);
            this.setDead();
        }
//TODO: Make particles go up before they drop!
        int doooe = particleAge % 10 + 1;
        this.setParticleTextureIndex(this.baseTextureIndex + (7 - doooe * 8 / 10));
        this.setRBGColorF((float) Math.tan(360 / doooe * 2), (float) Math.cos(360 / doooe * 3), (float) Math.sin(360 / doooe * 5));
        this.motionY -= 0.04D * (double)this.particleGravity;
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);

        if (this.onGround) {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}
