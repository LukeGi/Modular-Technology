package blep.modtech.machine.generator.solarHybrid;

import blep.modtech.machine.generator.TileEntityGeneratorBase;
import blep.modtech.reference.GeneratorConfigurations;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class TileEntityGeneratorSolarLunarHybrid extends TileEntityGeneratorBase
{
    protected TileEntityGeneratorSolarLunarHybrid()
    {
        super(GeneratorConfigurations.SOLAR_LUNAR_HYBRID_GENERATOR_MAX_ENERGY, GeneratorConfigurations.SOLAR_LUNAR_HYBRID_GENERATOR_MAX_TRANSFER);
    }

    @Override
    protected boolean shouldAttemptPushing()
    {
        return energy.getEnergyStored() > 0;
    }

    @Override
    protected boolean shouldGenerate()
    {
        return true;
    }

    @Override
    public void generate()
    {
        energy.modifyEnergyStored(getGenerateAmount());
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentString("Solar Hybrid Generator");
    }

    public int getGenerateAmount()
    {
        return (int) (worldObj.canSeeSky(pos.up()) ? Math.max(100F * worldObj.getSunBrightnessFactor(1F), worldObj.getWorldTime() > 12500 || worldObj.getWorldTime() < 23500 ? 100F * (1.0D - worldObj.getRainStrength(1F) * 5.0F / 16.0D) * (1.0D - worldObj.getThunderStrength(1F) * 5.0F / 16.0D) : 0) : 0);
    }
}
