package net.blep.modtech.explosion;

import net.minecraft.entity.Entity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

/**
 * Created by Kelan on 28/01/2016.
 */
public abstract class ExplosionModtech extends Explosion
{
    protected float radius;
    protected float energy;
    protected World worldObj;

    public ExplosionModtech(World world, Entity exploder, double x, double y, double z, float size, float radius, float energy)
    {
        super(world, exploder, x, y, z, size);
        this.worldObj = world;
        this.radius = radius;
        this.energy = energy;
    }

    @Override
    public void doExplosionA() {}

    @Override
    public void doExplosionB(boolean bool) {}

    public abstract void explode(int xCoord, int yCoord, int zCoord);

    public abstract void postExplode();
}
