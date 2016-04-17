package blep.modtech.machine.generator.solarHybrid;

import blep.modtech.ModTech;
import blep.modtech.block.BlockMod;
import blep.modtech.machine.GuiHandlerModTech;
import blep.modtech.machine.generator.BlockGenerator;
import blep.modtech.machine.generator.lunar.TileEntityGeneratorLunar;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class BlockGeneratorSolarLunarHybrid extends BlockGenerator
{
    @Override
    protected void openGui(World world, EntityPlayer player, BlockPos pos)
    {
        player.openGui(ModTech.INSTANCE, GuiHandlerModTech.GUIID_SONARGEN, world, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void registerTileEntity()
    {
        GameRegistry.registerTileEntity(TileEntityGeneratorSolarLunarHybrid.class, getUnlocalizedName());
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityGeneratorSolarLunarHybrid();
    }
}
