package blep.modtech.multiblock;

import blep.modtech.block.ModtechBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class MuPCobbleGen extends MultiblockPattern
{
    public static MuPCobbleGen cobblegen = new MuPCobbleGen().init();

    public MuPCobbleGen()
    {
        super("cobblegen");
    }

    public MuPCobbleGen init()
    {
        addToLibrary(ModtechBlocks.COBBLE_GENERATOR.block, getListFromArray(new BlockPos[]{new BlockPos(-2, -1, -1), new BlockPos(-1, -1, -1), new BlockPos(0, -1, -1), new BlockPos(1, -1, -1), new BlockPos(2, -1, -1), new BlockPos(-2, -1, 0), new BlockPos(-1, -1, 0), new BlockPos(0, -1, 0), new BlockPos(1, -1, 0), new BlockPos(2, -1, 0), new BlockPos(-2, -1, 1), new BlockPos(-1, -1, 1), new BlockPos(0, -1, 1), new BlockPos(1, -1, 1), new BlockPos(2, -1, 1), new BlockPos(-2, 0, -1), new BlockPos(-1, 0, -1), new BlockPos(0, 0, -1), new BlockPos(1, 0, -1), new BlockPos(2, 0, -1), new BlockPos(-2, 0, 0), new BlockPos(0, 0, 0), new BlockPos(2, 0, 0), new BlockPos(-2, 0, 1), new BlockPos(-1, 0, 1), new BlockPos(0, 0, 1), new BlockPos(1, 0, 1), new BlockPos(2, 0, 1), new BlockPos(-2, 1, -1), new BlockPos(-1, 1, -1), new BlockPos(0, 1, -1), new BlockPos(1, 1, -1), new BlockPos(2, 1, -1), new BlockPos(-2, 1, 0), new BlockPos(-1, 1, 0), new BlockPos(0, 1, 0), new BlockPos(1, 1, 0), new BlockPos(2, 1, 0), new BlockPos(-2, 1, 1), new BlockPos(-1, 1, 1), new BlockPos(0, 1, 1), new BlockPos(1, 1, 1), new BlockPos(2, 1, 1),}));
        addToLibrary(Blocks.water, new BlockPos(-1, 0, 0));
        addToLibrary(Blocks.lava, new BlockPos(1, 0, 0));
        return this;
    }

    public BlockPos getChest()
    {
        return new BlockPos(0, 0, 2);
    }
}
