package modulartechnology.energy;

/**
 * @author Kelan
 */
public interface IEnergyConductor extends IEnergyHandler
{
    /**
     * The energy that can be transferred through this energy handler every tick.
     *
     * @return the transfer rate.
     */
    double getEnergyTransferRate();

    /**
     * The amount of energy that is lost for the given number of blocks it
     * was transferred over.
     *
     * @param blocks The number of blocks the energy has moved.
     * @return The total lost energy.
     */
    double getEnergyLossForDistance(int blocks);
}
