package blep.modtech.proxy;

import blep.modtech.block.ModtechBlocks;
import blep.modtech.item.ModtechItems;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerBlockRenders()
    {
        ModtechBlocks.registerAllBlockRenders();
    }

    @Override
    public void registerItemRenders()
    {
        ModtechItems.registerAllItemRenders();
    }
}
