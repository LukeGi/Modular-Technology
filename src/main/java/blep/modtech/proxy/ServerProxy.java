package blep.modtech.proxy;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ServerProxy extends CommonProxy
{
    @Override
    public void spawnParticle(EnumParticleTypes type, World world, BlockPos pos, double mx, double my, double mz, int[] extradata)
    {
        /* no-op */
    }
}
