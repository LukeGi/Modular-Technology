package net.blep.modularTechnology.common.core.pathfinding;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class PFVec3
{
    public int x, y, z, d;

    public PFVec3(int x, int y, int z, int d)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.d = d;
    }

    public PFVec3(int x, int y, int z)
    {
        this(x,y,z,0);
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getZ()
    {
        return z;
    }

    public void setZ(int z)
    {
        this.z = z;
    }

    public int getD()
    {
        return d;
    }

    public void setD(int d)
    {
        this.d = d;
    }
}
