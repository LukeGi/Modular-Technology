package net.blep.modularTechnology.common.magic;

import com.google.common.collect.Lists;
import net.blep.modularTechnology.common.core.network.ModPacketHandler;
import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
import net.blep.modularTechnology.common.core.util.MethodHelper;
import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
import net.blep.modularTechnology.common.tech.items.ItemDesignator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
{
    private int x, y, z;
    private IInventory output;
    private List<Vec3> CutList = Lists.newArrayList();
    private int timer = 0;

    private Multiblock treefarm = new Multiblock(new Block[]
            {
                    MagicBlockHandler.treeFarm,
                    Blocks.lapis_block,
                    Blocks.iron_block
            }, new int[][][]
            {
                    {
                            {0, 0, 0}
                    },
                    {
                            {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                    },
                    {
                            {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                    }
            }
    );

    @Override
    public void updateEntity()
    {
        super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

        output = (IInventory) worldObj.getTileEntity(x, y, z);
        if (isFormed && output != null)
        {
            timer++;
            if (timer > 60)
            {
                timer = 0;
                ScanTrees(xCoord, yCoord, zCoord, CutList);
            }
            cutTree();
        }
    }

    private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
    {
        return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
    }

    @Override
    protected Multiblock getMultiblock()
    {
        return treefarm;
    }

    public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
    {
        int yo = 1;
        for (int xo = -radius; xo <= radius; xo++)
            for (int zo = -radius; zo <= radius; zo++)
                if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                    slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
        return slist;
    }

    public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
    {
        for (int xo = -radius; xo <= radius; xo++)
            for (int yo = -radius / 2; yo <= radius * 2; yo++)
                for (int zo = -radius; zo <= radius; zo++)
                    if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                        slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
        return slist;
    }

    public void cutTree()
    {
        if (output == null) return;
        if (CutList.isEmpty()) return;
        Vec3 pos = CutList.get(0);
        CutList.remove(0);
        List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
        ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
        worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

        for (ItemStack stack : drops) putBlockInOutputInventory(stack);
    }

    private void putBlockInOutputInventory(ItemStack stack)
    {
        for (int i = 0; i < output.getSizeInventory(); i++)
        {
            ItemStack slot = output.getStackInSlot(i);
            if (output.isItemValidForSlot(i, stack))
        }
    }

    public boolean canCut(Block block)
    {
        if (block == null) return false;package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }
        package net.blep.modularTechnology.common.magic;

        import com.google.common.collect.Lists;
        import net.blep.modularTechnology.common.core.network.ModPacketHandler;
        import net.blep.modularTechnology.common.core.network.packets.MessageSetBlock;
        import net.blep.modularTechnology.common.core.util.MethodHelper;
        import net.blep.modularTechnology.common.magic.multiblocks.Multiblock;
        import net.blep.modularTechnology.common.tech.items.ItemDesignator;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockLeavesBase;
        import net.minecraft.block.BlockRotatedPillar;
        import net.minecraft.block.material.Material;
        import net.minecraft.init.Blocks;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.ItemStack;
        import net.minecraft.tileentity.TileEntity;
        import net.minecraft.util.Vec3;

        import java.util.List;

/**
 * @author bluemonster122 <boo122333@gmail.com> TODO add functionality
 */
        public class TETreeFarm extends TileMagicMutliblock implements ItemDesignator.IDesignatorReciever
        {
            private int x, y, z;
            private IInventory output;
            private List<Vec3> CutList = Lists.newArrayList();
            private int timer = 0;

            private Multiblock treefarm = new Multiblock(new Block[]
                    {
                            MagicBlockHandler.treeFarm,
                            Blocks.lapis_block,
                            Blocks.iron_block
                    }, new int[][][]
                    {
                            {
                                    {0, 0, 0}
                            },
                            {
                                    {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}
                            },
                            {
                                    {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}
                            }
                    }
            );

            @Override
            public void updateEntity()
            {
                super.updateEntity();

//        if (worldObj.getWorldTime() % 1000 == 0)
//        {
//            LogHelper.info(isFormed ? "Formed" : "Not Formed");
//            LogHelper.info(output == null ? "No Inventory" : "Has Inventory");
//        }

                output = (IInventory) worldObj.getTileEntity(x, y, z);
                if (isFormed && output != null)
                {
                    timer++;
                    if (timer > 60)
                    {
                        timer = 0;
                        ScanTrees(xCoord, yCoord, zCoord, CutList);
                    }
                    cutTree();
                }
            }

            private List<Vec3> ScanTrees(int x, int y, int z, List<Vec3> blist)
            {
                return blist = scanForCuttableBlocksInRadius(x, y, z, 10, scanForCuttableBlocksInRadius(x, y, z, 10, scanForStumps(x, y, z, 3, blist)));
            }

            @Override
            protected Multiblock getMultiblock()
            {
                return treefarm;
            }

            public List<Vec3> scanForStumps(int x, int y, int z, int radius, List<Vec3> slist)
            {
                int yo = 1;
                for (int xo = -radius; xo <= radius; xo++)
                    for (int zo = -radius; zo <= radius; zo++)
                        if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                            slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public List<Vec3> scanForCuttableBlocksInRadius(int x, int y, int z, int radius, List<Vec3> slist)
            {
                for (int xo = -radius; xo <= radius; xo++)
                    for (int yo = -radius / 2; yo <= radius * 2; yo++)
                        for (int zo = -radius; zo <= radius; zo++)
                            if (canCut(worldObj.getBlock(x + xo, y + yo, z + zo)))
                                slist.add(Vec3.createVectorHelper(x + xo, y + yo, z + zo));
                return slist;
            }

            public void cutTree()
            {
                if (output == null) return;
                if (CutList.isEmpty()) return;
                Vec3 pos = CutList.get(0);
                CutList.remove(0);
                List<ItemStack> drops = worldObj.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord).getDrops(worldObj, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, worldObj.getBlockMetadata((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord), 1);
                ModPacketHandler.INSTANCE.sendToServer(new MessageSetBlock(Blocks.air, (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord));
                worldObj.markBlockForUpdate((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);

                for (ItemStack stack : drops) putBlockInOutputInventory(stack);
            }

            private void putBlockInOutputInventory(ItemStack stack)
            {
                for (int i = 0; i < output.getSizeInventory(); i++)
                {
                    ItemStack slot = output.getStackInSlot(i);
                    if (output.isItemValidForSlot(i, stack))
                }
            }

            public boolean canCut(Block block)
            {
                if (block == null) return false;
                if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
                    return true;
                else return false;
            }

            @Override
            public void recieveData(int x, int y, int z)
            {
                TileEntity te = worldObj.getTileEntity(x, y, z);
                this.x = x;
                this.y = y;
                this.z = z;

                if (te instanceof IInventory)
                {
                    this.output = (IInventory) te;
                }
            }
        }

        if (!((block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves) || (!(block instanceof BlockLeavesBase) && !(block instanceof BlockRotatedPillar))))
            return true;
        else return false;
    }

    @Override
    public void recieveData(int x, int y, int z)
    {
        TileEntity te = worldObj.getTileEntity(x, y, z);
        this.x = x;
        this.y = y;
        this.z = z;

        if (te instanceof IInventory)
        {
            this.output = (IInventory) te;
        }
    }
}
