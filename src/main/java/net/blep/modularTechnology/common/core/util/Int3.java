package net.blep.modularTechnology.common.core.util;

/**
 * @author Kelan
 */
public class Int3 extends Int2
{
    public Int3(int x, int y, int z)
    {
        super(x, y);
        values[2] = z;
    }

    public void setZ(int z)
    {
        values[2] = z;
    }

    public int getZ()
    {
        return values[2];
    }
}
