package com.blep.modularTechnology.core.common.energy;

/**
 * @author Kelan
 */
public interface IEnergyProvider extends IEnergyHandler
{
    /**
     * This is the rate at which this energy handler can send energy over
     * the network.
     * @return The output energy rate.
     */
    double getEnergySendRate();

    /**
     * The total amount of energy this energy handler has to offer to its
     * network.
     * @return The available energy.
     */
    double getEnergyAvailableToSend();

    /**
     * This is the opposite of requestEnergy(double amount) in IEnergyReciever.
     * It is a simulation of sending energy to the network, rather than recieving it.
     *
     * @param amount The amount of energy that is being requested by the network.
     * @return The amount of energy determined by the simulation to send to the network.
     */
    double onEnergyRequested(double amount);
}
