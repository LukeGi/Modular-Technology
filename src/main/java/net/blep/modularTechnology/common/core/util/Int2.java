package net.blep.modularTechnology.common.core.util;

/**
 * @author TheEpicTekkit
 */
public class Int2
{
    private int x;
    private int y;

    public Int2(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public boolean equals(Object o)
    {
        if (!(o instanceof Int2)) return false;
        Int2 i2 = (Int2) o;
        if (i2.getX() != getX()) return false;
        if (i2.getY() != getY()) return false;
        return true;
    }
}
