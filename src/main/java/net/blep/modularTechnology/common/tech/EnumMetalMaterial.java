package net.blep.modularTechnology.common.tech;

import net.blep.modularTechnology.common.core.util.Names;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import org.lwjgl.util.vector.Vector3f;

/**
 * @author TheEpicTekkit
 */
public enum EnumMetalMaterial
{
    COPPER(0, 0.7f, 1, new Vector3f(0.784313725F, 0.501960784F, 0.200000000F), 450, 16),
    TIN(1, 0.74f, 1, new Vector3f(0.400000000F, 0.501960784F, 0.501960784F), 430, 15),
    LEAD(2, 0.78f, 2, new Vector3f(0.341176471F, 0.349019608F, 0.380392157F), 523, 16),
    SILVER(3, 0.7f, 2, new Vector3f(0.752941176F, 0.752941176F, 0.752941176F), 130, 40),
    PLATINUM(4, 0.7f, 3, new Vector3f(0.815686275F, 0.815686275F, 0.878431373F), 543, 39),
    CHROMIUM(5, 0.7f, 2, new Vector3f(0.541176471F, 0.600000000F, 0.780392157F), 435, 23),
    ZINC(6, 0.7f, 2, new Vector3f(0.815686275F, 0.815686275F, 0.878431373F), 532, 45),
    MAGNESIUM(7, 0.7f, 2, new Vector3f(0.541176471F, 1.000000000F, 0.000000000F), 158, 43),
    MANGANESE(8, 0.7f, 2, new Vector3f(0.611764706F, 0.478431373F, 0.780392157F), 236, 34),
    YTTRIUM(9, 0.7f, 3, new Vector3f(0.580392157F, 1.000000000F, 1.000000000F), 435, 23),
    ZIRCONIUM(10, 0.7f, 3, new Vector3f(0.580392157F, 0.878431373F, 0.878431373F), 463, 41),
    ALUMINIUM(11, 0.7f, 2, new Vector3f(0.749019608F, 0.650980392F, 0.650980392F), 158, 23),
    MOLYBDENUM(12, 0.7f, 2, new Vector3f(0.329411765F, 0.709803922F, 0.709803922F), 634, 14),
    TITANIUM(13, 0.7f, 3, new Vector3f(0.713725490F, 0.686274509F, 0.662745098F), 870, 16),
    STEEL(100, 0.7f, 2, new Vector3f(0.878431373F, 0.874509804F, 0.858823529F), 1026, 23),
    BRONZE(101, 0.7f, 2, new Vector3f(0.803921569F, 0.498039216F, 0.196078431F), 750, 19),
    ZHYCONITE(102, 0.7f, 3, new Vector3f(0.619607843F, 0.834509804F, 0.902745098F), 3536, 153);


    private final String name;
    private final Item.ToolMaterial toolMaterial;
    private boolean isAlloy;
    private final float smeltingXP;
    private final float oreHardness;
    private final float oreResistance;
    private final float blockHardness;
    private final float blockResistance;
    private final int miningLevel;
    private final Vector3f colour;


    EnumMetalMaterial(int name, float smeltingXP, int miningLevel, Vector3f colour, int strength, int enchantability)
    {
        this.name = name < 100 ? Names.Items.METALS[name] : Names.Items.Alloys[name - 100];
        this.isAlloy = name >= 100;
        this.smeltingXP = smeltingXP;
        this.oreHardness = 4;
        this.miningLevel = miningLevel;
        this.oreResistance = (oreHardness * miningLevel) - miningLevel;
        this.blockHardness = oreHardness + miningLevel;
        this.blockResistance = oreResistance + miningLevel * 3;
        this.colour = colour;
        this.toolMaterial = EnumHelper.addToolMaterial("material" + name, miningLevel, strength, (miningLevel * oreHardness * strength) / 1000 > 2 ? (miningLevel * oreHardness * strength) / 1000 : 2, Math.round(oreHardness * strength / 1000) + 3, enchantability);
    }

    public String getName()
    {
        return name;
    }

    public boolean isAlloy()
    {
        return isAlloy;
    }

    public Item.ToolMaterial getToolMaterial()
    {
        return toolMaterial;
    }

    public float getSmeltingXP()
    {
        return smeltingXP;
    }

    public float getOreHardness()
    {
        return oreHardness;
    }

    public float getOreResistance()
    {
        return oreResistance;
    }

    public int getMiningLevel()
    {
        return miningLevel;
    }

    public float getBlockHardness()
    {
        return blockHardness;
    }

    public float getBlockResistance()
    {
        return blockResistance;
    }

    public Vector3f getColour()
    {
        return colour;
    }
}