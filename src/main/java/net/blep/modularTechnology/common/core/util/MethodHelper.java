package net.blep.modularTechnology.common.core.util;

import net.blep.modularTechnology.client.tech.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author TheEpicTekkit
 * @author bluemonster122 <boo122333@gmail.com>
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
                    entityitem.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
                }

                float f3 = 0.05F;
                entityitem.motionX = (double)((float)world.rand.nextGaussian() * f3);
                entityitem.motionY = (double)((float)world.rand.nextGaussian() * f3 + 0.2F);
                entityitem.motionZ = (double)((float)world.rand.nextGaussian() * f3);
                world.spawnEntityInWorld(entityitem);
            }
        }
    }

    public static void getCoordinatesAdjacentToSide(int x, int y, int z, int side)
    {
        ForgeDirection direction = ForgeDirection.values()[ClientProxy.BLOCK_SIDE_TO_FD[side]];
        x += direction.offsetX;
        y += direction.offsetY;
        z += direction.offsetZ;
    }

    public static void spawnEntityAtLocation(World world, EntityLivingBase entity, int x, int y, int z)
    {
        entity.setPosition(x + 0.5, y + 0.5, z + 0.5);
        world.spawnEntityInWorld(entity);
    }

    public static void spawnItemStack(World world, int x, int y, int z, ItemStack stack)
    {
        world.spawnEntityInWorld(new EntityItem(world, x, y, z, stack));
    }
}
