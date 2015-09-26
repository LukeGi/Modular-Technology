package net.blep.modularTechnology.common.tech.blocks.block.machines;

import net.blep.modularTechnology.common.core.gui.ModGuiHandler;
import net.blep.modularTechnology.common.tech.blocks.tileentity.TileEntityCrusher;
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
