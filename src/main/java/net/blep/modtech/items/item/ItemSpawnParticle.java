package net.blep.modtech.items.item;

import net.blep.modtech.client.particle.EntityFXBubble;
import net.blep.modtech.core.ModTech;
import net.blep.modtech.core.util.ItemTexture;
import net.blep.modtech.items.ModItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class ItemSpawnParticle extends ModItem {
    public ItemSpawnParticle() {
        super("spawnParticle");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            ForgeDirection d = ForgeDirection.getOrientation(side);
            for (int i = 0; i < 100; i++) //this spawns them not gonna do it like this forever... just for testing
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityFXBubble(world, x + d.offsetX + 0.5, y + d.offsetY + 0.5, z + d.offsetZ + 0.5, 0, 1, 0));
        }
        return super.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }
}
