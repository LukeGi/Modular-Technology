package blep.modtech.machine.generator;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public abstract class TileEntityGeneratorBase extends TileEntity implements ITickable, IEnergyProvider
{
    protected EnergyStorage energy;

    protected TileEntityGeneratorBase(int energy)
    {
        this.energy = new EnergyStorage(energy);
    }

    protected TileEntityGeneratorBase(int energy, int maxTransfer)
    {
        this.energy = new EnergyStorage(energy, maxTransfer);
    }

    protected TileEntityGeneratorBase(int energy, int maxRecieve, int maxExtract)
    {
        this.energy = new EnergyStorage(energy, maxRecieve, maxExtract);
    }

    @Override
    public void update()
    {
        if (shouldGenerate()) generate();
        if (shouldAttemptPushing()) push();
    }

    protected abstract boolean shouldAttemptPushing();

    protected void push()
    {
        TileEntity genericTile;
        IEnergyReceiver rec;
        for (EnumFacing facing : EnumFacing.values())
        {
            genericTile = worldObj.getTileEntity(pos.add(facing.getDirectionVec()));
            if (genericTile instanceof IEnergyReceiver)
            {
                rec = (IEnergyReceiver) genericTile;
                int transfer = energy.extractEnergy(energy.getMaxExtract(), false);
                transfer -= rec.receiveEnergy(EnumFacing.DOWN, transfer, false);
                energy.receiveEnergy(transfer, false);
            }
        }
    }

    protected abstract boolean shouldGenerate();

    public abstract void generate();

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
