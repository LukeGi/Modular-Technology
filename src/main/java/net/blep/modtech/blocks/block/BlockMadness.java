package net.blep.modtech.blocks.block;

import cpw.mods.fml.common.network.NetworkRegistry;
import net.blep.modtech.blocks.tileentity.ModTileEntity;
import net.blep.modtech.core.networking.NetworkManagerModtech;
import net.blep.modtech.core.networking.packets.MessageSpawnParticle;
import net.blep.modtech.core.util.LogHelper;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class BlockMadness extends BlockContainer {
    public BlockMadness() {
        super(Material.rock);
        this.setBlockUnbreakable();
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setBlockName("madness");
        this.setTickRandomly(true);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityParticleGenerator();
    }

    public static class TileEntityParticleGenerator extends ModTileEntity {
        @Override
        public void updateEntity() {
            super.updateEntity();

            if (!this.worldObj.isRemote && this.worldObj.getTotalWorldTime() % 100 == 0)
                NetworkManagerModtech.networkManager.sendToAllAround(new MessageSpawnParticle(1, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 1), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 64));
        }
    }
}
