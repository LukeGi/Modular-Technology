package net.blep.modularTechnology.common.magic;

import com.google.common.collect.Lists;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.blep.modularTechnology.common.core.network.ModPacketHandler;
import net.blep.modularTechnology.common.core.network.packets.MessageMoveEntity;
import net.blep.modularTechnology.common.core.network.packets.MessageTeleportEntity;
import net.blep.modularTechnology.common.core.util.IconHelper;
import net.blep.modularTechnology.common.core.util.Int3;
import net.blep.modularTechnology.common.core.util.MethodHelper;
import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
import net.blep.modularTechnology.common.magic.multiblocks.MultiblockBlockCoordPair;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class ItemGem extends MagicItem
{
    IIcon[] icons;
    public static Multiblock deathChamber = new Multiblock(new MultiblockBlockCoordPair[]{new MultiblockBlockCoordPair(Blocks.obsidian, Int3.ni3a(-1, 0, 1, 0, 0, 1, 1, 0, 1, -1, 0, 0, 0, 0, 0, 1, 0, 0, -1, 0, -1, 0, 0, -1, 1, 0, -1, -1, 1, 1, 0, 1, 1, 1, 1, 1, -1, 1, 0, 1, 1, 0, -1, 1, -1, 0, 1, -1, 1, 1, -1, -1, 2, 1, 1, 2, 1, -1, 2, -1, 1, 2, -1, -1, 3, 1, 0, 3, 1, 1, 3, 1, -1, 3, 0, 0, 3, 0, 1, 3, 0, -1, 3, -1, 0, 3, -1, 1, 3, -1)), new MultiblockBlockCoordPair(Blocks.flowing_water, Int3.ni3a(0, 1, 0, 0, 2, 0)), new MultiblockBlockCoordPair(Blocks.glass, Int3.ni3a(0, 2, 1, 0, 2, -1, 1, 2, 0, -1, 2, 0))});
    public static Multiblock sugarfarm = new Multiblock(new MultiblockBlockCoordPair[]{new MultiblockBlockCoordPair(Blocks.flowing_water, Int3.ni3a(0, 0, 0, 1, 0, 2, 2, 0, -1, -1, 0, -2, -2, 0, 1)), new MultiblockBlockCoordPair(Blocks.sand, Int3.ni3a(1, 0, 0, 0, 0, 1, -1, 0, 0, 0, 0, -1, 2, 0, 2, 1, 0, 3, 0, 0, 2, 1, 0, 1, 3, 0, -1, 2, 0, 0, 1, 0, -1, 2, 0, -2, 0, 0, -2, -1, 0, -1, -2, 0, -2, -1, 0, -3, -1, 0, 1, -2, 0, 2, -3, 0, 1, -2, 0, 0)), new MultiblockBlockCoordPair(Blocks.reeds, Int3.ni3a(1, 1, 0, 0, 1, 1, -1, 1, 0, 0, 1, -1, 2, 1, 2, 1, 1, 3, 0, 1, 2, 1, 1, 1, 3, 1, -1, 2, 1, 0, 1, 1, -1, 2, 1, -2, 0, 1, -2, -1, 1, -1, -2, 1, -2, -1, 1, -3, -1, 1, 1, -2, 1, 2, -3, 1, 1, -2, 1, 0))});

    public ItemGem()
    {
        super();
        setHasSubtypes(true);
        setUnlocalizedName("Gems");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase mob, EntityLivingBase player)
    {
        if (stack.getItemDamage() == 0)
        {
            mob.setFire(20);
            mob.setHealth(2);
            stack.stackSize--;
            return true;
        }
        return false;

    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int s, float hx, float hy, float hz)
    {
        if (world.isRemote) return false;
        switch (getDamage(stack))
        {
            case 0: // FIRE
                int deathtoall = world.rand.nextInt(100);
                if (deathtoall <= 60)
                {
                    ForgeDirection dir = ForgeDirection.getOrientation(s);
                    world.setBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, Blocks.fire);
                }
                if (60 < deathtoall && deathtoall <= 75)
                    world.setBlock(x, y, z, Blocks.flowing_lava);
                if (75 < deathtoall && deathtoall <= 80)
                    world.setBlock((int) player.posX, (int) player.posY + 5, (int) player.posZ, Blocks.flowing_lava);
                if (80 < deathtoall && deathtoall <= 85)
                {
                    for (int i = -7; i < 7; i++)
                    {
                        for (int k = -7; k < 7; k++)
                        {
                            if ((i < -3 || i > 3) || (k < -3 || k > 3))
                            {
                                world.setBlock((int) player.posX + i, world.getHeightValue((int) player.posX + i, (int) player.posZ + k) - 1, (int) player.posZ + k, Blocks.netherrack);
                                world.setBlock((int) player.posX + i, world.getHeightValue((int) player.posX + i, (int) player.posZ + k), (int) player.posZ + k, Blocks.fire);
                            }
                        }
                    }
                }
                if (85 < deathtoall && deathtoall <= 90)
                {
                    for (int i = -7; i <= 7; i++)
                    {
                        for (int k = -7; k <= 7; k++)
                        {
                            if (!(i == 0 && k == 0))
                                for (int j = -5; j < 0; j++)
                                    world.setBlock((int) player.posX + i, world.getHeightValue((int) player.posX + i, (int) player.posZ + k) + j, (int) player.posZ + k, Blocks.lava);
                        }
                    }
                }
                stack.stackSize--;
                return true;
            case 1: // EARTH
                Block block = world.getBlock(x, y, z);
                if (block.getMaterial().equals(Material.rock) || block.getMaterial().equals(Material.ground) || block.getMaterial().equals(Material.grass))
                {
                    int diewilliamdie = world.rand.nextInt(100);
                    if (diewilliamdie <= 35)
                        world.setBlock(x, y, z, Blocks.dirt);
                    if (35 < diewilliamdie && diewilliamdie <= 65)
                        world.setBlock(x, y, z, Blocks.cobblestone);
                    if (65 < diewilliamdie && diewilliamdie <= 70)
                        player.addChatComponentMessage(new ChatComponentText(ChatFormatting.RED + "YOU BE GETTING FLUNG BITCH!")); //Launch player to the the sky TODO
                    if (70 < diewilliamdie && diewilliamdie <= 80)
                        world.setBlock(x, y, z, Blocks.stone);
                    if (80 < diewilliamdie && diewilliamdie <= 85)
                        world.setBlock(x, y, z, Blocks.grass);
                    if (85 < diewilliamdie && diewilliamdie <= 90)
                        world.setBlock(x, y, z, Blocks.dirt, 2, 2);
                    if (90 < diewilliamdie && diewilliamdie <= 95)
                        world.setBlock(x, y, z, Blocks.obsidian);
                    if (95 < diewilliamdie && diewilliamdie <= 100)
                    {
                        String[] allOreEntries = OreDictionary.getOreNames();
                        List<String> refinedOreList = Lists.newArrayList();
                        for (String ore : allOreEntries)
                        {
                            if (!ore.startsWith("ore"))
                                continue;
                            refinedOreList.add(ore);
                        }
                        Block ore = Block.getBlockFromItem(OreDictionary.getOres(refinedOreList.get(world.rand.nextInt(refinedOreList.size()))).get(0).getItem());

                        world.setBlock(x, y, z, ore);
                    }
                    stack.stackSize--;
                    return true;
                }
            case 2: // WIND
                // INSERT CODE THAT DEFLECTS ARROWS HERE TODO
            case 3: // WATER
                int kelanisatard = world.rand.nextInt(100);
                if (0 <= kelanisatard && kelanisatard < 80)
                    world.setBlock(x, y, z, Blocks.flowing_water);
                if (80 <= kelanisatard && kelanisatard < 95)
                    sugarfarm.create(world, x, y, z);
                if (95 <= kelanisatard && kelanisatard < 98)
                    //Make the player wet themselves TODO
                    player.addChatComponentMessage(new ChatComponentText("You wet yourself"));
                if (98 <= kelanisatard && kelanisatard < 100)
                    //MOVE PLAYER INTO DEATHCHAMBER TODO
                    deathChamber.create(world, x, y, z);
                if (kelanisatard % 3 == 0)
                    MethodHelper.spawnEntityAtLocation(world, new EntitySquid(world), x, y, z);
                return true;

            default:
                return false;
        }
    }

    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[7];
        for (int i = 0; i < icons.length; i++)
            icons[i] = IconHelper.forItem(iconRegister, this, i);
    }

    @Override
    public IIcon getIconFromDamage(int meta)
    {
        return icons[Math.min(icons.length - 1, meta)];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        for (int i = 0; i < 7; i++)
            list.add(new ItemStack(item, 1, i));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return getUnLocalizedNameLazy(stack) + stack.getItemDamage();
    }

    String getUnLocalizedNameLazy(ItemStack stack)
    {
        return super.getUnlocalizedName(stack);
    }

    public enum GemType
    {
        FIRE("Fire", 10),
        EARTH("Earth", 200),
        WIND("Wind", 20),
        WATER("Water", 3),
        LIGHT("Light", 20),
        DARK("Dark", 20),
        YINGYANG("YingYang", 10);

        GemType(String name, int durabilityOfGem)
        {
            this.name = name;
            this.durabilityOfGem = durabilityOfGem;
        }

        GemType(String name)
        {
            this(name, -1);
        }

        private String name;
        private int durabilityOfGem;

        public int getDurabilityOfGem()
        {
            return durabilityOfGem;
        }

        public String getName()
        {
            return name;
        }
    }
}