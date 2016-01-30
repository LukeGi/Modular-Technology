package com.blep.modularTechnology.core.common.network.packets;

import io.netty.buffer.ByteBuf;
import com.blep.modularTechnology.core.common.network.PacketBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author TheEpicTekkit
 */
public class MessageMoveEntity //extends PacketBase<MessageMoveEntity>
{
//    protected float vx, vy, vz;
//    protected int entityID;
//
//    public MessageMoveEntity()
//    {
//    }
//
//    public MessageMoveEntity(Entity entity, float vx, float vy, float vz)
//    {
//        this.entityID = entity.getEntityId();
//        this.vx = vx;
//        this.vy = vy;
//        this.vz = vz;
//    }
//
//
//    @Override
//    public void handleServer(MessageMoveEntity message, Object... o)
//    {
//        if (!(o[0] instanceof EntityPlayer))
//            return;
//
//        EntityPlayer player = (EntityPlayer) o[0];
//        Entity entity = player.worldObj.getEntityByID(message.entityID);
//
//        if (entity != null)
//        {
//            entity.isCollided = false;
//            entity.isCollidedVertically = false;
//            entity.isCollidedHorizontally = false;
//            entity.onGround = false;
//            if (entity instanceof EntityLivingBase) ((EntityLivingBase) entity).setJumping(true);
//
//            entity.motionX = message.vx;
//            entity.motionY = message.vy;
//            entity.motionZ = message.vz;
//        }
//    }
//
//    @Override
//    public void handleClient(MessageMoveEntity message, Object... o)
//    {
//
//    }
//
//    @Override
//    public void fromBytes(ByteBuf buf)
//    {
//        buf.writeFloat(vx);
//        buf.writeFloat(vy);
//        buf.writeFloat(vz);
//        buf.writeInt(entityID);
//    }
//
//    @Override
//    public void toBytes(ByteBuf buf)
//    {
//        vx = buf.readFloat();
//        vy = buf.readFloat();
//        vz = buf.readFloat();
//        entityID = buf.readInt();
//    }
}
