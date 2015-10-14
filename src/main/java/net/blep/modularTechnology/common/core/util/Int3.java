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

    public static Int3 ni3(int x, int y, int z)
    {
        return new Int3(x, y, z);
    }

    public static Int3[] ni3a(int... is)
    {
        if (is.length % 3 == 0)
        {
            Int3[] int3s = new Int3[is.length / 3];
            for (int i = 0; i < is.length / 3; i++)
                int3s[i] = ni3(is[i * 3], is[i * 3 + 1], is[i * 3 + 2]);
            return int3s;
        } else return nei3a();
    }

    public static Int3[] nei3a()
    {
        return new Int3[0];
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
