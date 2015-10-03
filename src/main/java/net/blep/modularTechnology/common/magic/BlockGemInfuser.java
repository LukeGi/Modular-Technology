package net.blep.modularTechnology.common.magic;

import net.blep.modularTechnology.common.core.blocks.block.ModBlockTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class BlockGemInfuser extends ModBlockTileEntity
{
    protected BlockGemInfuser(Material material, String name, float resistance, float hardness, String toolType, int HarvestLevel)
    {
        super(material, name, resistance, hardness, toolType, HarvestLevel);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TEGemInfuser();
    }
}
