package com.blep.modularTechnology.core.common.network.packets;

import io.netty.buffer.ByteBuf;
import com.blep.modularTechnology.core.common.network.PacketBase;
import net.minecraft.entity.Entity;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class MessageTeleportEntity //extends PacketBase<MessageTeleportEntity>
{
//    private int x, y, z, entityID;
//
//    public MessageTeleportEntity()
//    {
//    }
//
//    public MessageTeleportEntity(int x, int y, int z, Entity entity)
//    {
//        this.x = x;
//        this.y = y;
//        this.z = z;
//        this.entityID = entity.getEntityId();
//    }
//
//    @Override
//    public void handleServer(MessageTeleportEntity message, Object... o)
//    {
//        if (!(o[0] instanceof Entity)) return;
//        Entity e = (Entity) o[0];
//        Entity e1 = e.worldObj.getEntityByID(message.entityID);
//        e1.setPosition(x + 0.5, y + 0.5, z + 0.5);
//
//    }
//
//    @Override
//    public void handleClient(MessageTeleportEntity message, Object... o)
//    {
//
//    }
//
//    @Override
//    public void fromBytes(ByteBuf buf)
//    {
//        entityID = buf.readInt();
//        x = buf.readInt();
//        y = buf.readInt();
//        z = buf.readInt();
//    }
//
//    @Override
//    public void toBytes(ByteBuf buf)
//    {
//        buf.writeInt(entityID);
//        buf.writeInt(x);
//        buf.writeInt(y);
//        buf.writeInt(z);
//    }
}
