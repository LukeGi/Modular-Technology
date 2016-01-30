package com.blep.modularTechnology.core.common.items;

import com.blep.modularTechnology.core.common.items.ModItem;
import net.blep.modularTechnology.common.core.ModContent;
import com.blep.modularTechnology.core.common.ModularTechnology;
import com.blep.modularTechnology.core.common.util.ITileRotatable;
import com.blep.modularTechnology.core.common.util.MethodHelper;
import net.blep.modularTechnology.common.tech.tabs.CreativeTabHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.input.Keyboard;

/**
 * @author TheEpicTekkit
 */
public class ItemWrench extends ModItem
{
//    public ItemWrench()
//    {
//        super("uniwrench", CreativeTabHandler.tabModtech);
//    }
//
//    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
//    {
//        if (!world.isRemote)
//        {
//            TileEntity tile = world.getTileEntity(x, y, z);
//            Block block = world.getBlock(x, y, z);
//            int sideClicked = side;
//
//            try
//            {
//                if (block == null)
//                    return false;
//
//                System.out.println(block.getUnlocalizedName());
//
//                if (ModContent.WRENCH_BLACKLIST.contains(block.getUnlocalizedName()))
//                    return false;
//            } catch (Exception e)
//            {
//                ModularTechnology.LOGGER.error("An error occurred while checking the blacklist.");
//                e.printStackTrace();
//            }
//
//            if (tile != null)
//            {
//                int meta = world.getBlockMetadata(x, y, z);
//                if (player.isSneaking())
//                {
//                    if (!Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
//                    {
//                        MethodHelper.spawnItemEntityInWorld(world, x, y, z, new ItemStack(block.getItemDropped(meta, world.rand, 0), block.quantityDropped(meta, 0, world.rand), block.damageDropped(meta)));
//                        world.setBlockToAir(x, y, z);
//                        return true;
//                    }
//                    else
//                    {
//                        sideClicked = ForgeDirection.OPPOSITES[side];
//                    }
//                }
//                else if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
//                {
//                    MethodHelper.spawnItemEntityInWorld(world, x, y, z, pickupTileEntity(world, stack, player, x, y, z));
//                    world.removeTileEntity(x, y, z);
//                    world.setBlockToAir(x, y, z);
//                }
//
//                if (tile instanceof ITileRotatable)
//                    return ((ITileRotatable) tile).onRotated(stack, player, world, x, y, z, sideClicked, hitX, hitY, hitZ);
//            }
//        }
//        return false;
//    }
//
//    public ItemStack pickupTileEntity(World world, ItemStack stack, EntityPlayer player, int x, int y, int z)
//    {
//        NBTTagCompound tileNBT = new NBTTagCompound();
//        NBTTagCompound tagCompound = new NBTTagCompound();
//
//        TileEntity tile = world.getTileEntity(x, y, z);
//        Block block = world.getBlock(x, y, z);
//        int meta = world.getBlockMetadata(x, y, z);
//
//        if (block != null && tile != null)
//        {
//            tile.writeToNBT(tileNBT);
//
//            tagCompound.setInteger("block", Block.getIdFromBlock(block));
//            tagCompound.setInteger("metadata", meta);
//            tagCompound.setTag("tileNBT", tileNBT);
//
//            ItemStack newStack = new ItemStack(ModContent.getTechModItems().dismountedMachine, 1, block.damageDropped(meta));
//            newStack.setTagCompound(tagCompound);
//
//            return newStack;
//        }
//
//        return null;
//    }
}
