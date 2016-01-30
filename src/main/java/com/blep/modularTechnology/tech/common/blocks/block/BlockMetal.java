package com.blep.modularTechnology.tech.common.blocks.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.blep.modularTechnology.core.client.ClientProxy;
import com.blep.modularTechnology.core.common.ModularTechnology;
import com.blep.modularTechnology.core.common.blocks.block.ModBlock;
import net.blep.modularTechnology.common.tech.EnumMetalMaterial;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class BlockMetal extends ModBlock
{
//    public final EnumMetalMaterial material;
//
//    public BlockMetal(EnumMetalMaterial material)
//    {
//        super(Material.rock, "metalBlock" + material.getName(), "metalBlockBase", material.getBlockResistance(), material.getBlockHardness(), "pickaxe", material.getMiningLevel(), getModTabs().tabMetals);
//        this.material = material;
//        setGetIcon(false);
//        for (String s : material.getOreDictNames())
//            OreDictionary.registerOre("block" + s, this);
//    }
//
//    @SideOnly(Side.CLIENT)
//    public void registerBlockIcons(IIconRegister iconRegister)
//    {
//        blockIcon = iconRegister.registerIcon(ModularTechnology.RESOURCE_PREFIX + "metalBlockBase");
//    }
//
//    public int getRenderType()
//    {
//        return ClientProxy.renderID_ColouredBlock;
//    }
//
//    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
//    {
//        int r = 0;
//        int g = 0;
//        int b = 0;
//
//        if (world.getBlock(x, y, z) instanceof BlockMetal)
//        {
//            r = (int) (material.getColour().getX() * 255);
//            g = (int) (material.getColour().getY() * 255);
//            b = (int) (material.getColour().getZ() * 255);
//        }
//
//        return ((r & 0x0ff) << 16) | ((g & 0x0ff) << 8) | (b & 0x0ff);
//    }
}
