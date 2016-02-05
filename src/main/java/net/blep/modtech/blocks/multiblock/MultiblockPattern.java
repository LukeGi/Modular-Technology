package net.blep.modtech.blocks.multiblock;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kelan on 24/01/2016.
 */
public class MultiblockPattern
{
    private int xSize;
    private int ySize;
    private int zSize;
    private Map<Character, Integer> blockMappings;
    private char[][][] pattern;
    private boolean ignoreAirBlocks;

    public MultiblockPattern(int xSize, int ySize, int zSize, Map<Character, Integer> blockMappings, char[][][] pattern)
    {
        this(xSize, ySize, zSize, blockMappings, pattern, true);
    }

    public MultiblockPattern(int xSize, int ySize, int zSize, Map<Character, Integer> blockMappings, char[][][] pattern, boolean ignoreAirBlocks)
    {
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;
        this.blockMappings = blockMappings;
        this.pattern = pattern;
        this.ignoreAirBlocks = ignoreAirBlocks;
    }

    public static Map<Character, Integer> getMappings(Object...params)
    {
        Map<Character, Integer> result = new HashMap<Character, Integer>();

        Block currBlock = null;
        Character currChar = null;

        int i = 0;

        for (Object o : params)
        {
            boolean flag = i % 2 == 0;

            if (flag) currBlock = (Block) o;
            else
            {
                currChar = (Character) o;
                result.put(currChar, Block.getIdFromBlock(currBlock));
            }
            i++;
        }

        return result;
    }

    public Block getBlockFor(char c)
    {
        return Block.getBlockById(getMappings().get(c));
    }

    public Block getBlockFor(int x, int y, int z)
    {
        return getBlockFor(getPattern()[x][y][z]);
    }

    public Map<Character, Integer> getBlockMappings()
    {
        return blockMappings;
    }

    public char[][][] getPattern()
    {
        return pattern;
    }

    public int getxSize()
    {
        return xSize;
    }

    public int getySize()
    {
        return ySize;
    }

    public int getzSize()
    {
        return zSize;
    }

    public boolean ignoreAirBlocks()
    {
        return ignoreAirBlocks;
    }
}
