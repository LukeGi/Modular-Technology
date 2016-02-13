package net.blep.modtech.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.blep.modtech.client.gui.GuiMachineFurnace;
import net.blep.modtech.core.util.EnumMachineProperties;
import net.blep.modtech.gui.container.ContainerMachineFurnace;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Kelan on 07/02/2016.
 */
public class GuiHandlerModtech implements IGuiHandler
{
    private static int next = 0;

    public static final int ID_FURNACE = next++;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);


        if (ID == ID_FURNACE)
            return new ContainerMachineFurnace(tile);
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (ID == ID_FURNACE)
            return new GuiMachineFurnace(new ContainerMachineFurnace(tile));
        return null;
    }
}
