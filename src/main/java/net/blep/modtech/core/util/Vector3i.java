package net.blep.modtech.core.util;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class Vector3i implements Comparable<Vector3i> {
    public int x, y, z;

    public Vector3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector3i vector3i = (Vector3i) o;

        if (x != vector3i.x) return false;
        if (y != vector3i.y) return false;
        return z == vector3i.z;

    }

    public int hashCode() {
        return (int) Math.pow(Math.pow(Math.pow(31, x), y), z);
    }

    @Override
    public int compareTo(Vector3i o) {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }
}
