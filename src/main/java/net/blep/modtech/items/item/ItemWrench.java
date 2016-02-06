package net.blep.modtech.items.item;

import net.blep.modtech.api.IWrenchable;
import net.blep.modtech.core.util.HelpfulMethods;
import net.blep.modtech.items.ModItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.input.Keyboard;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class ItemWrench extends ModItem {
    public ItemWrench() {
        super("wrench");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            Block block = world.getBlock(x, y, z);

            if ((Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)))
                side = ForgeDirection.OPPOSITES[side];

            if (block instanceof IWrenchable)
                ((IWrenchable) block).makeFront(side, world, x, y, z);

            if ((block instanceof BlockChest || block instanceof IWrenchable) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && !Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)))
                HelpfulMethods.removeBlockSafely(world, x, y, z, true);
        }
        return true;
    }
}
