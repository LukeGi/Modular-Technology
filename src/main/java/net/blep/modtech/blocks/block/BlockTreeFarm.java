package net.blep.modtech.blocks.block;

import net.blep.modtech.api.BlockProperties;
import net.blep.modtech.blocks.ModTileBlock;
import net.blep.modtech.blocks.tileentity.TileEntityTreeFarm;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class BlockTreeFarm  extends ModTileBlock {
    public BlockTreeFarm() {
        super(BlockProperties.GENERIC_MACHINE, "treeFarm");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityTreeFarm();
    }
}
