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
}
