package net.blep.modularTechnology.common.core.util;

/**
 * @author TheEpicTekkit
 */
public class Int2
{
    protected int[] values = {0, 0, 0, 0};

    public Int2(int x, int y)
    {
        values[0] = x;
        values[1] = y;
    }

    public int getX()
    {
        return values[0];
    }

    public int getY()
    {
        return values[1];
    }

    public void setX(int x)
    {
        values[0] = x;
    }

    public void setY(int y)
    {
        values[1] = y;
    }

    public boolean equals(Object o)
    {
        if (!(o instanceof Int2)) return false;
        Int2 i2 = (Int2) o;
        for (int i = 0; i < i2.values.length; i++)
            if (i2.values[i] != values[i]) return false;
        return true;
    }
}
