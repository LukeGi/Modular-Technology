package net.blep.modularTechnology.common.core.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.blep.modularTechnology.client.core.gui.machines.GuiCrusher;
import net.blep.modularTechnology.common.core.blocks.tileentity.TileEntityContainerHolder;
import net.blep.modularTechnology.common.core.gui.machines.ContainerCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author TheEpicTekkit
 */
public class ModGuiHandler implements IGuiHandler
{
    public static final int ID_CRUSHER = 1;

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile != null && tile instanceof TileEntityContainerHolder)
        {
            switch (id)
            {
                case ID_CRUSHER: return new ContainerCrusher((IInventory)tile, player);

                default: return null;
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile != null && tile instanceof TileEntityContainerHolder)
        {
            switch (id)
            {
                case ID_CRUSHER: return new GuiCrusher(tile, player);

                default: return null;
            }
        }

        return null;
    }
}
