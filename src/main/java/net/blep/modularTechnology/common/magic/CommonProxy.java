package net.blep.modularTechnology.common.magic;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.item.EntityItem;

import java.util.ArrayList;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class CommonProxy
{
    private ArrayList<EntityItem> actions = new ArrayList<EntityItem>();

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event)
    {
        if (!actions.isEmpty())
        {
            EntityItem current = actions.get(0);
            actions.remove(0);
            current.worldObj.spawnEntityInWorld(current);
        }
    }

    public void addItemEntityToSpawn(EntityItem item)
    {
        actions.add(item);
    }
}
