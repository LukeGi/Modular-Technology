package net.blep.modtech.core.proxy;

import java.io.File;

/**
 * Created by Kelan on 07/02/2016.
 */
public interface IProxy
{
    void registerBlocks();

    void registerItems();

    void registerNetwork();

    void registerGuiHandler();

    void registerRenderers();

    void registerConfigs(File file);

    void registerRecipes();

    void registerWorldGenerators();
}
