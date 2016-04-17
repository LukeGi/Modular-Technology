package blep.modtech.machine.generator.lunar;

import blep.modtech.machine.generator.solar.TileEntityGeneratorSolar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ContainerGeneratorLunar extends Container
{

    public ContainerGeneratorLunar(InventoryPlayer inventory, TileEntityGeneratorLunar tileEntityNew)
    {

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
}
