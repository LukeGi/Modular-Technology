package com.blep.modularTechnology.tech.common.blocks.block;

import com.blep.modularTechnology.core.common.blocks.block.ModBlockTileEntity;
import com.blep.modularTechnology.tech.common.blocks.tileentity.TileEntityRedstoneInput;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Kelan
 */
public class BlockRedstoneInput extends ModBlockTileEntity
{
    public BlockRedstoneInput(Material material, String name, float resistance, float hardness, String toolType, int HarvestLevel, String... textures)
    {
        super(material, name, resistance, hardness, toolType, HarvestLevel, textures);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityRedstoneInput();
    }
}
