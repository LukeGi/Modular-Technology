package blep.modtech.machine.farm.treefarm;

import blep.modtech.core.ContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ContainerTreeFarm extends ContainerBase<TileEntityTreeFarm>
{
    public ContainerTreeFarm(InventoryPlayer inventory, TileEntityTreeFarm tile)
    {
        super(inventory, tile, 3, 9, 32, 86, 32, 144, 10, 23);
    }
}


