package net.blep.modularTechnology.common.magic;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.blep.modularTechnology.common.core.util.IconHelper;
import net.blep.modularTechnology.common.tech.tabs.CreativeTabHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class MagicBlock extends Block
{
    public MagicBlock(Material par2Material)
    {
        super(par2Material);
        if (showInCreativeTab())
            setCreativeTab(CreativeTabHandler.tabMagic);
    }

    @Override
    public Block setBlockName(String bn)
    {
        GameRegistry.registerBlock(this, bn);
        return super.setBlockName(bn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir)
    {
        blockIcon = IconHelper.forBlock(ir, this);
    }

    boolean showInCreativeTab()
    {
        return true;
    }
}
