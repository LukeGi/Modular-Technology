package net.blep.modularTechnology.common.tech.blocks.block;

import net.blep.modularTechnology.common.core.blocks.block.ModBlockTileEntity;
import net.blep.modularTechnology.common.tech.blocks.tileentity.TileEntityRedstoneInput;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Kelan
 */
public class BlockRedstoneInput extends ModBlockTileEntity
{
    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityRedstoneInput();
    }
}
