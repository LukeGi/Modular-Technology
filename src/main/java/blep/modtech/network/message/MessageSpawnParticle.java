package blep.modtech.network.message;

import blep.modtech.network.ModtechMessageBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;

import javax.annotation.Nullable;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class MessageSpawnParticle extends ModtechMessageBase<MessageSpawnParticle>
{
    private double x, y, z, ox, oy, oz;
    private EnumParticleTypes type;
    private int[] extraInfo;

    public MessageSpawnParticle()
    {
    }

    public MessageSpawnParticle(double x, double y, double z, double ox, double oy, double oz, EnumParticleTypes type, @Nullable int[] extraInfo)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.ox = ox;
        this.oy = oy;
        this.oz = oz;
        this.type = type;
        this.extraInfo = extraInfo == null ? new int[0] : extraInfo;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeDouble(ox);
        buf.writeDouble(oy);
        buf.writeDouble(oz);
        buf.writeInt(type.getParticleID());
        buf.writeInt(extraInfo.length);
        for (int i : extraInfo)
            buf.writeInt(i);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
        ox = buf.readDouble();
        oy = buf.readDouble();
        oz = buf.readDouble();
        type = EnumParticleTypes.getParticleFromId(buf.readInt());
        int size = buf.readInt();
        extraInfo = new int[size];
        for (int i = 0; i < size; i++)
            extraInfo[i] = buf.readInt();
    }

    @Override
    public void handleClient(MessageSpawnParticle message, EntityPlayer player)
    {
        player.worldObj.spawnParticle(message.type, message.x, message.y, message.z, message.ox, message.oy, message.oz, message.extraInfo);
    }

    @Override
    public void handleServer(MessageSpawnParticle message, EntityPlayer player)
    {

    }
}
