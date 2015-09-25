package net.blep.modularTechnology.common.magic;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.blep.modularTechnology.common.core.util.IconHelper;
import net.blep.modularTechnology.common.tech.tabs.CreativeTabHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public abstract class MagicBlockContainer<T extends TileEntity> extends BlockContainer
{
    protected MagicBlockContainer(Material m)
    {
        super(m);
        if (showInCreativeTab())
            setCreativeTab(CreativeTabHandler.tabMagic);
    }

    @Override
    public Block setBlockName(String bn)
    {
        if (shouldRegisterInNameSet())
            GameRegistry.registerBlock(this, bn);
        return super.setBlockName(bn);
    }

    protected boolean shouldRegisterInNameSet()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir)
    {
        blockIcon = IconHelper.forBlock(ir, this);
    }

    public boolean showInCreativeTab()
    {
        return true;
    }

    @Override
    public abstract T createNewTileEntity(World world, int meta);
}