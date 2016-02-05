package net.blep.modtech.core.reference;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public class ModInfo {
    public static final String MOD_ID = "modtech";
    public static final String MOD_NAME = "Modular Technology";
    /*
    TODO: make this use CI
    VERSION = "1-2.3.4.5"
    1: Minecraft Version
    2: Major release
    3: Major API
    4: Minor release
    5: Patch
     */
    public static final String MOD_VERSION = "1.7.10-0.0.0.1";
    public static final String DEPENDENCIES = "";
    public static final String RESOURCE_PREFIX = MOD_ID + ":";
    public static final String CLIENT_PROXY_CLASS = "net.blep." + MOD_ID + ".core.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "net.blep." + MOD_ID + ".core.proxy.ServerProxy";
    public static final String GUI_FACTORY_CLASS = "net.blep." + MOD_ID + ".client.gui.inventory.GuiFactory";
}
