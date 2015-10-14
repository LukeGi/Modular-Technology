package com.blep.modularTechnology.tech.common.blocks.block.machines;

import com.blep.modularTechnology.core.common.blocks.block.BlockMachineBase;
import com.blep.modularTechnology.core.common.inventory.ModGuiHandler;
import com.blep.modularTechnology.tech.common.blocks.tileentity.TileEntityCrusher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author TheEpicTekkit
 */
public class BlockCrusher extends BlockMachineBase
{
    public BlockCrusher()
    {
        super("crusher", 4.0F, 4.0F, ModGuiHandler.ID_CRUSHER);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityCrusher();
    }
}
