package net.blep.modularTechnology.common.magic.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.blep.modularTechnology.common.core.util.IconHelper;
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
    }

    @Override
    public Block setBlockName(String bn)
    {
        MagicBlockHandler.registerMagicBlock(this, bn);
        return super.setBlockName(bn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir)
    {
        blockIcon = IconHelper.forBlock(ir, this);
    }
}
