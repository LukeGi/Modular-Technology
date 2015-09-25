package net.blep.modularTechnology.common.tech.blocks.block.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.blep.modularTechnology.client.tech.ClientProxy;
import net.blep.modularTechnology.common.core.ModularTechnology;
import net.blep.modularTechnology.common.core.blocks.block.ModBlockContainer;
import net.blep.modularTechnology.common.core.util.MethodHelper;
import net.blep.modularTechnology.common.tech.blocks.tileentity.TileEntityMachineBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

/**
 * @author TheEpicTekkit
 */
public abstract class BlockMachineBase extends ModBlockContainer
{
    protected String[] texturesActive = new String[6];
    protected IIcon[] iconsActive = new IIcon[6];

    protected BlockMachineBase(String name, float resistance, float hardness, String[] textures_idle, String[] textures_active)
    {
        super(Material.iron, "machine" + MethodHelper.capitaliseCharAt(name, 0), resistance, hardness, "pickaxe", 1, textures_idle);

        for (int i = 0; i < 6; i++)
        {
            this.texturesActive[i] = i < textures_active.length && textures_active[i] != null && textures_active[i].length() > 0 ? texturesActive[i] : i < textures.length && textures[i] != null && textures[i].length() > 0 ? textures[i] : "";
        }
    }

    protected BlockMachineBase(String name, float resistance, float hardness)
    {
        this(name, resistance, hardness, new String[]{name + "_bottom", name + "_top", name + "_front", name + "_back", name + "_right", name + "_left"}, new String[]{null, null, name + "_front_active", null, null, null});
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        super.registerBlockIcons(register);

        for (int i = 0; i < 6; i++)
        {
            iconsActive[i] = register.registerIcon(ModularTechnology.RESOURCE_PREFIX + subFolder + texturesActive[i]);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        boolean active = meta > 5 && meta < 12;

        int[][] MACHINE_ICON_MATRIX = new int[][]
                {
                        //TODO: setup matrix for up/down orientation
                        {2, 0, 0, 0, 0, 0},
                        {0, 2, 0, 0, 0, 0},

                        {0, 1, 2, 3, 4, 5},
                        {0, 1, 3, 2, 5, 4},
                        {0, 1, 5, 4, 2, 3},
                        {0, 1, 4, 5, 3, 2},
                };

        int i = MACHINE_ICON_MATRIX[meta % 6][side];

        return active ? iconsActive[i] : icons[i];
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {

        }
        return true;
    }

    public int damageDropped(int meta)
    {
        return 0;
    }

    public int quantityDropped(int meta, int fortune, Random rand)
    {
        return 1;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
    {
        int[] ORIENTATION_TO_FD = {0, 1, 2, 5, 3, 4};
        int l = ORIENTATION_TO_FD[MethodHelper.getEntityRotationYawOrdinal(entity, 4) + 2];

        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile != null && tile instanceof TileEntityMachineBase)
        {
            TileEntityMachineBase machine = (TileEntityMachineBase) tile;
            machine.setOrientation(ForgeDirection.getOrientation(l));
        }

        super.onBlockPlacedBy(world, x, y, z, entity, stack);
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        MethodHelper.getDefaultRotation(world, x, y, z);
        super.onBlockAdded(world, x, y, z);
    }
}
