package net.blep.modtech.core.proxy;

import net.minecraft.client.particle.EntityFX;

import java.io.File;

/**
 * Created by Kelan on 07/02/2016.
 */
public interface IProxy
{
    void registerBlocks();

    void registerItems();

    void registerNetwork();

    void registerRenderers();

    void registerConfigs(File file);

    void registerRecipes();

    void registerWorldGenerators();

    void spawnParticle(EntityFX particle);
}
