package net.blep.modtech.core.util;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Kelan on 26/01/2016.
 */
public class BlockTexture
{
    private IIcon[] icons = new IIcon[6];
    private String[] files = new String[6];
    private ForgeDirection facing = ForgeDirection.UNKNOWN;
    private int down = 0, up = 1, front = 2, back = 3, left = 4, right = 5;

    public final int[][] rotationMatrix = new int[][]{
            {back, front, left, right, up, down},
            {front, back, left, right, up, down},
            {down, up, front, back, right, left},
            {down, up, back, front, left, right},
            {down, up, left, right, front, back},
            {down, up, right, left, back, front}
    };

    public BlockTexture(String[] files, String defaultFile)
    {
        for (int i = 0; i < this.files.length; i++)
        {
            if (files != null && i >= 0 && i < files.length)
            {
                this.files[i] = files[i];
            } else if (defaultFile != null)
            {
                this.files[i] = defaultFile;
            } else
            {
                this.files[i] = "";
            }
        }
    }

    public BlockTexture(String... files)
    {
        this(files, null);
    }

    public IIcon getIcon(int side, int meta)
    {
        if (!facing.equals(ForgeDirection.UNKNOWN))
            return icons[rotationMatrix[meta][side]];
        return icons[side];
    }

    public void setFacing(ForgeDirection direction)
    {
        this.facing = direction;
    }

    public ForgeDirection getFacing()
    {
        return facing;
    }

    public void setupIcons(IIconRegister iconRegister)
    {
        for (int i = 0; i < 6; i++)
        {
            icons[i] = iconRegister.registerIcon("modtech:" + files[i]);
        }
    }

    public void facePlayer(EntityPlayer player, World world, int x, int y, int z, boolean useYAxis)
    {
        int playerYaw = MathHelper.floor_double(((double) player.rotationYaw * 4.0D / 360.0D) + 2.5D) & 3;
        float pitch = player.rotationPitch;
        int playerPitch = pitch < -45 ? 0 : pitch > 45 ? 1 : -1;
        ForgeDirection fd = ForgeDirection.UNKNOWN;

        if (playerPitch == -1 || !useYAxis)
        {
            if (playerYaw == 0) fd = ForgeDirection.NORTH;
            if (playerYaw == 1) fd = ForgeDirection.EAST;
            if (playerYaw == 2) fd = ForgeDirection.SOUTH;
            if (playerYaw == 3) fd = ForgeDirection.WEST;
        } else
        {
            if (playerPitch == 0) fd = ForgeDirection.DOWN;
            if (playerPitch == 1) fd = ForgeDirection.UP;
        }

        this.facing = fd.getOpposite();
        world.setBlockMetadataWithNotify(x, y, z, facing.ordinal(), 2);
    }

    public void setFront(int side, World world, int x, int y, int z)
    {
        this.facing = ForgeDirection.getOrientation(side == 0 ? 1 : side == 1 ? 0 : side);
        world.setBlockMetadataWithNotify(x, y, z, facing.ordinal(), 2);
    }
}
