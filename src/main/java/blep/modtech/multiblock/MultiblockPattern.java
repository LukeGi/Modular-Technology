package blep.modtech.multiblock;

import blep.modtech.block.ModtechBlocks;
import blep.modtech.util.LogHelper;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class MultiblockPattern
{
    public static final MultiblockPattern treefarm = new MultiblockPattern("treefarm")
            .addToLibrary(ModtechBlocks.TREE_FARM.block, new BlockPos(0, 0, 0))
            .addToLibrary(Blocks.chest, new BlockPos(0, 1, 0))
            .addToLibrary(Blocks.hopper, new BlockPos(0, 2, 0));

    protected Map<List<Block>, List<BlockPos>> library = Maps.newHashMap();
    private String name;

    public MultiblockPattern(String name)
    {
        this.name = name;
    }

    public MultiblockPattern addToLibrary(Block b, List<BlockPos> pList)
    {
        List<Block> bList = new ArrayList<Block>(1);
        bList.add(b);
        return addToLibrary(bList, pList);
    }

    public MultiblockPattern addToLibrary(List<Block> bList, BlockPos p)
    {
        List<BlockPos> pList = new ArrayList<BlockPos>(1);
        pList.add(p);
        return addToLibrary(bList, pList);
    }

    public MultiblockPattern addToLibrary(Block b, BlockPos p)
    {
        List<Block> bList = new ArrayList<Block>(1);
        List<BlockPos> pList = new ArrayList<BlockPos>(1);
        bList.add(b);
        pList.add(p);
        return addToLibrary(bList, pList);
    }

    /**
     * Adds the given block(s) to the library, and associates them with the given position(s).
     * This method will not work if the given blocks match a set of blocks in the library.
     * This method will not work if any of the given positions match any of the positions in the library.
     *
     * @param bList The blocks you want to add to the library
     * @param pList The positions you want to associate with the blocks
     */
    public MultiblockPattern addToLibrary(List<Block> bList, List<BlockPos> pList)
    {
        if (getLibrary().keySet().contains(bList))
            return this;
        else
            for (List<BlockPos> libPList : getLibrary().values())
                for (BlockPos p : libPList)
                    if (pList.contains(p))
                    {
                        LogHelper.error("This BlockPosition is already in the library : \n" + p.toString());
                        return this;
                    }
        getLibrary().put(bList, pList);
        return this;
    }

    /**
     * This method checks if the given Block Position can be found in the library.
     *
     * @param p The Block Position that you want to search for in the library.
     * @return True if is found, otherwise False.
     */
    public boolean contains(BlockPos p)
    {
        for (List<BlockPos> pList : getLibrary().values())
            if (pList.contains(p))
                return true;
        return false;
    }

    /**
     * This method checks if the given Array of Block Positions can be found in the library.
     *
     * @param pList The Array of Block Positions that you want to search for in the library.
     * @return True if is found, otherwise False.
     */
    public boolean contains(List<BlockPos> pList)
    {
        return getLibrary().values().contains(pList);
    }

    /**
     * This method checks if the given Block can be found in the library.
     *
     * @param b The Block that you want to search for in the library.
     * @return True if is found, otherwise False.
     */
    public boolean contains(Block b)
    {
        for (List<Block> bList : getLibrary().keySet())
            if (bList.contains(b)) return true;
        return false;
    }

    /**
     * This method returns the BlockPositions for the given block.
     *
     * @param b The block you want to search the library's library for
     * @return BlockPositions of the given block, if the block is in the library.
     */
    public List<BlockPos> getPositionsFromBlock(Block b)
    {
        for (List<Block> bList : getLibrary().keySet())
            if (bList.contains(b))
                return getLibrary().get(bList);
        return null;
    }

    /**
     * This method checks the library for the given Block Array, and if it is found,
     * it returns the associated Block Positions
     *
     * @param bList The array of blocks you want to search the library for.
     * @return Null if the given block array isn't in the library, and the associated Block Positions if it is.
     */
    public List<BlockPos> getPositionsFromBlockArr(List<Block> bList)
    {
        if (getLibrary().keySet().contains(bList))
            return getLibrary().get(bList);
        return null;
    }

    /**
     * This method returns the default Block for the give Block Position.
     *
     * @param p The Block Position that you want to get the default Block for.
     * @return The Default Block for the given position of null, if the Block is not in the library.
     */
    public Block getDefaultBlockForPosition(BlockPos p)
    {
        if (this.contains(p))
            for (int i = 0; i < getLibrary().keySet().size(); i++)
                if (getLibrary().get(getLibrary().keySet().toArray()[i]).contains(p))
                    return ((List<Block>) getLibrary().keySet().toArray()[i]).get(0);

                else
                    LogHelper.error("BlockPosition " + p.toString() + ", Does not have a Block associate in this library.");
        return Blocks.air;
    }

    public boolean create(World world, BlockPos base)
    {
        boolean flag = true;
        for (List<BlockPos> pList : getLibrary().values())
            for (BlockPos p : pList)
                flag &= world.setBlockState(new BlockPos(base).add(p), getDefaultBlockForPosition(p).getDefaultState());
        return flag;
    }

    public Map<List<Block>, List<BlockPos>> getLibrary()
    {
        return library;
    }

    public String getName()
    {
        return name;
    }

    //TODO: add CheckIfFormed Method.

    public boolean isSpace(World world, BlockPos pos)
    {
        boolean flag = true;
        for (List<BlockPos> pList : getLibrary().values())
            for (BlockPos p : pList)
                flag &= world.isAirBlock(new BlockPos(pos).add(p));
        return flag;
    }
}
