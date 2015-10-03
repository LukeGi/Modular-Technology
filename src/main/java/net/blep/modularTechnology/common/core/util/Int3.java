package net.blep.modularTechnology.common.core.util;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class Int3 extends Int2
{
    public Int3(int x, int y, int z)
    {
        super(x, y);
        ints[2] = z;
    }

    public int getZ()
    {
        return ints[2];
    }

    public void setZ(int z)
    {
        this.ints[2] = z;
    }
}
