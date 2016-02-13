package net.blep.modtech.blocks.block;

import net.blep.modtech.blocks.tileentity.ModTileEntity;
import net.blep.modtech.client.particle.ParticleManager;
import net.blep.modtech.core.networking.packets.MessageSpawnParticle;
import net.blep.modtech.core.util.Vector3d;
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
                ParticleManager.spawnParticlez(MessageSpawnParticle.ParticleTypes.SHINYLIGHTNING, worldObj, new Vector3d(xCoord  + .5, yCoord + .5, zCoord + .5), 1);
        }
    }
}
