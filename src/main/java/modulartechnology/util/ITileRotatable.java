package modulartechnology.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author TheEpicTekkit
 */
public interface ITileRotatable
{
    ForgeDirection getOrientation();

    boolean[] getValidOrientations();

    void setOrientation(ForgeDirection orientation);

    boolean onRotated(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ);
}
