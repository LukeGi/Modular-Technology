package net.blep.modularTechnology.common.magic;

import net.blep.modularTechnology.common.magic.multiblocks.TE.TETreeFarm;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class BlockTreeFarm extends MagicBlockContainer
{
    public BlockTreeFarm()
    {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TETreeFarm();
    }
}
