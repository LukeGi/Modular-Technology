package net.blep.modtech.core.worldgen;

import net.blep.modtech.blocks.BlockHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Kelan on 05/02/2016.
 */
public enum EnumOreGenProperties
{
    COPPER(0.40F, 35, 75, 16, 1.0F, null, new int[]{0}),
    TIN(0.45F, 30, 65, 16, 1.0F, null, new int[]{0}),
    LEAD(0.20F, 10, 35, 16, 1.0F, null, new int[]{0}),
    SILVER(0.12F, 5, 45, 16, 1.0F, null, new int[]{0}),
    PLATINUM(0.08F, 5, 22, 16, 1.0F, null, new int[]{0}),
    CHROMIUM(0.09F, 10, 20, 16, 1.0F, null, new int[]{0}),
    ZINC(0.70F, 5, 64, 16, 1.0F, null, new int[]{0}),
    MAGNESIUM(0.52F, 5, 80, 16, 1.0F, null, new int[]{0}),
    MANGANESE(0.32F, 40, 60, 16, 1.0F, null, new int[]{0}),
    YTTRIUM(0.36F, 5, 35, 16, 1.0F, null, new int[]{0}),
    ZIRCONIUM(0.32F, 20, 35, 16, 1.0F, null, new int[]{0}),
    ALUMINIUM(0.58F, 15, 40, 16, 1.0F, null, new int[]{0}),
    MOLYBDENUM(0.26F, 14, 45, 16, 1.0F, null, new int[]{0}),
    TITANIUM(0.10F, 5, 35, 16, 1.0F, null, new int[]{0});

    private float chancePerChunk;
    private int minHeight;
    private int maxHeight;
    private int veinSize;
    private float weight;
    private Block spawnAround;
    private String[] biomes;
    private int[] dimensions;

    EnumOreGenProperties(float chancePerChunk, int minHeight, int maxHeight, int veinSize, float weight, String[] biomes, int[] dimensions)
    {
        this(chancePerChunk, minHeight, maxHeight, veinSize, weight, Blocks.stone, biomes, dimensions);
    }

    EnumOreGenProperties(float chancePerChunk, int minHeight, int maxHeight, int veinSize, float weight, Block spawnAround, String[] biomes, int[] dimensions)
    {
        this.chancePerChunk = chancePerChunk;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinSize = veinSize;
        this.weight = weight;
        this.spawnAround = spawnAround;
        this.biomes = biomes;
        this.dimensions = dimensions;
    }

    public float getChancePerChunk()
    {
        return chancePerChunk;
    }

    public int getMaxHeight()
    {
        return maxHeight;
    }

    public int getMinHeight()
    {
        return minHeight;
    }

    public int getVeinSize()
    {
        return veinSize;
    }

    public float getWeight()
    {
        return weight;
    }

    public String[] getBiomes()
    {
        return biomes;
    }

    public int[] getDimensions()
    {
        return dimensions;
    }

    public boolean generate(World world, Random random, int chunkX, int chunkZ)
    {
        if (random.nextFloat() <= chancePerChunk && random.nextFloat() <= weight)
        {
            int x = random.nextInt(16) * chunkX;
            int y = minHeight + random.nextInt(maxHeight - minHeight);
            int z = random.nextInt(16) * chunkZ;

            float f = random.nextFloat() * (float) Math.PI;
            double d0 = (double) ((float) (x + 8) + MathHelper.sin(f) * (float) this.veinSize / 8.0F);
            double d1 = (double) ((float) (x + 8) - MathHelper.sin(f) * (float) this.veinSize / 8.0F);
            double d2 = (double) ((float) (z + 8) + MathHelper.cos(f) * (float) this.veinSize / 8.0F);
            double d3 = (double) ((float) (z + 8) - MathHelper.cos(f) * (float) this.veinSize / 8.0F);
            double d4 = (double) (y + random.nextInt(3) - 2);
            double d5 = (double) (y + random.nextInt(3) - 2);

            System.out.println("Spawning vein of " + this + " at " + x + " " + y + " " + z);
            for (int l = 0; l <= this.veinSize; ++l)
            {
                double d6 = d0 + (d1 - d0) * (double) l / (double) this.veinSize;
                double d7 = d4 + (d5 - d4) * (double) l / (double) this.veinSize;
                double d8 = d2 + (d3 - d2) * (double) l / (double) this.veinSize;
                double d9 = random.nextDouble() * (double) this.veinSize / 16.0D;
                double d10 = (double) (MathHelper.sin((float) l * (float) Math.PI / (float) this.veinSize) + 1.0F) * d9 + 1.0D;
                double d11 = (double) (MathHelper.sin((float) l * (float) Math.PI / (float) this.veinSize) + 1.0F) * d9 + 1.0D;
                int i1 = MathHelper.floor_double(d6 - d10 / 2.0D);
                int j1 = MathHelper.floor_double(d7 - d11 / 2.0D);
                int k1 = MathHelper.floor_double(d8 - d10 / 2.0D);
                int l1 = MathHelper.floor_double(d6 + d10 / 2.0D);
                int i2 = MathHelper.floor_double(d7 + d11 / 2.0D);
                int j2 = MathHelper.floor_double(d8 + d10 / 2.0D);

                for (int k2 = i1; k2 <= l1; ++k2)
                {
                    double d12 = ((double) k2 + 0.5D - d6) / (d10 / 2.0D);

                    if (d12 * d12 < 1.0D)
                    {
                        for (int l2 = j1; l2 <= i2; ++l2)
                        {
                            double d13 = ((double) l2 + 0.5D - d7) / (d11 / 2.0D);

                            if (d12 * d12 + d13 * d13 < 1.0D)
                            {
                                for (int i3 = k1; i3 <= j2; ++i3)
                                {
                                    double d14 = ((double) i3 + 0.5D - d8) / (d10 / 2.0D);

                                    if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && world.getBlock(k2, l2, i3).isReplaceableOreGen(world, k2, l2, i3, spawnAround))
                                    {
                                        world.setBlock(k2, l2, i3, BlockHandler.BLOCK_METAL_ORE, ordinal(), 2);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
