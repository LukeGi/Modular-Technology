package com.blep.modularTechnology.core.common.blocks.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.blep.modularTechnology.core.common.ModularTechnology;
import com.blep.modularTechnology.core.common.util.IRegisterable;
import net.blep.modularTechnology.common.tech.tabs.CreativeTabHandler;
import net.blep.modularTechnology.common.core.ModContent;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public abstract class ModBlock //extends Block implements IRegisterable
{
//    protected final String name;
//    protected String harvestTool;
//    protected float resistance;
//    protected float hardness;
//    protected int harvestLevel;
//    protected String subFolder = "";
//    protected boolean useGetIcon;
//
//    /**
//     * Adding less than 6 icons to this will cause it to only use the first texture as every side.
//     */
//    protected String[] textures;
//    protected IIcon[] icons = new IIcon[6];
//
//    protected ModBlock(Material material, String name, String resName, float resistance, float hardness, String toolType, int harvestLevel, CreativeTabs tab, String... textures)
//    {
//        super(material);
//        this.name = name;
//        this.textures = textures.length >= 6 ? new String[]{textures[0], textures[1], textures[2], textures[3], textures[4], textures[5]} : textures;
//        setBlockName(this.getName());
//        setResistance(this.resistance = resistance);
//        setHardness(this.hardness = hardness);
//        setHarvestLevel(this.harvestTool = toolType, this.harvestLevel = harvestLevel);
//        setCreativeTab(tab);
//        setGetIcon(true);
//    }
//
//    public ModBlock setSubFolder(String folder)
//    {
//        if (folder == null)
//        {
//            this.subFolder = "";
//        }
//        else
//        {
//            this.subFolder = folder;
//
//            if (!this.subFolder.endsWith("/"))
//            {
//                this.subFolder += "/";
//            }
//        }
//        return this;
//    }
//
//    public ModBlock setGetIcon(boolean useGetIcon)
//    {
//        this.useGetIcon = useGetIcon;
//        return this;
//    }
//
//    @SideOnly(Side.CLIENT)
//    public IIcon getIcon(int side, int meta)
//    {
//        if (useGetIcon)
//        {
//            return icons[side];
//        }
//        else
//        {
//            return blockIcon;
//        }
//    }
//
//    @SideOnly(Side.CLIENT)
//    public void registerBlockIcons(IIconRegister register)
//    {
//        if (textures.length == 6)
//        {
//            for (int i = 0; i < 6; i++)
//            {
//                icons[i] = register.registerIcon(ModularTechnology.RESOURCE_PREFIX + this.subFolder + textures[i]);
//            }
//        }
//        else
//        {
//            blockIcon = register.registerIcon(ModularTechnology.RESOURCE_PREFIX + this.subFolder + textures[0]);
//
//            for (int i = 0; i < 6; i++)
//            {
//                icons[i] = blockIcon;
//            }
//        }
//    }
//
//    protected ModBlock(Material material, String name, float resistance, float hardness, String toolType, int harvestLevel, CreativeTabs tab, String... textures)
//    {
//        this(material, name, name, resistance, hardness, toolType, harvestLevel, tab, textures);
//    }
//
//    protected ModBlock(Material material, String name, CreativeTabs tab)
//    {
//        this(material, name, 5f, 5f, null, -1, tab);
//    }
//
//    protected ModBlock(String name, CreativeTabs tab)
//    {
//        this(new Material(MapColor.blueColor), name, tab);
//    }
//
//    public String getName()
//    {
//        return name;
//    }
//
//    public String getHarvestTool()
//    {
//        return harvestTool;
//    }
//
//    public float getResistance()
//    {
//        return resistance;
//    }
//
//    public float getHardness()
//    {
//        return hardness;
//    }
//
//    public int getHarvestLevel()
//    {
//        return harvestLevel;
//    }
//
//    public static CreativeTabHandler getModTabs()
//    {
//        return ModContent.getModTabs();
//    }
//
//    public void register()
//    {
//        GameRegistry.registerBlock(this, getUnlocalizedName());
//    }
}
