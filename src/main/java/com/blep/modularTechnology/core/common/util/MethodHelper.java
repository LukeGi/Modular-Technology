package com.blep.modularTechnology.core.common.util;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.input.Keyboard;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TheEpicTekkit
 */
public class MethodHelper
{
    public static String capitaliseCharAt(String str, int index, boolean capital)
    {
        if (str == null)
            return "";

        if (index < 0 || index > str.length())
            return str;

        String first = str.substring(0, index);
        String last = str.substring(index, str.length());

        if (capital)
            last = last.substring(0, 1).toUpperCase() + last.substring(1);
        else
            last = last.substring(0, 1).toLowerCase() + last.substring(1);

        String result = first + last;

        return result;
    }

    public static String capitaliseCharAt(String str, int index)
    {
        return capitaliseCharAt(str, index, true);
    }

    public static ItemStack setStackTagCompound(ItemStack stack, NBTTagCompound tagCompound)
    {
        return setStackTagCompound(stack, tagCompound, false);
    }

    public static ItemStack setStackTagCompound(ItemStack stack, NBTTagCompound tagCompound, boolean override)
    {
        if (override)
            stack.setTagCompound(tagCompound);

        else if (!stack.hasTagCompound())
            stack.setTagCompound(tagCompound);

        return stack;
    }

    public static void getDefaultRotation(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
            Block block = world.getBlock(x, y, z - 1);
            Block block1 = world.getBlock(x, y, z + 1);
            Block block2 = world.getBlock(x - 1, y, z);
            Block block3 = world.getBlock(x + 1, y, z);
            byte b0 = 3;

            if (block.func_149730_j() && !block1.func_149730_j())
            {
                b0 = 2;
            }

            if (block1.func_149730_j() && !block.func_149730_j())
            {
                b0 = 3;
            }

            if (block2.func_149730_j() && !block3.func_149730_j())
            {
                b0 = 4;
            }

            if (block3.func_149730_j() && !block2.func_149730_j())
            {
                b0 = 5;
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    public static int getEntityRotationYawOrdinal(Entity entity, int resolution)
    {
        return MathHelper.floor_double(0.5D + entity.rotationYaw * resolution / 360.0D) & (resolution - 1);
    }

    public static void spawnItemEntityInWorld(World world, double x, double y, double z, ItemStack stack)
    {
        if (stack != null)
        {
            float f = world.rand.nextFloat() * 0.8F + 0.1F;
            float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
            float f2 = world.rand.nextFloat() * 0.8F + 0.1F;

            while (stack.stackSize > 0)
            {
                int j1 = world.rand.nextInt(21) + 10;

                if (j1 > stack.stackSize)
                {
                    j1 = stack.stackSize;
                }

                stack.stackSize -= j1;
                EntityItem entityitem = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(stack.getItem(), j1, stack.getItemDamage()));

                if (stack.hasTagCompound())
                {
                    entityitem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
                }

                float f3 = 0.05F;
                entityitem.motionX = (double) ((float) world.rand.nextGaussian() * f3);
                entityitem.motionY = (double) ((float) world.rand.nextGaussian() * f3 + 0.2F);
                entityitem.motionZ = (double) ((float) world.rand.nextGaussian() * f3);
                world.spawnEntityInWorld(entityitem);
            }
        }
    }

    public static String formatNumber(double number)
    {
        String num = "" + number;
        String result = "";

        if (num.length() >= 4)
        {
            int end = 0;
            if (num.contains(".")) end = num.indexOf(".");
            else end = num.length();

            int last = end;
            for (int i = end - 3; i > 0; i -= 3)
            {
                String s = num.substring(i >= 0 ? i : 0, last) + ",";
                result = s + result;
                last = i;
            }

            if (result.contains(",.")) result.replace(",.", ".");
            if (result.endsWith(".")) result = result.substring(0, result.length() - 1);
        } else
            result = num;

        return result;
    }

    public static void getCoordinatesAdjacentToSide(int x, int y, int z, int side)
    {
        ForgeDirection direction = ForgeDirection.values()[side];
        x += direction.offsetX;
        y += direction.offsetY;
        z += direction.offsetZ;
    }

    public static void handleTooltipException(Exception e, List list)
    {
        list.add(EnumChatFormatting.RED + "An exception was caught while creating tooltip:");
        list.add(EnumChatFormatting.RED + e.getLocalizedMessage());
        list.add(" ");
        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
        {
            for (StackTraceElement t : e.getStackTrace())
            {
                list.add(EnumChatFormatting.RED + "" + t);
            }
        } else
        {
            list.add(EnumChatFormatting.RED + "Hold " + EnumChatFormatting.DARK_RED + EnumChatFormatting.UNDERLINE + "CONTROL" + EnumChatFormatting.RESET + EnumChatFormatting.RED + " to display the StackTrace");
            list.add(EnumChatFormatting.RED + "The StackTrace may not fit on your screen; put your GUI scale to small");
        }
    }

    public static List writeNBTToList(NBTBase tag) throws NoSuchFieldException, IllegalAccessException
    {
        List list = Lists.newArrayList();

        if (tag instanceof NBTTagCompound)
        {
            Field f = tag.getClass().getDeclaredField("tagMap");
            f.setAccessible(true);
            Map tagMap = (HashMap) f.get(tag);

            for (Object key : tagMap.keySet())
            {
                Object value = tagMap.get(key);

                if (value instanceof NBTTagCompound || value instanceof NBTTagList)
                {
                    list.add(key + ":");
                    list.add(writeNBTToList((NBTBase) value));
                } else
                {
                    list.add(key + ": " + (value.toString()));
                }
            }
        } else if (tag instanceof NBTTagList)
        {
            Field f = tag.getClass().getDeclaredField("tagList");
            f.setAccessible(true);
            List tagList = (java.util.ArrayList) f.get(tag);

            for (Object o : tagList)
            {
                list.add(o);
            }
        }

        return list;
    }

    public static double getValueScaled(double value, double maxValue, double scale)
    {
        return value * scale / maxValue;
    }

    public static boolean areItemStacksEqual(ItemStack a, ItemStack b)
    {
        return a == b || (a != null && b != null && a.getItem() == b.getItem() && a.getItemDamage() == b.getItemDamage() && Objects.equal(a.stackTagCompound, b.stackTagCompound));
    }

    public static void spawnEntityAtLocation(World world, Entity entity, int x, int y, int z)
    {
        entity.setPosition(x, y, z);
        world.spawnEntityInWorld(entity);
    }
}
}
