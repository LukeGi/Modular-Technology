package blep.modtech.proxy;

import blep.modtech.block.ModtechBlocks;
import blep.modtech.entity.fx.EntityBlueFlameFX;
import blep.modtech.item.ModtechItems;
import blep.modtech.reference.ModInfo;
import blep.modtech.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ClientProxy extends CommonProxy
{
    @SideOnly(Side.CLIENT)
    public static void spawnEntityFX(EntityFX effectIn)
    {
        Minecraft.getMinecraft().effectRenderer.addEffect(effectIn);
    }

    @Override
    public void registerBlocks()
    {
        super.registerBlocks();
//        for (ModtechBlocks b : ModtechBlocks.values())
//        {
//            Item block = Item.getItemFromBlock(b.block);
//            ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation(ModInfo.MOD_ID + ":" + b.getInternalName(), "inventory"));
//            LogHelper.info("Registered Block Renderer: " + b.getInternalName());
//        }

    }

    @Override
    public void registerItems()
    {
        super.registerItems();
//        for (ModtechItems i : ModtechItems.values())
//        {
//            ModelLoader.setCustomModelResourceLocation(i.item, 0, new ModelResourceLocation(ModInfo.MOD_ID + ":" + i.getInternalName(), "inventory"));
//            LogHelper.info("Registered Item Renderer: " + i.getInternalName());
//        }

    }

    @Override
    public void registerEntityFXStuff()
    {
        super.registerEntityFXStuff();
        MinecraftForge.EVENT_BUS.register(new EntityBlueFlameFX.TextureStitcherBreathFX());
    }

    @Override
    public void spawnParticle(EnumParticleTypes type, World world, BlockPos pos, double mx, double my, double mz, int[] extradata)
    {
        LogHelper.info("Spawning " + type.name());
        world.spawnParticle(type, pos.getX(), pos.getY(), pos.getZ(), mx, my, mz, extradata);
    }
}
