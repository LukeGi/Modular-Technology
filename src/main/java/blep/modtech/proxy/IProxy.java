package blep.modtech.proxy;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.io.File;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public interface IProxy
{
    /**
     * Register Configs
     */
    void registerConfigs(File configFile);

    /**
     * Register Network
     */
    void registerNetwork();

    /**
     * Registers Blocks
     */
    void registerBlocks();

    /**
     * Register Items
     */
    void registerItems();

    /**
     * Register Creative Tab
     */
    void registerCreativeTab();

    /**
     * Register what is needed for entity FX
     */
    void registerEntityFXStuff();

    /**
     * Register Gui Handler
     */
    void registerGuiHandler();

    /**
     * Spawn Particle n stuff
     */
    void spawnParticle(EnumParticleTypes type, World world, BlockPos pos, double mx, double my, double mz, int[] extradata);
}
