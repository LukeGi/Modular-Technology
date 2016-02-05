package net.blep.modtech.core.worldgen;

import cpw.mods.fml.common.IWorldGenerator;
import net.blep.modtech.blocks.BlockHandler;
import net.blep.modtech.core.util.EnumMetalMaterial;
import net.blep.modtech.items.itemblock.ItemBlockOre;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

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
            generateOverworld(random, chunkX * 16, chunkZ * 16, world, chunkGenerator, chunkProvider);
        else if (dimension == DIMENSION_END)
            generateEnd(random, chunkX * 16, chunkZ * 16, world, chunkGenerator, chunkProvider);
        else if (dimension == DIMENSION_NETHER)
            generateNether(random, chunkX * 16, chunkZ * 16, world, chunkGenerator, chunkProvider);

    }

    private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        generateOres(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
    }

    private void generateOres(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        for (int i = 0; i < EnumOreGenProperties.values().length; i++) {
            addOreSpawn(BlockHandler.BLOCK_METAL_ORE, i, world, random, chunkX, chunkZ, 16, 16, 2 + random.nextInt(4), 15, EnumOreGenProperties.values()[i].getMinHeight(), EnumOreGenProperties.values()[i].getMaxHeight());
        }
    }

    private void generateEnd(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {

    }

    private void generateNether(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {

    }

    private void addOreSpawn(Block block, int meta, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chanceToSpawn, int minY, int maxY)
    {
        for (int i = 0; i < chanceToSpawn; i++) {
            int posX= blockXPos + random.nextInt(maxX);
            int posY= minY + random.nextInt(maxY - minY);
            int posZ= blockZPos + random.nextInt(maxZ);
            (new WorldGenMinable(block, meta, maxVeinSize, Blocks.stone)).generate(world, random, posX, posY, posZ);
        }
    }

}
