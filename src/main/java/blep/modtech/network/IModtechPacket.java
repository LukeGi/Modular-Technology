package blep.modtech.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Created by Kelan on 07/02/2016.
 */
public interface IModtechPacket<REQ extends IMessage>
{
    void handleServer(REQ message, EntityPlayer player);

    void handleClient(REQ message, EntityPlayer player);
}
