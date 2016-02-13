package net.blep.modtech.core;

import com.google.common.base.Stopwatch;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.blep.modtech.core.proxy.ModHandler;
import net.blep.modtech.core.reference.ModInfo;
import net.blep.modtech.core.util.LogHelper;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kelan on 24/01/2016.
 */
@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, dependencies = ModInfo.DEPENDENCIES)
public final class ModTech
{
    private Stopwatch timer;

    @Mod.Instance
    public static ModTech instance;

    @SidedProxy(clientSide = ModInfo.CLIENT_PROXY_CLASS, serverSide = ModInfo.SERVER_PROXY_CLASS)
    public static ModHandler PROXY;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        timer = Stopwatch.createStarted();
        LogHelper.info("Starting ModTech!");
        ModHandler.get().preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        ModHandler.get().init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        ModHandler.get().postInit(event);
        long e = timer.elapsed(TimeUnit.MILLISECONDS);

        long minutes = TimeUnit.MILLISECONDS.toMinutes(e);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(e) % 60;
        long mills = e % 1000;

        DecimalFormat minsecFmt = new DecimalFormat("00");
        DecimalFormat millsFmt = new DecimalFormat("0000");
        LogHelper.info("Successfully loaded ModTech; took " + minsecFmt.format(minutes) + ":" + minsecFmt.format(seconds) + ":" + millsFmt.format(mills) + " (" + + e + " milliseconds)");
    }
}
