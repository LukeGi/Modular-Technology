package blep.modtech.proxy;

import blep.modtech.block.ModtechBlocks;
import blep.modtech.config.ConfigHandler;

import java.io.File;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public abstract class CommonProxy implements IProxy
{
    @Override
    public void registerBlocks()
    {
        ModtechBlocks.initAllBlocks();
    }

    @Override
    public void registerItems()
    {

    }

    @Override
    public void registerConfigs(File configFile)
    {
        ConfigHandler.init(configFile);
    }
}
