package net.blep.modularTechnology.common.core.util;

/**
 * @author Kelan
 */
public interface IRedstoneReciever
{
    Int3[] getAllInputs();

    void removeInput(Int3 input);
}
