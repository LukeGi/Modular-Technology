package com.blep.modularTechnology.tech.common.blocks.block;

import com.blep.modularTechnology.core.common.inventory.ModGuiHandler;
import com.blep.modularTechnology.core.common.blocks.block.BlockMachineBase;
import com.blep.modularTechnology.tech.common.blocks.tileentity.TileEntityLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Kelan
 */
public class BlockLogic extends BlockMachineBase
{
    public BlockLogic()
    {
        super("logicGate", 2.0F, 1.2F, ModGuiHandler.ID_LOGIC_GATE);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityLogic();
    }
}
