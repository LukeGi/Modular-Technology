package modulartechnology.item;

import cpw.mods.fml.relauncher.Side;
import modulartechnology.networking.ModularTechnologyPacketHandler;
import modulartechnology.networking.MyMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBase extends Item {

    @Override
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        return super.onItemUse(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        if(p_77659_2_.isRemote)
            ModularTechnologyPacketHandler.INSTANCE.sendToServer(new MyMessage((int)p_77659_3_.getPosition(0).yCoord));
        return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
    }
}
