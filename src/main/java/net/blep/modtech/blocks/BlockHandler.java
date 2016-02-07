package net.blep.modtech.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.blep.modtech.blocks.block.BlockMachine;
import net.blep.modtech.blocks.block.BlockMetal;
import net.blep.modtech.blocks.block.BlockOre;
import net.blep.modtech.blocks.tileentity.TileEntityMachine;
import net.blep.modtech.blocks.tileentity.machines.TileEntityFurnace;
import net.blep.modtech.core.ModTech;
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

    public static void registerBlocks()
    {
        String TILEENTITY_PREFIX = ModInfo.MOD_ID + ".tile.";
        String BLOCK_PREFIX = ModInfo.MOD_ID + ".block.";

        GameRegistry.registerBlock(BLOCK_METAL_ORE, ItemBlockMetalBlock.class, BLOCK_PREFIX + "ore");
        GameRegistry.registerBlock(BLOCK_METAL_BLOCK, ItemBlockMetalBlock.class, BLOCK_PREFIX + "metal");
        GameRegistry.registerBlock(BLOCK_MACHINE, ItemBlockMachine.class, BLOCK_PREFIX + "machine");

        for (int i = 0; i < EnumMachineProperties.values().length; i++)
        {
            EnumMachineProperties machine = EnumMachineProperties.values()[i];
            GameRegistry.registerTileEntity(machine.getTileClass(), TILEENTITY_PREFIX + machine.getName().toLowerCase());
        }
    }
}
