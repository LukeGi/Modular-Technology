package blep.modtech;

import net.minecraft.block.Block;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.BlockPos;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.IPlayerFileData;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

import java.io.File;

/**
 * Created by Kelan on 06/02/2016.
 */
public class DummyWorld extends World
{
    private static String NAME = "dummyworld_modtech";

    private static WorldProvider worldProvider = new WorldProvider()
    {
        @Override
        public String getDimensionName()
        {
            return NAME;
        }

        @Override
        public String getInternalNameSuffix()
        {
            return null;
        }
    };

    private static ISaveHandler saveHandler = new ISaveHandler()
    {
        @Override
        public WorldInfo loadWorldInfo()
        {
            return null;
        }

        @Override
        public void checkSessionLock() throws MinecraftException
        {

        }

        @Override
        public IChunkLoader getChunkLoader(WorldProvider p_75763_1_)
        {
            return null;
        }

        @Override
        public void saveWorldInfoWithPlayer(WorldInfo p_75755_1_, NBTTagCompound p_75755_2_)
        {

        }

        @Override
        public void saveWorldInfo(WorldInfo p_75761_1_)
        {

        }

        @Override
        public IPlayerFileData getPlayerNBTManager()
        {
            return null;
        }

        @Override
        public void flush()
        {

        }

        @Override
        public File getWorldDirectory()
        {
            return null;
        }

        @Override
        public File getMapFileFromName(String p_75758_1_)
        {
            return null;
        }

        @Override
        public String getWorldDirectoryName()
        {
            return null;
        }
    };

    public DummyWorld()
    {
        super(saveHandler, new WorldInfo(new NBTTagCompound()), worldProvider, new Profiler(), false);
    }

    @Override
    protected IChunkProvider createChunkProvider()
    {
        return null;
    }

    @Override
    public Entity getEntityByID(int p_73045_1_)
    {
        return null;
    }

    @Override
    public boolean setBlockState(BlockPos pos, IBlockState newState, int flags)
    {
        return true;
    }

    @Override
    protected int getRenderDistanceChunks()
    {
        return 0;
    }
}
