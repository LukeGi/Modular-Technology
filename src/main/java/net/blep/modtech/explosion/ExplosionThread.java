package net.blep.modtech.explosion;

import org.lwjgl.util.vector.Vector3f;

import java.util.HashSet;

/**
 * Created by Kelan on 28/01/2016.
 */
public class ExplosionThread extends Thread
{
    public boolean doneExplode = false;
    private ExplosionModtech explosion;
    protected float radius;
    protected float energy;
    protected int x;
    protected int y;
    protected int z;

    protected HashSet<Vector3f> result = new HashSet<>();

    public ExplosionThread(ExplosionModtech explosion, int x, int y, int z, float radius, float energy)
    {
        this.explosion = explosion;
        this.radius = radius;
        this.energy = energy;
    }

    @Override
    public void run()
    {
        doneExplode = true;
        explosion.postExplode();
        System.out.println("Finished explosion.");
        interrupt();
    }

    public HashSet<Vector3f> getResult()
    {
        return result;
    }
}
