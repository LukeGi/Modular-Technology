package net.blep.modtech.client.particle;

import net.blep.modtech.core.networking.NetworkManagerModtech;
import net.blep.modtech.core.networking.packets.MessageSpawnEntity;
import net.blep.modtech.core.networking.packets.MessageSpawnParticle;
import net.blep.modtech.core.util.Vector3d;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class EntityBubbleLightningFX extends EntityFX {
    int baseTextureIndex = 160;
    float d = .25f;
    double ox, oy, oz;

    public EntityBubbleLightningFX(World world, double x, double y, double z, double xMotion, double yMotion, double zMotion) {
        super(world, x, y + 0.25f, z, xMotion, 100, zMotion);
        particleMaxAge = (1000 + (int) (210 * rand.nextFloat())) / 10;
        particleGravity = 10;
        float c = rand.nextFloat();
        setRBGColorF(1, 0, 1);
        this.particleScale = 3;
        motionX = 0;
        motionY = rand.nextFloat() / 2;
        motionZ = 0;
        ox = x;
        oy = y;
        oz = z;
    }

    public EntityBubbleLightningFX(World world, double x, double y, double z) {
        this(world, x, y, z, 0, 0, 0);
    }

    @Override
    public void onUpdate() {
        this.setParticleTextureIndex(/*this.baseTextureIndex + (7 - (particleAge % 10 + 1) * 8 / 10)*/32);

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (particleAge < particleMaxAge * 8 / 10) {
            d *= -1;
            if (rand.nextBoolean())
                this.moveEntity(d, 0, 0);
            if (rand.nextBoolean())
                this.moveEntity(0, d * rand.nextInt(5), 0);
            if (rand.nextBoolean())
                this.moveEntity(0, 0, d);
        } else {
            this.motionX = 0;
            this.motionZ = 0;
            this.motionY -= 0.04D * (double) this.particleGravity;
            this.motionY *= 0.9800000190734863D;
        }

        this.motionX = (0.5 - rand.nextFloat()) - (posX - ox);
        this.motionZ = (1 - motionX) - (posZ - oz);

        if (this.particleAge++ >= this.particleMaxAge) {
            if (rand.nextBoolean())
                ParticleManager.spawnParticlez(MessageSpawnParticle.ParticleTypes.SHINYLIGHTNING, this.worldObj, new Vector3d(ox, oy + 0.5, oz), 2);
            else {
                NetworkManagerModtech.networkManager.sendToServer(new MessageSpawnEntity(MessageSpawnEntity.EntityType.LIGHTNING, prevPosX, prevPosY, prevPosZ));
                NetworkManagerModtech.networkManager.sendToDimension(new MessageSpawnEntity(MessageSpawnEntity.EntityType.LIGHTNING, prevPosX, prevPosY, prevPosZ), worldObj.provider.dimensionId);
            }
            this.setDead();
        }
        this.moveEntity(motionX, motionY, motionZ);
    }
}
