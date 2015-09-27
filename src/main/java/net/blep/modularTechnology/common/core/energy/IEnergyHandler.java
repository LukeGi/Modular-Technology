package net.blep.modularTechnology.common.core.energy;

/**
 * @author Kelan
 */
public interface IEnergyHandler
{
    /**
     * The energy network that this energy handler is connected to
     * @return The energy network with the most available network.
     */
    EnergyNetwork getEnergyNetwork();

    /**
     * Used to determine if this machine is loosing power, gaining power, or neither.
     * @return the power status.
     */
    EnumPowerStatus getPowerStatus();
}
