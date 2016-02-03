package modulartechnology.client.gui.inventory;

import cpw.mods.fml.client.config.GuiConfig;
import modulartechnology.config.ConfigHandler;
import modulartechnology.reference.ModInfo;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class GuiModConfiguration extends GuiConfig
{
    public GuiModConfiguration(GuiScreen guiScreen)
    {
        super(guiScreen,
                new ConfigElement(ConfigHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                ModInfo.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigHandler.configuration.toString()));
    }
}
