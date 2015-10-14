package net.blep.modularTechnology.common.core.oredict;

import net.blep.modularTechnology.common.core.ModContent;
import net.blep.modularTechnology.common.tech.EnumMetalMaterial;

import static net.minecraftforge.oredict.OreDictionary.*;
/**
 * @author Kelan
 */
public class OreDict extends ModContent
{
    public static void init()
    {
        registerOre("dustStone", getTechModItems().dustStone);
    }
}
