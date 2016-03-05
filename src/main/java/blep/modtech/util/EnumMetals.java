package blep.modtech.util;

import net.minecraft.util.IStringSerializable;

import java.util.Arrays;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public enum EnumMetals implements IStringSerializable
{
    // Vanilla
    IRON("Iron", EnumMetalProperty.VANILLA, EnumMetalProperty.NUGGET),
    GOLD("Gold", EnumMetalProperty.VANILLA),

    COPPER("Copper", EnumMetalProperty.NUGGET, EnumMetalProperty.INGOT, EnumMetalProperty.ORE, EnumMetalProperty.BLOCK),
    TIN("Tin", EnumMetalProperty.NUGGET, EnumMetalProperty.INGOT, EnumMetalProperty.ORE, EnumMetalProperty.BLOCK);

    private String metalName;
    private EnumMetalProperty[] properties;

    EnumMetals(String name, EnumMetalProperty... propertiesIn)
    {
        metalName = name;
        properties = propertiesIn;
    }

    public static EnumMetals byMeta(int meta)
    {
        if (meta < 0 || meta > values().length)
            meta = 0;
        return values()[meta];
    }

    public boolean hasProperty(EnumMetalProperty propertyIn)
    {
        return Arrays.asList(properties).contains(propertyIn);
    }

    public boolean hasProperties(EnumMetalProperty... propertiesIn)
    {
        return Arrays.asList(properties).containsAll(Arrays.asList(propertiesIn));
    }

    public int getMeta()
    {
        return ordinal();
    }

    @Override
    public String getName()
    {
        return metalName;
    }

    public String getUnlocalizedName()
    {
        return this.metalName.toLowerCase();
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
