package net.blep.modtech.core.util;

import net.blep.modtech.blocks.tileentity.TileEntityMachine;
import net.blep.modtech.blocks.tileentity.machines.*;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Kelan on 06/02/2016.
 */
public enum EnumMachineProperties
{
    FURNACE("furnace", TileEntityFurnace.class, HelpfulMethods.DEFAULT_DIRECTIONS),
    CRUSHER("crusher", TileEntityCrusher.class, HelpfulMethods.DEFAULT_DIRECTIONS),
    COMPRESSOR("compressor", TileEntityCompressor.class, HelpfulMethods.DEFAULT_DIRECTIONS),
    CENTRIFUGE("centrifuge", TileEntityCentrifuge.class, HelpfulMethods.DEFAULT_DIRECTIONS),
    ELECTROLYSER("electrolyser", TileEntityElectrolyser.class, HelpfulMethods.DEFAULT_DIRECTIONS),
    PURIFIER("purifier", TileEntityPurifier.class, HelpfulMethods.DEFAULT_DIRECTIONS),
    ENRICHER("enricher", TileEntityEnricher.class, HelpfulMethods.DEFAULT_DIRECTIONS),
    CHEMICAL_REACTOR("chemicalReactor", TileEntityChemicalReactor.class, HelpfulMethods.DEFAULT_DIRECTIONS),
    METAL_BENDER("metalBender", TileEntityMetalBender.class, HelpfulMethods.DEFAULT_DIRECTIONS),
    SLAG_FURNACE("slagFurace", TileEntitySlagFurnace.class, HelpfulMethods.DEFAULT_DIRECTIONS),
    ;

    private String name;
    private Class<? extends TileEntityMachine> tileClass;
    private ForgeDirection[] rotatableDirections;

    EnumMachineProperties(String name, Class<? extends TileEntityMachine> tileClass, ForgeDirection[] rotatableDirections)
    {
        this.name = name;
        this.tileClass = tileClass;
        this.rotatableDirections = rotatableDirections;
    }

    public String getName()
    {
        return name;
    }

    public Class<? extends TileEntityMachine> getTileClass()
    {
        return tileClass;
    }

    public ForgeDirection[] getRotatableDirections()
    {
        return rotatableDirections;
    }
}
