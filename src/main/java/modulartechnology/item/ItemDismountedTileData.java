package modulartechnology.item;

/**
 * @author TheEpicTekkit
 */
public class ItemDismountedTileData extends ModItem
{
//    public ItemDismountedTileData()
//    {
//        super("dismountedTile", null);
//        setMaxStackSize(1);
//    }
//
//    public String getUnlocalizedName(ItemStack stack)
//    {
//        String name = null;
//
//        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("block"))
//        {
//            try
//            {
//                name = Block.getBlockById(stack.getTagCompound().getInteger("block")).getUnlocalizedName();
//            } catch (Exception e)
//            {
//            }
//        }
//
//        return "item.dismountedTile." + name;
//    }
//
//    @Override
//    public String getItemStackDisplayName(ItemStack stack)
//    {
//        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("block"))
//        {
//            return "Dismounted " + Block.getBlockById(stack.getTagCompound().getInteger("block")).getLocalizedName();
//        } else
//        {
//            return super.getItemStackDisplayName(stack);
//        }
//    }
//
//    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
//    {
//        if (stack.hasTagCompound())
//        {
//            try
//            {
//                Block block = Block.getBlockById((stack.getTagCompound().getInteger("block")));
//                int meta = stack.getTagCompound().getInteger("metadata");
//
//                if (block != null && block instanceof ITileEntityProvider)
//                {
//                    int x1 = x;
//                    int y1 = y;
//                    int z1 = z;
//
//                    if (!world.getBlock(x1, y1, z1).isReplaceable(world, x1, y1, z1))
//                    {
//                        ForgeDirection direction = ForgeDirection.values()[side];
//                        x1 += direction.offsetX;
//                        y1 += direction.offsetY;
//                        z1 += direction.offsetZ;
//                    } else
//                    {
//                        world.setBlockToAir(x1, y1, z1);
//                    }
//
//                    world.setBlock(x1, y1, z1, block);
//                    world.setBlockMetadataWithNotify(x1, y1, z1, meta, 2);
//                    block.onBlockAdded(world, x1, y1, z1);
//                    TileEntity tile = ((ITileEntityProvider) block).createNewTileEntity(world, meta);
//                    tile.validate();
//                    world.setTileEntity(x1, y1, z1, tile);
//                    tile.markDirty();
//                    tile.readFromNBT(stack.getTagCompound().getCompoundTag("tileNBT"));
//                    tile.xCoord = x1;
//                    tile.yCoord = y1;
//                    tile.zCoord = z1;
//                    world.markBlockForUpdate(x1, y1, z1);
//                    stack.stackSize--;
//
//                    return true;
//                }
//            } catch (Exception e)
//            {
//                ModularTechnology.LOGGER.error("An error has occured while placing a saved tile data:");
//                e.printStackTrace();
//            }
//        }
//
//        return false;
//    }
//
//    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
//    {
//        try
//        {
//            if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("tileNBT"))
//            {
//                list.add(EnumChatFormatting.RED + "Error in stack NBT");
//            } else
//            {
//                List<String> data = new ArrayList();
//
//                if (stack.getTagCompound().hasKey("data"))
//                {
//                    NBTTagList dataSaved = stack.getTagCompound().getTagList("data", 10);
//                    for (int i = 0; i < dataSaved.tagCount(); i++)
//                    {
//                        data.add(dataSaved.getCompoundTagAt(i).getString("dataSaved:" + i));
//                    }
//                } else
//                {
//                    NBTTagList nbttl = new NBTTagList();
//
//                    for (int i = 0; i < data.size(); i++)
//                    {
//                        NBTTagCompound newTag = new NBTTagCompound();
//                        newTag.setString("dataSaved:" + i, data.get(i));
//                        nbttl.appendTag(newTag);
//                    }
//
//                    stack.getTagCompound().setTag("data", nbttl);
//                }
//                if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
//                {
//                    NBTTagCompound tag = stack.getTagCompound().getCompoundTag("tileNBT");
//
//                    List formatted = MethodHelper.writeNBTToList(tag);
//
//                    for (Object o : formatted)
//                    {
//                        if (o instanceof List)
//                        {
//                            Map<String, Integer> added = Maps.newHashMap();
//
//                            for (Object o1 : (List) o)
//                            {
//                                String[] components = o1.toString().split(",");
//                                String itemStats = "";
//
//                                for (String component : components)
//                                {
//                                    if (component.startsWith("id:"))
//                                    {
//                                        Item item = getItemById(Integer.parseInt(component.substring(component.indexOf(":") + 1, component.length() - 1)));
//                                        itemStats += item.getItemStackDisplayName(new ItemStack(item)) + " ";
//                                    } else if (component.startsWith("Count:"))
//                                    {
//                                        itemStats += ("x" + Integer.parseInt(component.substring(component.indexOf(":") + 1, component.length() - 1)));
//                                    }
//                                }
//
//                                String key = itemStats.split("x")[0];
//                                int value = Integer.parseInt(itemStats.split("x")[1]);
//                                if (added.containsKey(key))
//                                {
//                                    added.put(key, value + added.get(key));
//                                } else
//                                {
//                                    added.put(key, value);
//                                }
//                            }
//
//                            for (String s : added.keySet()) list.add("    " + s + "x" + added.get(s));
//
//                        } else
//                        {
//                            list.add(o);
//                        }
//                    }
//                } else
//                {
//                    list.add(EnumChatFormatting.RESET + "Hold " + EnumChatFormatting.GOLD + EnumChatFormatting.UNDERLINE + "SHIFT" + EnumChatFormatting.RESET + " to show more information.");
//                }
//            }
//        } catch (Exception e)
//        {
//            MethodHelper.handleTooltipException(e, list);
//        }
//    }
}
