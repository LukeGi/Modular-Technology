package blep.modtech.machine.generator;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class TileEntityGeneratorBase extends TileEntity implements ITickable, IEnergyProvider
{
    private EnergyStorage energy = new EnergyStorage(1000000);

    @Override
    public void update()
    {
        energy.receiveEnergy(30, false);
    }

    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate)
    {
        return energy.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(EnumFacing from)
    {
        return energy.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from)
    {
        return energy.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(EnumFacing from)
    {
        return true;
    }
}
