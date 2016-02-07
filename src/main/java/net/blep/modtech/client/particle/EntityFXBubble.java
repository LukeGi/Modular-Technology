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
        this.setRBGColorF(0.5F,0.5F,0.5F); //makes them grey... so if i do this see =D
        this.particleGravity = 0.123F;
        this.particleMaxAge = 15;
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }
        this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
        this.motionY = (1 - rand.nextFloat() * 2) / 10;
        this.motionZ = (1 - rand.nextFloat() * 2) / 10;
        this.motionX = (1 - rand.nextFloat() * 2) / 10;
//        this.setRBGColorF((float) motionX * rand.nextFloat() * 5,(float) motionY * rand.nextFloat() * 10,(float) motionZ * rand.nextFloat() * 15);
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.particleScale = particleMaxAge/particleAge;
    }
}
