package net.blep.modularTechnology.client.core.gui;

import cpw.mods.fml.client.config.GuiConfig;
import com.blep.modularTechnology.core.common.ModularTechnology;
import net.blep.modularTechnology.common.core.handler.ConfigHandler;
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
                ModularTechnology.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigHandler.configuration.toString()));
    }
}
