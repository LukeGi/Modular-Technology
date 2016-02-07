package net.blep.modtech.core.networking;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Kelan on 07/02/2016.
 */
public interface IModtechPacket<REQ extends IMessage>
{
    void handleServer(REQ message, EntityPlayer player);

    void handleClient(REQ message, EntityPlayer player);
}
