package blep.modtech.machine.generator.solar;

import blep.modtech.machine.generator.TileEntityGeneratorBase;
import blep.modtech.reference.GeneratorConfigurations;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class TileEntityGeneratorSolar extends TileEntityGeneratorBase
{

    private TileEntityGeneratorSolar()
    {
        super(GeneratorConfigurations.SOLAR_GENERATOR_MAX_ENERGY, GeneratorConfigurations.SOLAR_GENERATOR_MAX_TRANSFER);
    }

    public static TileEntityGeneratorSolar create()
    {
        return new TileEntityGeneratorSolar();
    }

    @Override
    public void update()
    {
        super.update();
    }

    @Override
    protected boolean shouldAttemptPushing()
    {
        return 0 < energy.getEnergyStored();
    }

    @Override
    public boolean shouldGenerate()
    {
        return worldObj.getWorldTime() < 12000L;
    }

    @Override
    public void generate()
    {
        energy.modifyEnergyStored(1000);
    }

    public ITextComponent getDisplayName()
    {
        return new TextComponentString("Solar Generator");
    }
}
