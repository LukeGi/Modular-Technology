package net.blep.modularTechnology.common.core.items;

import net.blep.modularTechnology.common.core.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * @author TheEpicTekkit
 */
public class ItemDismountedTileData extends ModItem
{
    public ItemDismountedTileData()
    {
        super("dismountedTile", null);
        setMaxStackSize(1);
    }

    public String getUnlocalizedName(ItemStack stack)
    {
        String name = null;

        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("block"))
        {
            try
            {
                name = Block.getBlockById(stack.getTagCompound().getInteger("block")).getUnlocalizedName();
            } catch (Exception e)
            {
            }
        }

        return "item.dismountedTile." + name + ".name";
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (stack.hasTagCompound())
        {
            try
            {
                Block block = Block.getBlockById((stack.getTagCompound().getInteger("block")));
                int meta = stack.getTagCompound().getInteger("metadata");

                if (block != null && block instanceof ITileEntityProvider)
                {
                    int x1 = x;
                    int y1 = y;
                    int z1 = z;

                    if (!world.getBlock(x1, y1, z1).isReplaceable(world, x1, y1, z1))
                    {
                        ForgeDirection direction = ForgeDirection.values()[side];
                        x1 += direction.offsetX;
                        y1 += direction.offsetY;
                        z1 += direction.offsetZ;
                    } else
                    {
                        world.setBlockToAir(x1, y1, z1);
                    }

                    world.setBlock(x1, y1, z1, block);
                    world.setBlockMetadataWithNotify(x1, y1, z1, meta, 2);
                    block.onBlockAdded(world, x1, y1, z1);
                    TileEntity tile = ((ITileEntityProvider) block).createNewTileEntity(world, meta);
                    tile.validate();
                    world.setTileEntity(x1, y1, z1, tile);
                    tile.markDirty();
                    tile.readFromNBT(stack.getTagCompound().getCompoundTag("tileNBT"));
                    tile.xCoord = x1;
                    tile.yCoord = y1;
                    tile.zCoord = z1;
                    world.markBlockForUpdate(x1, y1, z1);
                    stack.stackSize--;

                    return true;
                }
            } catch (Exception e)
            {
                LogHelper.error("An error has occured while placing a saved tile data:");
                e.printStackTrace();
            }
        }

        return false;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
    {
        if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("tileNBT"))
        {
            list.add(EnumChatFormatting.RED + "Error in stack NBT");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
        {
            NBTTagCompound tag = stack.getTagCompound().getCompoundTag("tileNBT");

            String[] s = tag.toString().split(",");

            for (String s1 : s)
            {
                list.add(s1);
            }

            list.add(tag.toString());
        } else
        {
            list.add(EnumChatFormatting.RESET + "Hold " + EnumChatFormatting.GOLD + EnumChatFormatting.UNDERLINE + "SHIFT" + EnumChatFormatting.RESET + " to show more information.");
        }
    }
}
