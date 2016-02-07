package net.blep.modtech.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class EntityFXBubble extends EntityFX {
    public EntityFXBubble(World world, double x, double y, double z, double xMotion, double yMotion, double zMotion) {
        super(world, x, y, z, xMotion, yMotion, zMotion);
        this.setParticleTextureIndex(0);
        this.setRBGColorF(0.5F,0.5F,0.5F);
        this.particleGravity = 0.123F;
        this.particleMaxAge = 15;
    }

    public EntityFXBubble(World world, double x, double y, double z) {
        this(world, x, y, z, 0,0,0);
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        this.motionY -= 0.04D * (double)this.particleGravity;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}
