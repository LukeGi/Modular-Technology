package blep.modtech.machine.farm.cobblestone;

import blep.modtech.core.ContainerBase;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ContainerCobbleFarm extends ContainerBase<TileEntityCobbleFarm>
{
    public ContainerCobbleFarm(InventoryPlayer inventory, TileEntityCobbleFarm tile)
    {
        super(inventory, tile, 8, 8, 8, 168, 8, 226, 8, 18);
    }
}
