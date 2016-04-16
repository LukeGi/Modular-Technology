package blep.modtech.machine.generator.solar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ContainerGeneratorSolar extends Container
{

    public ContainerGeneratorSolar(InventoryPlayer inventory, TileEntityGeneratorSolar tileEntityNew)
    {

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
}
