package blep.modtech.machine.cable;

import blep.modtech.util.LogHelper;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class TileEntityPowerCable extends TileEntity implements IEnergyProvider, IEnergyReceiver, ITickable
{
    List<BlockPos> children;
    BlockPos master;
    private boolean isMaster, firstrun = true;
    private EnergyStorage energyStorage;

    public TileEntityPowerCable(int powerTransfer)
    {
        energyStorage = new EnergyStorage(powerTransfer);
    }

    @Override
    public void update()
    {
        if (firstrun)
        {
            firstrun = false;
            initialise();
        }
        for (EnumFacing facing : EnumFacing.VALUES)
        {
            TileEntity tile = worldObj.getTileEntity(pos.add(facing.getDirectionVec()));
            if (tile instanceof IEnergyProvider)
                energyStorage.modifyEnergyStored(((IEnergyProvider) tile).extractEnergy(facing.getOpposite(), energyStorage.getMaxEnergyStored() - energyStorage.getEnergyStored(), false));
            if (tile instanceof IEnergyReceiver && !(tile instanceof TileEntityPowerCable))
                energyStorage.modifyEnergyStored(-((IEnergyReceiver) tile).receiveEnergy(facing.getOpposite(), energyStorage.getEnergyStored(), false));
        }
    }

    private void initialise()
    {
        if (master == null)
        {
            List<TileEntityPowerCable> connectedStorages = new ArrayList<>();
            Stack<TileEntityPowerCable> traversingStorages = new Stack<>();
            TileEntityPowerCable master = this;
            traversingStorages.add(this);
            connectedStorages.add(this);
            while (!traversingStorages.isEmpty())
            {
                TileEntityPowerCable storage = traversingStorages.pop();
                if (storage.isMaster())
                {
                    master = storage;
                }
                for (EnumFacing b : EnumFacing.values())
                {
                    TileEntity te = worldObj.getTileEntity(new BlockPos(storage.pos).add(b.getDirectionVec()));
                    if (te instanceof TileEntityPowerCable && !connectedStorages.contains(te))
                    {
                        connectedStorages.add((TileEntityPowerCable) te);
                        traversingStorages.add((TileEntityPowerCable) te);
                    }
                }
            }
            LogHelper.info("Setting master to " + master.pos.toString().substring(8) + " for " + connectedStorages.size() + " blocks");
            for (TileEntityPowerCable storage : connectedStorages)
            {
                storage.setMaster(master, connectedStorages.size());
            }
        }

        for (EnumFacing direction : EnumFacing.VALUES)
        {
            TileEntity tile = worldObj.getTileEntity(pos.add(direction.getDirectionVec()));
            if (tile instanceof TileEntityPowerCable){
                if (((TileEntityPowerCable) tile).isMaster())
                    master = pos.add(direction.getDirectionVec());
                else master = ((TileEntityPowerCable) tile).getMaster();
            }
        }
    }

    private void setMaster(TileEntityPowerCable master, int size)
    {
        this.master = master.getPos();
        isMaster = master == this;
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

    public boolean isMaster()
    {
        return isMaster;
    }

    public BlockPos getMaster()
    {
        return master;
    }
}
