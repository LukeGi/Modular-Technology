package net.blep.modtech.blocks;

import net.blep.modtech.core.util.BlockProperties;
import net.blep.modtech.core.util.BlockTexture;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Kelan on 24/01/2016.
 */
public abstract class ModBlock extends Block
{
    protected BlockTexture textureConfiguration;

    public ModBlock(BlockProperties properties, String blockName)
    {
        super(properties.getMaterial());
        properties.apply(this, blockName, null);
    }

    public ModBlock(Material material)
    {
        super(material);
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return textureConfiguration.getIcon(side, meta);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        textureConfiguration.setupIcons(iconRegister);
    }

    public Block setTextureConfiguration(BlockTexture texture)
    {
        this.textureConfiguration = texture;
        return this;
    }

    public void makeFront(int side, World world, int x, int y, int z) {
        this.textureConfiguration.setFront(side, world, x, y, z);
    }
}
