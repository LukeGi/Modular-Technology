package net.blep.modularTechnology.common.core.energy;

/**
 * @author Kelan
 */
public interface IEnergyStore extends IEnergyProvider, IEnergyReciever
{
    /**
     * This is the amount of energy that is lost by this energy store every tick.
     * For example, 0.05D would mean that every second, one RF is lost to entropy.
     *
     * @return The amount of energy that is lost per tick.
     */
    double getEnergyDecay();
}
