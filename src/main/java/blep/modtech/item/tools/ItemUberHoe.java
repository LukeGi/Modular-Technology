package blep.modtech.item.tools;

import blep.modtech.item.ItemMod;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ItemUberHoe extends ItemMod
{
    public ItemUberHoe()
    {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(1);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        for (int i = -4; i < 5; i++)
            for (int j = -4; j < 5; j++)
                if (!playerIn.canPlayerEdit(pos.offset(EnumFacing.NORTH, i).offset(EnumFacing.EAST, j), facing, stack))
                    return EnumActionResult.FAIL;

        int counter = 0;
        for (int i = -4; i < 5; i++)
            for (int j = -4; j < 5; j++)
            {
                BlockPos offsetPos = pos.offset(EnumFacing.NORTH, i).offset(EnumFacing.EAST, j);
                IBlockState block = worldIn.getBlockState(offsetPos.offset(EnumFacing.UP));
                if (!block.getBlock().getMaterial(block).isReplaceable())
                    return EnumActionResult.FAIL;
                block = worldIn.getBlockState(offsetPos);
                if (!(block.getBlock() instanceof BlockDirt || block.getBlock() instanceof BlockGrass))
                    return EnumActionResult.FAIL;
                UseHoeEvent event = new UseHoeEvent(playerIn, stack, worldIn, offsetPos);
                if (MinecraftForge.EVENT_BUS.post(event))
                    return EnumActionResult.FAIL;
                if (event.getResult() == Event.Result.ALLOW)
                    counter++;
            }

        if (counter < 0)
        {
            stack.damageItem(counter, playerIn);
            return EnumActionResult.SUCCESS;
        }
        for (int i = -4; i < 5; i++)
            for (int j = -4; j < 5; j++)
            {
                BlockPos offsetPos = pos.offset(EnumFacing.NORTH, i).offset(EnumFacing.EAST, j);
                IBlockState block = worldIn.getBlockState(offsetPos.offset(EnumFacing.UP));
                IBlockState newState = block.getBlock() == Blocks.dirt ? block.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.COARSE_DIRT ? Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT) : Blocks.farmland.getDefaultState() : Blocks.farmland.getDefaultState();
                if (pos.equals(offsetPos)) newState = Blocks.water.getDefaultState();
                worldIn.setBlockState(offsetPos, newState, 11);
                if (worldIn.getBlockState(offsetPos.offset(EnumFacing.UP)).getBlock() != Blocks.air)
                    worldIn.setBlockState(offsetPos.offset(EnumFacing.UP), Blocks.air.getDefaultState(), 11);
                stack.damageItem(1, playerIn);
                counter++;
            }
        worldIn.playSound(playerIn, pos, SoundEvents.item_hoe_till, SoundCategory.BLOCKS, 1.0F, 1.0F);
        return EnumActionResult.SUCCESS;
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(1, attacker);
        return true;
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0.0D, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", 2.0D, 0));
        }

        return multimap;
    }
}
