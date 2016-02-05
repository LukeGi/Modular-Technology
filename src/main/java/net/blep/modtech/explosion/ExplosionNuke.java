package net.blep.modtech.explosion;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Kelan on 28/01/2016.
 */
public class ExplosionNuke extends ExplosionModtech
{
    private boolean setThread = false;
    private ExplosionThread thread;

    public ExplosionNuke(World world, Entity exploder, double x, double y, double z, float size, float radius, float energy)
    {
        super(world, exploder, x, y, z, size, radius, energy);
    }

    @Override
    public void explode(int xCoord, int yCoord, int zCoord)
    {
        thread = new ExpltionThreadNuke(this, xCoord, yCoord, zCoord, radius, energy);
//        try
//        {
//            new ExplotionThreadListener(thread, this.getClass().getDeclaredMethod("postExplode"), this).start();
//        }
//        catch (NoSuchMethodException e)
//        {
//            e.printStackTrace();
//        }

        thread.start();
        setThread = true;
    }

    @Override
    public void postExplode()
    {
        System.out.println("postExplode");
        if (thread == null)
            throw new NullPointerException("How did we even get into an if block where the explosion isn't null, and find a null explosion?!?!?");

        for (Vector3f v : thread.getResult())
        {
            worldObj.getBlock((int) v.x, (int) v.y, (int) v.x).onBlockDestroyedByExplosion(worldObj, (int) v.x, (int) v.y, (int) v.z, this);
        }
    }

    private class ExpltionThreadNuke extends ExplosionThread
    {
        public ExpltionThreadNuke(ExplosionModtech explosoin, int x, int y, int z, float radius, float energy)
        {
            super(explosoin, x, y, z, radius, energy);
        }

        @Override
        public void run()
        {
            for (int i = (int) -radius; i < radius; i++)
            {
                for (int j = (int) -radius; j < radius; j++)
                {
                    for (int k = (int) -radius; k < radius; k++)
                    {
                        int x = this.x + i;
                        int y = this.y + j;
                        int z = this.z + k;

                        Block block = worldObj.getBlock(x, y, z);

                        if (block.getMaterial() != Material.air && (i * i + j * j + k * k) <= (radius * radius))
                        {
                            result.add(new Vector3f(x, y, z));
                        }
                    }
                }
            }

            super.run();
        }
    }
}
