package modulartechnology.proxy;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import modulartechnology.item.ItemBase;
import modulartechnology.networking.ModularTechnologyPacketHandler;

public abstract class CommonProxy implements IProxy {
    @Override
    public void registerConfigs() {

    }

    @Override
    public void registerBlocks() {

    }

    @Override
    public void registerItems() {
        GameRegistry.registerItem(new ItemBase("item"), "item");
    }

    @Override
    public void registerPackets() {
        ModularTechnologyPacketHandler.registerMessages();
    }

    @Override
    public void registerRenders() {

    }

    @Override
    public void registerRecipes() {

    }
}
