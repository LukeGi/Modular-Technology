package net.blep.modularTechnology.common.core.energy;

/**
 * @author Kelan
 */
public interface IEnergyReciever extends IEnergyHandler
{

    /**
     * The energy buffer capacity of this energy handler.
     * @return The buffer capacity.
     */
    double getEnergyCapacity();

    /**
     * The energy currently stored in this energy handlers buffer.
     * @return The energy currently stored in the buffer.
     */
    double getEnergyStored();

    /**
     * The maximum energy per tick that this energy handler can recieve from the network.
     * @return The energy recieve rate.
     */
    double getEnergyRecieveRate();

    /**
     * This method is called when this energy handler wants to request energy from the network.
     * It should simulate what will happen if energy is requested and act accordingly. The simulation
     * should happen to avoid energy dupes.
     *
     * @param amount The desired amount of energy to pull from the network.
     * @return The actual amount of energy that can be recieved (determined by the simulation).
     */
    double requestEnergy(double amount);
}
