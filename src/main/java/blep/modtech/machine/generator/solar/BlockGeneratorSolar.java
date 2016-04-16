package blep.modtech.machine.generator.solar;

import blep.modtech.ModTech;
import blep.modtech.machine.farm.GuiHandlerModTech;
import blep.modtech.machine.generator.BlockGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class BlockGeneratorSolar extends BlockGenerator
{
    private BlockGeneratorSolar()
    {
    }

    @Override
    protected void openGui(World world, EntityPlayer player, BlockPos pos)
    {
        player.openGui(ModTech.INSTANCE, GuiHandlerModTech.GUIID_SOLARGEN, world, pos.getX(), pos.getY(), pos.getZ());
    }

    public static BlockGeneratorSolar create()
    {
        return new BlockGeneratorSolar();
    }

    @Override
    public void registerTileEntity()
    {
        GameRegistry.registerTileEntity(TileEntityGeneratorSolar.class, getUnlocalizedName());
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return TileEntityGeneratorSolar.create();
    }
}
