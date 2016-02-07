package net.blep.modtech.blocks.block;

import net.blep.modtech.blocks.ModBlock;
import net.blep.modtech.core.proxy.Proxy;
import net.blep.modtech.api.BlockProperties;
import net.blep.modtech.api.BlockTexture;
import net.blep.modtech.api.EnumMetalMaterial;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Kelan on 03/02/2016.
 */
public class BlockMetal extends ModBlock
{
    public BlockMetal()
    {
        super(Material.rock);
        setTextureConfiguration(new BlockTexture(new String[EnumMetalMaterial.values().length][0], "blockMetalBase"));
        setCreativeTab(CreativeTabs.tabBlock);
        setBlockName("block");
    }

    @Override
    public String getHarvestTool(int metadata)
    {
        return BlockProperties.ORES[metadata].getHarvestTool();
    }

    @Override
    public int getHarvestLevel(int metadata)
    {
        return BlockProperties.ORES[metadata].getHarvestLevel();
    }

    @Override
    public float getBlockHardness(World world, int x, int y, int z)
    {
        return BlockProperties.ORES[world.getBlockMetadata(x, y, z)].getHardness();
    }

    @Override
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        return BlockProperties.ORES[world.getBlockMetadata(x, y, z)].getResistance();
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List blocks)
    {
        for (int i = 0; i < EnumMetalMaterial.ORES.length; i++)
        {
            blocks.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    public int getRenderType()
    {
        return Proxy.get().getRenderers().RENDERER_COLOURED_BLOCK;
    }

    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z) instanceof BlockMetal)
        {
            int r = (int) (getMaterial(world.getBlockMetadata(x, y, z)).getColour().getX() * 255);
            int g = (int) (getMaterial(world.getBlockMetadata(x, y, z)).getColour().getY() * 255);
            int b = (int) (getMaterial(world.getBlockMetadata(x, y, z)).getColour().getZ() * 255);
            return ((r & 0x0FF) << 16) | ((g & 0x0FF) << 8) | (b & 0x0FF);
        }

        return 0xFFFFFF;
    }

    public EnumMetalMaterial getMaterial(int meta)
    {
        return EnumMetalMaterial.values()[meta];
    }
}
