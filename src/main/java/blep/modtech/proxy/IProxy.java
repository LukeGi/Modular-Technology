package blep.modtech.proxy;

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
     * Registers Blocks
     */
    void registerBlocks();

    /**
     * Register Items
     */
    void registerItems();

    /**
     * Register Block Renders
     */
    void registerBlockRenders();

    /**
     * Register Item Renders
     */
    void registerItemRenders();
}
