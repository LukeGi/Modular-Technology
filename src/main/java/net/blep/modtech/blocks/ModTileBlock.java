package net.blep.modtech.blocks;

import net.blep.modtech.core.util.BlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Kelan on 24/01/2016.
 */
public abstract class ModTileBlock extends ModBlock implements ITileEntityProvider
{
    public ModTileBlock(BlockProperties properties, String blockName)
    {
        super(properties, blockName);
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        super.breakBlock(world, x, y, z, block, meta);
        world.removeTileEntity(x, y, z);
    }

    public boolean onBlockEventReceived(World world, int x, int y, int z, int meta, int partialTick)
    {
        super.onBlockEventReceived(world, x, y, z, meta, partialTick);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null && tileentity.receiveClientEvent(meta, partialTick);
    }

    public abstract TileEntity createNewTileEntity(World world, int meta);
}
