package net.blep.modularTechnology.common.magic.items;

import com.blep.modularTechnology.core.common.items.ModItem;
import com.blep.modularTechnology.core.common.network.ModPacketHandler;
import com.blep.modularTechnology.core.common.network.packets.MessageSetBlock;
import com.blep.modularTechnology.core.common.util.Int3;
import net.blep.modularTechnology.common.magic.MTMagic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class ItemChainsaw extends ModItem
{

    public ItemChainsaw()
    {
        super();
        setMaxStackSize(1);
        setUnlocalizedName("chainsaw");
    }

    @Override
    public boolean onBlockDestroyed(ItemStack itemStack, World world, Block brokenBlock, int x, int y, int z, EntityLivingBase player)
    {
        if (brokenBlock.isWood(world, x, y, z))
        {
            ArrayList<Int3> visited = new ArrayList<Int3>();
            PriorityQueue<Int3> q = new PriorityQueue<Int3>();
            Int3 start = Int3.ni3(x, y, z);
            q.add(start);
            while (!q.isEmpty())
            {
                Int3 element = q.poll();
                for (int j = -1; j <= 1; j++)
                    for (int k = -1; k <= 1; k++)
                        for (int i = -1; i <= 1; i++)
                        {
                            Int3 target = Int3.ni3(element.getX() + i, element.getY() + j, element.getZ() + k);
                            Block current = world.getBlock(target.getX(), target.getY(), target.getZ());
                            if (current.isWood(world, element.getX(), element.getY(), element.getZ()) || current.isLeaves(world, element.getX(), element.getY(), element.getZ()))
                            {
                                if (!visited.contains(target))
                                {
                                    visited.add(target);
                                    q.add(target);
                                    ArrayList<ItemStack> drops = current.getDrops(world, target.getX(), target.getY(), target.getZ(), world.getBlockMetadata(target.getX(), target.getY(), target.getZ()), 1);
                                    ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, target.getX(), target.getY(), target.getZ()));

                                    for (ItemStack stack : drops)
                                        if (!world.isRemote)
                                            MTMagic.proxy.addItemEntityToSpawn(new EntityItem(world, target.getX(), target.getY(), target.getZ(), stack));
                                }
                            }
                        }
            }
        }

        return super.onBlockDestroyed(itemStack, world, brokenBlock, x, y, z, player);
    }

    public float func_150893_a(ItemStack stack, Block block)
    {
        return (block.getMaterial().equals(Material.wood) && block instanceof BlockRotatedPillar) ? 0.5F : 5.0F;
    }
}
