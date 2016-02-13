package net.blep.modtech.client.particle;

import net.blep.modtech.core.networking.NetworkManagerModtech;
import net.blep.modtech.core.networking.packets.MessageSpawnParticle;
import net.blep.modtech.core.util.Vector3d;
import net.minecraft.world.World;


/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ParticleManager {
    public static void spawnParticlez(MessageSpawnParticle.ParticleTypes type, World world, Vector3d pos, int amount) {
        NetworkManagerModtech.networkManager.sendToDimension(new MessageSpawnParticle(type, pos.x, pos.y, pos.z, amount), world.provider.dimensionId);
    }
}
