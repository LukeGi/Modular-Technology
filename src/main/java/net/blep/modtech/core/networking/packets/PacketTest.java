package net.blep.modtech.core.networking.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.blep.modtech.core.networking.PacketModtechBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

/**
 * Created by Kelan on 07/02/2016.
 */
public class PacketTest extends PacketModtechBase<PacketTest>
{
    private String test;

    public PacketTest(String test)
    {
        this.test = test;
    }

    public PacketTest() {}

    public void toBytes(ByteBuf buffer)
    {
        ByteBufUtils.writeUTF8String(buffer, test);
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        test = ByteBufUtils.readUTF8String(buffer);
    }

    @Override
    public void handleServer(PacketTest message, EntityPlayer player)
    {

    }

    @Override
    public void handleClient(PacketTest message, EntityPlayer player)
    {
        player.addChatComponentMessage(new ChatComponentTranslation(message.test));
    }
}
