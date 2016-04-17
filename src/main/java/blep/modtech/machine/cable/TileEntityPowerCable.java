package blep.modtech.machine.cable;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class TileEntityPowerCable extends TileEntity implements IEnergyProvider, IEnergyReceiver, ITickable
{
    private EnergyStorage energyStorage;

    public TileEntityPowerCable(int powerTransfer)
    {
        energyStorage = new EnergyStorage(powerTransfer);
    }

    @Override
    public void update()
    {
        for (EnumFacing dir : EnumFacing.VALUES)
        {
            BlockPos searchPos = pos.add(dir.getDirectionVec());
            TileEntity tileNextdoor = worldObj.getTileEntity(searchPos);
            if (tileNextdoor instanceof TileEntityPowerCable)
                balanceWith((TileEntityPowerCable)tileNextdoor, dir);
            else if (tileNextdoor instanceof IEnergyProvider)
                takePower((IEnergyProvider)tileNextdoor, dir);
            else if (tileNextdoor instanceof IEnergyReceiver)
                givePower((IEnergyReceiver)tileNextdoor, dir);
        }
    }

    private void givePower(IEnergyReceiver tileNextdoor, EnumFacing dir)
    {
        energyStorage.modifyEnergyStored(-tileNextdoor.receiveEnergy(dir.getOpposite(), energyStorage.getEnergyStored(), false));
    }

    private void takePower(IEnergyProvider tileNextdoor, EnumFacing dir)
    {
        energyStorage.modifyEnergyStored(tileNextdoor.extractEnergy(dir.getOpposite(), energyStorage.getMaxEnergyStored() - energyStorage.getEnergyStored(), false));
    }

    private void balanceWith(TileEntityPowerCable that, EnumFacing dir)
    {
        int thisEnergy = getEnergyStored(dir);
        int thatEnergy = that.getEnergyStored(dir.getOpposite());
        if (thisEnergy < thatEnergy)
            energyStorage.modifyEnergyStored(-that.receiveEnergy(dir.getOpposite(), (thisEnergy - thatEnergy) / 2, false));
        if (thatEnergy < thisEnergy)
            that.balanceWith(this, dir.getOpposite());
    }

    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate)
    {
        return energyStorage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate)
    {
        return energyStorage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int getEnergyStored(EnumFacing from)
    {
        return energyStorage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from)
    {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(EnumFacing from)
    {
        return true;
    }
}
