package blep.modtech.block;

import blep.modtech.creativetab.ModTechCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class BlockMod extends Block
{
    public BlockMod(Material material, String name)
    {
        //TODO: Checkout the this.blockParticleGravity stuffs
        super(material);
        this.setUnlocalizedName(name);
        this.setCreativeTab(ModTechCreativeTabs.getInstance());
    }
}
