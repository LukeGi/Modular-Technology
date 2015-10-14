package net.blep.modularTechnology.common.core.util;

/**
 * @author TheEpicTekkit
 */
public class Int2 implements Comparable<Int2>
{
    protected int[] ints = {0, 0, 0, 0};

    public Int2(int x, int y)
    {
        ints[0] = x;
        ints[1] = y;
    }

    public int getX()
    {
        return ints[0];
    }

    public int getY()
    {
        return ints[1];
    }

    public void setX(int x)
    {
        ints[0] = x;
    }

    public void setY(int y)
    {
        ints[1] = y;
    }

    public boolean equals(Object o)
    {
        if (!(o instanceof Int2)) return false;
        Int2 i2 = (Int2) o;
        for (int i = 0; i < 4; i++)
            if (i2.ints[i] != ints[i]) return false;
        return true;
    }

    public int hashCode()
    {
        int result = 31;
        for (int i : ints)
            if (i != 0) result ^= i;
        return result;
    }

    @Override
    public int compareTo(Int2 o)
    {
        return ((Integer)hashCode()).compareTo(o.hashCode());
    }
}
