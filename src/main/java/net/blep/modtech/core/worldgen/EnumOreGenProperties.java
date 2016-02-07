package net.blep.modtech.core.worldgen;

import net.blep.modtech.blocks.BlockHandler;
import net.blep.modtech.core.util.LogHelper;
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

    //    public void generate(Block block, int meta, World world, Random random, int blockXPos, int blockZPos)
//    {
//        int maxVeinSize = this.veinSize + random.nextInt(this.weight);
//        for (int i = 0; i < this.chancePerChunk; i++) {
//            int posX= blockXPos + random.nextInt(16);
//            int posY= this.minHeight + random.nextInt(this.maxHeight - this.minHeight);
//            int posZ= blockZPos + random.nextInt(16);
//            (new WorldGenMinable(block, meta, maxVeinSize, Blocks.stone)).generate(world, random, posX, posY, posZ);
//            LogHelper.info(String.format("Spawned a(n) %s ore patch at x: %s, y: %s, z: %s", name().toLowerCase(), posX, posY, posZ));
//        }
//    }
    //  /cofh clearblocks <YourIGN> 100 200 100 stone dirt water lava gravel sand

    public boolean generate(World world, Random random, int chunkX, int chunkZ)
    {
        boolean flag = false;
        if (random.nextFloat() <= chancePerChunk && random.nextFloat() <= weight)
        {
            int x = random.nextInt(16) * chunkX;
            int y = minHeight + random.nextInt(maxHeight - minHeight);
            int z = random.nextInt(16) * chunkZ;

            float f = random.nextFloat() * (float) Math.PI;
            double d0 = (double)((float)(x + 8) + MathHelper.sin(f) * (float)this.veinSize / 8.0F);
            double d1 = (double)((float)(x + 8) - MathHelper.sin(f) * (float)this.veinSize / 8.0F);
            double d2 = (double)((float)(z + 8) + MathHelper.cos(f) * (float)this.veinSize / 8.0F);
            double d3 = (double)((float)(z + 8) - MathHelper.cos(f) * (float)this.veinSize / 8.0F);
            double d4 = (double)(y + random.nextInt(3) - 2);
            double d5 = (double)(y + random.nextInt(3) - 2);

            for (int l = 0; l <= this.veinSize; ++l)
            {
                double d6 = d0 + (d1 - d0) * (double) l / (double) this.veinSize;
                double d7 = d4 + (d5 - d4) * (double) l / (double) this.veinSize;
                double d8 = d2 + (d3 - d2) * (double) l / (double) this.veinSize;
                double d9 = random.nextDouble() * (double) this.veinSize / 16.0D;
                double d10 = (double) (MathHelper.sin((float) l * (float) Math.PI / (float) this.veinSize) + 1.0F) * d9 + 1.0D;
                double d11 = (double) (MathHelper.sin((float) l * (float) Math.PI / (float) this.veinSize) + 1.0F) * d9 + 1.0D;
                int xStartRand = MathHelper.floor_double(d6 - d10 / 2.0D);
                int yStartRand = MathHelper.floor_double(d7 - d11 / 2.0D);
                int zStartRand = MathHelper.floor_double(d8 - d10 / 2.0D);
                int xEndRand = MathHelper.floor_double(d6 + d10 / 2.0D);
                int yEndRand = MathHelper.floor_double(d7 + d11 / 2.0D);
                int zEndRand = MathHelper.floor_double(d8 + d10 / 2.0D);

                for (int x0 = xStartRand; x0 <= xEndRand; ++x0)
                {
                    double d12 = ((double) x0 + 0.5D - d6) / (d10 / 2.0D);

                    if (d12 * d12 < 1.0D)
                    {
                        for (int y0 = yStartRand; y0 <= yEndRand; ++y0)
                        {
                            double d13 = ((double) y0 + 0.5D - d7) / (d11 / 2.0D);

                            if (d12 * d12 + d13 * d13 < 1.0D)
                            {
                                for (int z0 = zStartRand; z0 <= zEndRand; ++z0)
                                {
                                    double d14 = ((double) z0 + 0.5D - d8) / (d10 / 2.0D);

                                    if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && world.getBlock(x0, y0, z0).isReplaceableOreGen(world, x0, y0, z0, spawnAround))
                                    {
                                        world.setBlock(x0, y0, z0, BlockHandler.BLOCK_METAL_ORE, ordinal(), 2);
                                        flag = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /*
    this.ironGen = new WorldGenMinable(Blocks.iron_ore, 8);

    if (TerrainGen.generateOre(currentWorld, randomGenerator, ironGen, chunk_X, chunk_Z, IRON))
        this.genStandardOre1(20, this.ironGen, 0, 64);

    public static boolean generateOre(World world, Random rand, WorldGenerator generator, int worldX, int worldZ, GenerateMinable.EventType type)
    {
        GenerateMinable event = new GenerateMinable(world, rand, generator, worldX, worldZ, type);
        MinecraftForge.ORE_GEN_BUS.post(event);
        return event.getResult() != Result.DENY;
    }

    protected void genStandardOre1(int p_76795_1_, WorldGenerator p_76795_2_, int p_76795_3_, int p_76795_4_)
    {
        for (int l = 0; l < p_76795_1_; ++l)
        {
            int i1 = this.chunk_X + this.randomGenerator.nextInt(16);
            int j1 = this.randomGenerator.nextInt(p_76795_4_ - p_76795_3_) + p_76795_3_;
            int k1 = this.chunk_Z + this.randomGenerator.nextInt(16);
            p_76795_2_.generate(this.currentWorld, this.randomGenerator, i1, j1, k1);
        }
    }
     */
}
