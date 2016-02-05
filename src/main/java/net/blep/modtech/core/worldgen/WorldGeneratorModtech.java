package net.blep.modtech.core.worldgen;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

/**
 * Created by Kelan on 05/02/2016.
 */
public class WorldGeneratorModtech implements IWorldGenerator
{
    public static final int DIMENSION_OVERWORLD = 0;
    public static final int DIMENSION_NETHER = -1;
    public static final int DIMENSION_END = 1;
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        int dimension = world.provider.dimensionId;

        if (dimension == DIMENSION_OVERWORLD)
            generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        else if (dimension == DIMENSION_END)
            generateEnd(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        else if (dimension == DIMENSION_NETHER)
            generateNether(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);

    }

    private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        for (EnumOreGenProperties oreGenerator : EnumOreGenProperties.values())
        {
            if (oreGenerator.generate(world, random, chunkX, chunkZ));
        }
    }

    private void generateEnd(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {

    }

    private void generateNether(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {

    }
}
