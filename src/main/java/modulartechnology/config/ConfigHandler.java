package modulartechnology.config;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import modulartechnology.reference.ModInfo;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class ConfigHandler
{
    public static Configuration configuration;
    public static String[] WRENCH_BLACKLIST;

    public static void init(File configFile)
    {
        // Create the configuration object from the given configuration file
        if (configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        WRENCH_BLACKLIST = configuration.getStringList("wrenchBlacklist", Configuration.CATEGORY_GENERAL, new String[] {"tile.chest.name"}, "Blacklisted blocks. The wrench will not pick these up. The string here is the unlocalized name, e.g. tile.furnace.name");

        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(ModInfo.MOD_ID))
        {
            loadConfiguration();
        }
    }
}
