package net.blep.modtech.core.config;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.blep.modtech.core.reference.ModInfo;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class ConfigHandler {
    public static Configuration configuration;
    public static boolean doOreGen;

    public static void init(File configFile) {
        // Create the configuration object from the given configuration file
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {
        doOreGen = configuration.getBoolean("doOreGen", Configuration.CATEGORY_GENERAL, true, "true = do spawn ores, false = dont spawn ores");
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(ModInfo.MOD_ID)) {
            loadConfiguration();
        }
    }

}
