package com.blep.modularTechnology.core.common.util;

/**
 * @author Kelan
 */
public abstract class ShiftClickTarget
{

    private ShiftClickTarget() {}

    /**
     * Tries to merge the stack with one of the Slots between from and to (inclusive).
     * If from is greater than to, the range will be iterated in reverse (to} to from).
     *
     * @param from first slot
     * @param to last slot
     * @return a ShiftClickTarget
     */
    public static ShiftClickTarget range(int from, int to)
    {
        assert from >= 0 && to >= 0 : "Both 'from' and 'to' cannot be negative";

        if (to == from)
            return of(to);
        else if (to > from)
            return new RevRange(from, to);
        else
            return new Range(from, to);
    }

    /**
     * Discard the shift-click, don't move the ItemStack at all.
     *
     * @return a ShiftClickTarget
     */
    public static ShiftClickTarget none()
    {
        return None.INSTANCE;
    }

    /**
     * Use the default behavior.
     *
     * @return a ShiftClickTarget
     */
    public static ShiftClickTarget standard()
    {
        return Standard.INSTANCE;
    }

    /**
     * Tries to merge the stack with the single given slot.
     *
     * @param slot the slot
     * @return a ShiftClickTarget
     */
    public static ShiftClickTarget of(int slot)
    {
        return new One(slot);
    }

    /**
     * Tries to merge the stack with the given slots in the order provided.
     *
     * @param slots the slots
     * @return a ShiftClickTarget
     */
    public static ShiftClickTarget of(int... slots)
    {
        if (slots.length <= 0)
            return none();
        else if (slots.length == 1)
        return of(slots[0]);

        return new ForArray(slots);
    }

    public abstract boolean hasNext();

    public abstract int next();

    public abstract void reset();

    public boolean isStandard()
    {
        return false;
    }

    public boolean isNone()
    {
        return false;
    }

    private static final class Range extends ShiftClickTarget
    {

        private final int from;
        private final int to;
        private int next;

        private Range(int from, int to)
        {
            this.from = from;
            this.to = to;

            this.next = from;
        }

        public boolean hasNext()
        {
            return next <= to;
        }

        public int next()
        {
            return next++;
        }

        public void reset()
        {
            next = from;
        }
    }

    private static final class RevRange extends ShiftClickTarget
    {
        private final int from;
        private final int to;
        private int next;

        private RevRange(int from, int to)
        {
            this.from = from;
            this.to = to;

            this.next = to;
        }

        public boolean hasNext()
        {
            return next >= from;
        }

        public int next()
        {
            return next--;
        }

        public void reset()
        {
            next = to;
        }

    }

    private static final class ForArray extends ShiftClickTarget
    {
        private final int[] slots;
        private int curr;

        private ForArray(int[] slots)
        {
            this.slots = slots;
        }

        public boolean hasNext()
        {
            return curr != slots.length;
        }

        public int next()
        {
            return slots[curr++];
        }

        public void reset()
        {
            curr = 0;
        }
    }

    private static final class One extends ShiftClickTarget
    {
        private int slot;

        private One(int slot)
        {
            this.slot = slot;
        }

        public boolean hasNext()
        {
            return (slot & (1 << 31)) == 0;
        }

        public int next()
        {
            int ret = slot;
            slot = ret | (1 << 31);
            return ret;
        }

        public void reset()
        {
            slot &= ~(1 << 31);
        }
    }

    private static final class None extends ShiftClickTarget
    {
        static final None INSTANCE = new None();

        private None() {}

        public boolean hasNext()
        {
            return false;
        }

        public int next()
        {
            throw new AssertionError();
        }

        public void reset() {}

        public boolean isNone()
        {
            return true;
        }
    }

    private static final class Standard extends ShiftClickTarget
    {
        static final Standard INSTANCE = new Standard();

        private Standard() {}

        public boolean hasNext()
        {
            throw new AssertionError();
        }

        public int next()
        {
            throw new AssertionError();
        }

        public void reset()
        {
            throw new AssertionError();
        }

        public boolean isStandard()
        {
            return true;
        }
    }

}
