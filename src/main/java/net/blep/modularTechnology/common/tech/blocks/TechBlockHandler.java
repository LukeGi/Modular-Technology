package net.blep.modularTechnology.common.tech.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.blep.modularTechnology.common.tech.EnumMetalMaterial;
import net.blep.modularTechnology.common.tech.blocks.block.BlockLogic;
import net.blep.modularTechnology.common.tech.blocks.block.BlockMetal;
import net.blep.modularTechnology.common.tech.blocks.block.BlockOre;
import net.blep.modularTechnology.common.tech.blocks.block.BlockRedstoneInput;
import net.blep.modularTechnology.common.tech.blocks.block.machines.BlockCrusher;
import net.blep.modularTechnology.common.tech.blocks.tileentity.TileEntityCrusher;
import net.blep.modularTechnology.common.core.ModContent;
import net.blep.modularTechnology.common.tech.blocks.tileentity.TileEntityLogic;
import net.minecraft.block.Block;

/**
 * @author TheEpicTekkit
 */
public class TechBlockHandler extends ModContent
{
    //----------------================ ORES ================----------------\\
    public static final Block oreAluminium = new BlockOre(EnumMetalMaterial.ALUMINIUM);
    public static final Block oreChromium = new BlockOre(EnumMetalMaterial.CHROMIUM);
    public static final Block oreCopper = new BlockOre(EnumMetalMaterial.COPPER);
    public static final Block oreLead = new BlockOre(EnumMetalMaterial.LEAD);
    public static final Block oreMagnesium = new BlockOre(EnumMetalMaterial.MAGNESIUM);
    public static final Block oreManganese = new BlockOre(EnumMetalMaterial.MANGANESE);
    public static final Block oreMolybdenum = new BlockOre(EnumMetalMaterial.MOLYBDENUM);
    public static final Block orePlatinum = new BlockOre(EnumMetalMaterial.PLATINUM);
    public static final Block oreSilver = new BlockOre(EnumMetalMaterial.SILVER);
    public static final Block oreTin = new BlockOre(EnumMetalMaterial.TIN);
    public static final Block oreTitanium = new BlockOre(EnumMetalMaterial.TITANIUM);
    public static final Block oreYttrium = new BlockOre(EnumMetalMaterial.YTTRIUM);
    public static final Block oreZinc = new BlockOre(EnumMetalMaterial.ZINC);
    public static final Block oreZirconium = new BlockOre(EnumMetalMaterial.ZIRCONIUM);

    //----------------================ METAL BLOCKS ================----------------\\
    public static final Block blockAluminium = new BlockMetal(EnumMetalMaterial.ALUMINIUM);
    public static final Block blockChromium = new BlockMetal(EnumMetalMaterial.CHROMIUM);
    public static final Block blockCopper = new BlockMetal(EnumMetalMaterial.COPPER);
    public static final Block blockLead = new BlockMetal(EnumMetalMaterial.LEAD);
    public static final Block blockMagnesium = new BlockMetal(EnumMetalMaterial.MAGNESIUM);
    public static final Block blockManganese = new BlockMetal(EnumMetalMaterial.MANGANESE);
    public static final Block blockMolybdenum = new BlockMetal(EnumMetalMaterial.MOLYBDENUM);
    public static final Block blockPlatinum = new BlockMetal(EnumMetalMaterial.PLATINUM);
    public static final Block blockSilver = new BlockMetal(EnumMetalMaterial.SILVER);
    public static final Block blockTin = new BlockMetal(EnumMetalMaterial.TIN);
    public static final Block blockTitanium = new BlockMetal(EnumMetalMaterial.TITANIUM);
    public static final Block blockYttrium = new BlockMetal(EnumMetalMaterial.YTTRIUM);
    public static final Block blockZinc = new BlockMetal(EnumMetalMaterial.ZINC);
    public static final Block blockZirconium = new BlockMetal(EnumMetalMaterial.ZIRCONIUM);

    //----------------================ ALLOYS BLOCKS ================----------------\\
    public static final Block blockBronze = new BlockMetal(EnumMetalMaterial.BRONZE);
    public static final Block blockSteel = new BlockMetal(EnumMetalMaterial.STEEL);
    public static final Block blockZhyconite = new BlockMetal(EnumMetalMaterial.ZHYCONITE);

    //----------------================ MACHINES: ORE PROCESSING ================----------------\\
    public static final Block blockCrusher = new BlockCrusher().setSubFolder("machines");


    //----------------================ UTILITY MACHINES AND BLOCKS ================----------------\\
//    public static final Block blockLogic = new BlockLogic();
//    public static final Block blockRedstoneInput = new BlockRedstoneInput();
}
