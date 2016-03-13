package blep.modtech.multiblock;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class MultiblockPattern
{
    protected Map<Block[], BlockPos[]> library = Maps.newHashMap();
    private String name;

    public MultiblockPattern(String name)
    {
        this.name = name;
    }

    /**
     * Adds the given block(s) to the library, and associates them with the given position(s).
     * This method will not work if the given blocks match a set of blocks in the library.
     * This method will not work if any of the given positions match any of the positions in the library.
     *
     * @param blocks The blocks you want to add to the library
     * @param poses  The positions you want to associate with the blocks
     */
    public void addToLibrary(Block[] blocks, BlockPos[] poses)
    {
        if (library.keySet().contains(blocks))
            return;
        else
            for (BlockPos[] posArr : library.values())
                for (BlockPos pos : poses)
                    if (Arrays.asList(posArr).contains(pos))
                        return;
        library.put(blocks, poses);
    }

    /**
     * This method checks if the given Block Position can be found in the library.
     *
     * @param pos The Block Position that you want to search for in the library.
     * @return True if is found, otherwise False.
     */
    public boolean contains(BlockPos pos)
    {
        for (BlockPos[] posArr : library.values())
            if (Arrays.asList(posArr).contains(pos))
                return true;
        return false;
    }

    /**
     * This method checks if the given Array of Block Positions can be found in the library.
     *
     * @param posArr The Array of Block Positions that you want to search for in the library.
     * @return True if is found, otherwise False.
     */
    public boolean contains(BlockPos[] posArr)
    {
        return this.library.values().contains(posArr);
    }

    /**
     * This method checks if the given Block can be found in the library.
     *
     * @param block The Block that you want to search for in the library.
     * @return True if is found, otherwise False.
     */
    public boolean contains(Block block)
    {
        for (Block[] bArr : library.keySet())
            if (Arrays.asList(bArr).contains(block)) return true;
        return false;
    }

    /**
     * This method checks if the given Array of Block Positions can be found in the library.
     *
     * @param blockArr The Array of Block Positions that you want to search for in the library.
     * @return True if found, otherwise False.
     */
    public boolean contains(Block[] blockArr)
    {
        return library.keySet().contains(blockArr);
    }

    /**
     * This method returns the BlockPositions for the given block.
     *
     * @param block The block you want to search the library's library for
     * @return BlockPositions of the given block, if the block is in the library.
     */
    public BlockPos[] getPositionsFromBlock(Block block)
    {
        for (Block[] bArr : library.keySet())
            if (Arrays.asList(bArr).contains(block))
                return library.get(bArr);
        return null;
    }

    /**
     * This method checks the library for the given Block Array, and if it is found,
     * it returns the associated Block Positions
     *
     * @param blockArr The array of blocks you want to search the library for.
     * @return Null if the given block array isn't in the library, and the associated Block Positions if it is.
     */
    public BlockPos[] getPositionsFromBlockArr(Block[] blockArr)
    {
        if (library.keySet().contains(blockArr))
            return library.get(blockArr);
        return null;
    }

    /**
     * This method returns the default Block for the give Block Position.
     *
     * @param pos The Block Position that you want to get the default Block for.
     * @return The Default Block for the given position of null, if the Block is not in the library.
     */
    public Block getDefaultBlockForPosition(BlockPos pos)
    {
        if (this.contains(pos))
            for (int i = 0; i < library.keySet().size(); i++)
                if (Arrays.asList(library.get(library.keySet().toArray()[i])).contains(pos))
                    return ((Block[]) library.keySet().toArray())[0];
        return null;
    }

    public Map<Block[], BlockPos[]> getLibrary()
    {
        return library;
    }

    public String getName()
    {
        return name;
    }
}
