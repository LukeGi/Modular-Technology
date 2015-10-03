package net.blep.modularTechnology.common.magic;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class ItemDesignator extends MagicItem
{
    protected ItemDesignator()
    {
        super();
        setMaxStackSize(1);
        setUnlocalizedName("coordDesig");
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        Block block = world.getBlock(x, y, z);
        if ((!world.isRemote) && ((player.isSneaking()) && (block != null)))
        {
            NBTTagCompound pos = new NBTTagCompound();
            pos.setInteger("x", x);
            pos.setInteger("y", y);
            pos.setInteger("z", z);
            stack.setTagCompound(pos);
            player.addChatComponentMessage(new ChatComponentText(String.format("Saved the co-ordinated: %s, %s, %s", stack.getTagCompound().getInteger("x"), stack.getTagCompound().getInteger("y"), stack.getTagCompound().getInteger("z"))));
        } else if (tile instanceof IDesignatorReceiver)
        {
            System.out.println("saving coords");
            ((IDesignatorReceiver) tile).recieveData(stack.getTagCompound().getInteger("x"), stack.getTagCompound().getInteger("y"), stack.getTagCompound().getInteger("z"));
        }
        return true;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
        {
            if (stack.hasTagCompound())
            {
                int x = stack.getTagCompound().getInteger("x");
                int y = stack.getTagCompound().getInteger("y");
                int z = stack.getTagCompound().getInteger("z");

                EnumChatFormatting colour = player.canPlayerEdit(x, y, z, 1, stack) ? EnumChatFormatting.GREEN : EnumChatFormatting.RED;
                list.add(colour + "x: " + x);
                list.add(colour + "y: " + y);
                list.add(colour + "z: " + z);
            } else
            {
                list.add(EnumChatFormatting.GOLD + "No co-ordinates designated");
            }
        } else
        {
            list.add(EnumChatFormatting.RESET + "Hold " + EnumChatFormatting.GOLD + EnumChatFormatting.UNDERLINE + "SHIFT" + EnumChatFormatting.RESET + " to show more information.");
        }
    }

    public interface IDesignatorReceiver
    {
        void recieveData(int x, int y, int z);
    }
}
