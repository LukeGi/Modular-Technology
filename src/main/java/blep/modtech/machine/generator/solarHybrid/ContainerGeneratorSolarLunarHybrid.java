package blep.modtech.machine.generator.solarHybrid;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ContainerGeneratorSolarLunarHybrid extends Container
{
    public ContainerGeneratorSolarLunarHybrid(InventoryPlayer inventory, TileEntityGeneratorSolarLunarHybrid tileEntityNew)
    {
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
}
