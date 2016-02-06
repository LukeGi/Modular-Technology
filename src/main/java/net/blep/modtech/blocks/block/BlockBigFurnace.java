package net.blep.modtech.blocks.block;

import net.blep.modtech.core.util.BlockProperties;
import net.blep.modtech.blocks.ModTileBlock;
import net.blep.modtech.blocks.tileentity.TileEntityBigFurnace;
import net.blep.modtech.api.IWrenchable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Kelan on 24/01/2016.
 */
public class BlockBigFurnace extends ModTileBlock implements IWrenchable
{
    public BlockBigFurnace()
    {
        super(BlockProperties.GENERIC_MACHINE, "bigfurnace");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityBigFurnace();
    }

    @Override// when hitX > something: rotate a certain way nononono blue make it face up do it
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            textureConfiguration.setFront(side, world, x, y, z);
        }
        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
    {
        if (entity instanceof EntityPlayer)
        {
            textureConfiguration.facePlayer((EntityPlayer) entity, world, x, y, z, false);
        }
    }
}
