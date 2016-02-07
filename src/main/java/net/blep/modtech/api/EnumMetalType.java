package net.blep.modtech.api;

import net.blep.modtech.core.util.Names;

/**
 * Created by Kelan on 03/02/2016.
 */
public enum EnumMetalType
{
    INGOT("ingot"),
    DUST_IMPURE("dustImpure"),
    DUST_PURE("dust"),
    NUGGET("nugget");

    private String unlocalizedName;
    private String localizedName;

    EnumMetalType(String name)
    {
        this.unlocalizedName = name;
        localizedName = Names.METAL_TYPES[ordinal()];
    }

    public String getUnlocalizedName()
    {
        return unlocalizedName;
    }

    public String getLocalizedName()
    {
        return localizedName;
    }
}
