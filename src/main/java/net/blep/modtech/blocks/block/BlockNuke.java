package net.blep.modtech.blocks.block;

import net.blep.modtech.api.BlockProperties;
import net.blep.modtech.blocks.ModTileBlock;
import net.blep.modtech.blocks.tileentity.TileEntityNuke;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Kelan on 28/01/2016.
 */
public class BlockNuke extends ModTileBlock
{
    public BlockNuke()
    {
        super(BlockProperties.GENERIC_MACHINE, "nuke");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityNuke();
    }
}
