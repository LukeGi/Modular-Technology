package modulartechnology.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */

public class TileMagic extends TileEntity
{
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        writeCustomNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 743, nbttagcompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        super.onDataPacket(net, packet);
        readCustomNBT(packet.func_148857_g());
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        writeCustomNBT(nbt);
    }

    public void writeCustomNBT(NBTTagCompound nbt) {
        // NO-OP
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        readCustomNBT(nbt);
    }

    public void readCustomNBT(NBTTagCompound nbt) {
        // NO-OP
    }
}