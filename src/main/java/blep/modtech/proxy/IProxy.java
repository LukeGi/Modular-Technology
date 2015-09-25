package blep.modtech.proxy;

import java.io.File;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public interface IProxy
{
    /**
     * Registers Blocks
     */
    void registerBlocks();

    /**
     * Register Items
     */
    void registerItems();

    /**
     * Register Configs
     */
    void registerConfigs(File configFile);
}