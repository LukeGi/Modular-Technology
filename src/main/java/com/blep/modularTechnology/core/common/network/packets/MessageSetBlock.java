package com.blep.modularTechnology.core.common.network.packets;

import io.netty.buffer.ByteBuf;
import com.blep.modularTechnology.core.common.network.PacketBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class MessageSetBlock //extends PacketBase<MessageSetBlock>
{
//    public int blockID;
//    public int x;
//    public int y;
//    public int z;
//
//    public MessageSetBlock() {}
//
//    public MessageSetBlock(Block block, int x, int y, int z)
//    {
//        this.x = x;
//        this.y = y;
//        this.z = z;
//        this.blockID = Block.getIdFromBlock(block);
//    }
//
//    @Override
//    public void handleServer(MessageSetBlock message, Object... o)
//    {
//        if (!(o[0] instanceof EntityPlayer))
//            return;
//
//        ((EntityPlayer) o[0]).worldObj.setBlock(message.x, message.y, message.z, Block.getBlockById(message.blockID));
//    }
//
//    @Override
//    public void handleClient(MessageSetBlock message, Object... o)
//    {
//
//    }
//
//    @Override
//    public void fromBytes(ByteBuf buf)
//    {
//        this.blockID = buf.readInt();
//        this.x = buf.readInt();
//        this.y = buf.readInt();
//        this.z = buf.readInt();
//    }
//
//    @Override
//    public void toBytes(ByteBuf buf)
//    {
//        buf.writeInt(this.blockID);
//        buf.writeInt(this.x);
//        buf.writeInt(this.y);
//        buf.writeInt(this.z);
//    }
}
