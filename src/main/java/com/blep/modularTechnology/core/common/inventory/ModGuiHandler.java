package com.blep.modularTechnology.core.common.inventory;

import cpw.mods.fml.common.network.IGuiHandler;
import com.blep.modularTechnology.tech.client.gui.GuiCrusher;
import com.blep.modularTechnology.core.common.blocks.tileentity.TileEntityContainerHolder;
import com.blep.modularTechnology.tech.common.inventory.ContainerCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author TheEpicTekkit
 */
public class ModGuiHandler //implements IGuiHandler
{
//    public static final int ID_CRUSHER = 1;
//    public static final int ID_LOGIC_GATE = 2;
//
//    @Override
//    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
//    {
//        TileEntity te = world.getTileEntity(x, y, z);
//
//        if (te != null && te instanceof TileEntityContainerHolder)
//        {
//            TileEntityContainerHolder tile = (TileEntityContainerHolder) te;
//
//            switch (id)
//            {
//                case ID_CRUSHER: return new ContainerCrusher((IInventory)tile, player);
//
//                default: return null;
//            }
//        }
//
//        return null;
//    }
//
//    @Override
//    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
//    {
//        TileEntity te = world.getTileEntity(x, y, z);
//
//        if (te != null && te instanceof TileEntityContainerHolder)
//        {
//            TileEntityContainerHolder tile = (TileEntityContainerHolder) te;
//
//            switch (id)
//            {
//                case ID_CRUSHER: return new GuiCrusher(tile, player);
//
//                default: return null;
//            }
//        }
//
//        return null;
//    }
}
