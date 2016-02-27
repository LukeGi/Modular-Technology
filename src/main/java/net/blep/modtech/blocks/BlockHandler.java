package net.blep.modtech.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.blep.modtech.blocks.block.*;
import net.blep.modtech.blocks.tileentity.TileEntityTreeFarm;
import net.blep.modtech.core.reference.ModInfo;
import net.blep.modtech.core.util.EnumMachineProperties;
import net.blep.modtech.items.itemblock.ItemBlockMachine;
import net.blep.modtech.items.itemblock.ItemBlockMetalBlock;
import net.minecraft.block.Block;

/**
 * Created by Kelan on 24/01/2016.
 */
public class BlockHandler
{
    public static final Block BLOCK_METAL_ORE = new BlockOre();
    public static final Block BLOCK_METAL_BLOCK = new BlockMetal();
    public static final Block BLOCK_MACHINE = new BlockMachine();
    public static final Block BLOCK_TEST_SIDES = new BlockTest();
    public static final Block TEST = new BlockMadness();
    public static final Block TREE_FARM = new BlockTreeFarm();

    public static void registerBlocks()
    {
        String TILEENTITY_PREFIX = ModInfo.MOD_ID + ".tile.";
        String BLOCK_PREFIX = ModInfo.MOD_ID + ".block.";

        GameRegistry.registerBlock(BLOCK_METAL_ORE, ItemBlockMetalBlock.class, BLOCK_PREFIX + "ore");
        GameRegistry.registerBlock(BLOCK_METAL_BLOCK, ItemBlockMetalBlock.class, BLOCK_PREFIX + "metal");
        GameRegistry.registerBlock(BLOCK_MACHINE, ItemBlockMachine.class, BLOCK_PREFIX + "machine");
        for (int i = 0; i < EnumMachineProperties.values().length; i++)
            GameRegistry.registerTileEntity(EnumMachineProperties.values()[i].getTileClass(), TILEENTITY_PREFIX + EnumMachineProperties.values()[i].getName().toLowerCase());
        GameRegistry.registerBlock(BLOCK_TEST_SIDES, BLOCK_PREFIX + "test");
        GameRegistry.registerBlock(TEST, "blockTest");
        GameRegistry.registerTileEntity(BlockMadness.TileEntityParticleGenerator.class, ModInfo.RESOURCE_PREFIX + "particleGenerator");
        GameRegistry.registerBlock(TREE_FARM, "treeFarm");
        GameRegistry.registerTileEntity(TileEntityTreeFarm.class, ModInfo.RESOURCE_PREFIX + "treeFarm");

    }
}
