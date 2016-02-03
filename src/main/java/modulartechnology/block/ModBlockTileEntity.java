package modulartechnology.block;

import net.minecraft.block.ITileEntityProvider;

/**
 * @author TheEpicTekkit
 */
public abstract class ModBlockTileEntity extends ModBlock implements ITileEntityProvider
{
//    protected ModBlockTileEntity(Material material, String name, float resistance, float hardness, String toolType, int HarvestLevel, String... textures)
//    {
//        super(material, name, resistance, hardness, toolType, HarvestLevel, CreativeTabHandler.tabModtech, textures);
//        this.isBlockContainer = true;
//    }
//
//    /**
//     * Called whenever the block is added into the world. Args: world, x, y, z
//     */
//    public void onBlockAdded(World world, int x, int y, int z)
//    {
//        super.onBlockAdded(world, x, y, z);
//    }
//
//    public void breakBlock(World world, int x, int y, int z, Block block, int partialTick)
//    {
//        super.breakBlock(world, x, y, z, block, partialTick);
//        world.removeTileEntity(x, y, z);
//    }
//
//    public boolean onBlockEventReceived(World world, int x, int y, int z, int par1, int par2)
//    {
//        super.onBlockEventReceived(world, x, y, z, par1, par2);
//        TileEntity tileentity = world.getTileEntity(x, y, z);
//        return tileentity != null ? tileentity.receiveClientEvent(par1, par2) : false;
//    }
}
