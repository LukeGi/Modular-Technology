package net.blep.modtech.api;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.crafting.IRecipe;

/**
 * Created by Kelan on 25/01/2016.
 */
public enum BlockProperties
{
    GENERIC_MACHINE(Material.rock, CreativeTabs.tabBlock, Block.soundTypeMetal, "pickaxe", 1, 1.75F, 4.25F),
    METAL_COPPER(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.COPPER.getMiningLevel(), EnumMetalMaterial.COPPER.getBlockHardness(), EnumMetalMaterial.COPPER.getBlockResistance()),
    METAL_TIN(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.TIN.getMiningLevel(), EnumMetalMaterial.TIN.getBlockHardness(), EnumMetalMaterial.TIN.getBlockResistance()),
    METAL_LEAD(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.LEAD.getMiningLevel(), EnumMetalMaterial.LEAD.getBlockHardness(), EnumMetalMaterial.LEAD.getBlockResistance()),
    METAL_SILVER(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.SILVER.getMiningLevel(), EnumMetalMaterial.SILVER.getBlockHardness(), EnumMetalMaterial.SILVER.getBlockResistance()),
    METAL_PLATINUM(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.PLATINUM.getMiningLevel(), EnumMetalMaterial.PLATINUM.getBlockHardness(), EnumMetalMaterial.PLATINUM.getBlockResistance()),
    METAL_CHROMIUM(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.CHROMIUM.getMiningLevel(), EnumMetalMaterial.CHROMIUM.getBlockHardness(), EnumMetalMaterial.CHROMIUM.getBlockResistance()),
    METAL_ZINC(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.ZINC.getMiningLevel(), EnumMetalMaterial.ZINC.getBlockHardness(), EnumMetalMaterial.ZINC.getBlockResistance()),
    METAL_MAGNESIUM(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.MAGNESIUM.getMiningLevel(), EnumMetalMaterial.MAGNESIUM.getBlockHardness(), EnumMetalMaterial.MAGNESIUM.getBlockResistance()),
    METAL_MANGANESE(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.MANGANESE.getMiningLevel(), EnumMetalMaterial.MANGANESE.getBlockHardness(), EnumMetalMaterial.MANGANESE.getBlockResistance()),
    METAL_YTTRIUM(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.YTTRIUM.getMiningLevel(), EnumMetalMaterial.YTTRIUM.getBlockHardness(), EnumMetalMaterial.YTTRIUM.getBlockResistance()),
    METAL_ZIRCONIUM(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.ZIRCONIUM.getMiningLevel(), EnumMetalMaterial.ZIRCONIUM.getBlockHardness(), EnumMetalMaterial.ZIRCONIUM.getBlockResistance()),
    METAL_ALUMINIUM(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.ALUMINIUM.getMiningLevel(), EnumMetalMaterial.ALUMINIUM.getBlockHardness(), EnumMetalMaterial.ALUMINIUM.getBlockResistance()),
    METAL_MOLYBDENUM(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.MOLYBDENUM.getMiningLevel(), EnumMetalMaterial.MOLYBDENUM.getBlockHardness(), EnumMetalMaterial.MOLYBDENUM.getBlockResistance()),
    METAL_TITANIUM(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.TITANIUM.getMiningLevel(), EnumMetalMaterial.TITANIUM.getBlockHardness(), EnumMetalMaterial.TITANIUM.getBlockResistance()),
    METAL_STEEL(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.STEEL.getMiningLevel(), EnumMetalMaterial.STEEL.getBlockHardness(), EnumMetalMaterial.STEEL.getBlockResistance()),
    METAL_BRONZE(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.BRONZE.getMiningLevel(), EnumMetalMaterial.BRONZE.getBlockHardness(), EnumMetalMaterial.BRONZE.getBlockResistance()),
    METAL_ZHYCONITE(Material.rock, CreativeTabs.tabBlock, Block.soundTypeStone, "pickaxe", EnumMetalMaterial.ZHYCONITE.getMiningLevel(), EnumMetalMaterial.ZHYCONITE.getBlockHardness(), EnumMetalMaterial.ZHYCONITE.getBlockResistance());

    private final Material material;
    private final CreativeTabs creativeTab;
    private final SoundType stepSound;
    private final String harvestTool;
    private final int harvestLevel;
    private final float hardness;
    private final float resistance;

    public static final BlockProperties[] ORES = new BlockProperties[]{METAL_COPPER, METAL_TIN, METAL_LEAD, METAL_SILVER, METAL_PLATINUM, METAL_CHROMIUM, METAL_ZINC, METAL_MAGNESIUM, METAL_MANGANESE, METAL_YTTRIUM, METAL_ZIRCONIUM, METAL_ALUMINIUM, METAL_MOLYBDENUM, METAL_TITANIUM};
    public static final BlockProperties[] ALLOYS = new BlockProperties[]{METAL_STEEL, METAL_BRONZE, METAL_ZHYCONITE};

    private BlockProperties(Material material, CreativeTabs creativeTab, SoundType stepSound, String harvestTool, int harvestLevel, float hardness, float resistance)
    {
        this.material = material;
        this.creativeTab = creativeTab;
        this.stepSound = stepSound;
        this.harvestTool = harvestTool;
        this.harvestLevel = harvestLevel;
        this.hardness = hardness;
        this.resistance = resistance;
    }

    public void apply(Block block, String blockName, IRecipe[] recipes)
    {
        block.setBlockName(blockName);
        block.setBlockTextureName(blockName);
        block.setCreativeTab(creativeTab);
        block.setHardness(hardness);
        block.setResistance(resistance);
        block.setStepSound(stepSound);
        block.setHarvestLevel(harvestTool, harvestLevel);
    }

    public Material getMaterial()
    {
        return material;
    }

    public CreativeTabs getCreativeTab()
    {
        return creativeTab;
    }

    public SoundType getStepSound()
    {
        return stepSound;
    }

    public String getHarvestTool()
    {
        return harvestTool;
    }

    public int getHarvestLevel()
    {
        return harvestLevel;
    }

    public float getHardness()
    {
        return hardness;
    }

    public float getResistance()
    {
        return resistance;
    }
}
