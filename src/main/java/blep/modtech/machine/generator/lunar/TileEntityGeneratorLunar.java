package blep.modtech.machine.generator.lunar;

import blep.modtech.machine.generator.TileEntityGeneratorBase;
import blep.modtech.reference.GeneratorConfigurations;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public class TileEntityGeneratorLunar extends TileEntityGeneratorBase
{
    protected TileEntityGeneratorLunar()
    {
        super(GeneratorConfigurations.LUNAR_GENERATOR_MAX_ENERGY, GeneratorConfigurations.LUNAR_GENERATOR_MAX_TRANSFER);
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
        return new TextComponentString("Lunar Generator");
    }

    public int getGenerateAmount()
    {
        return worldObj.getWorldTime() > 13500 ? worldObj.canSeeSky(pos.up()) ? (int) (50 * (1.0D - worldObj.getRainStrength(1F) * 5.0F / 16.0D) * (1.0D - worldObj.getThunderStrength(1F) * 5.0F / 16.0D)) : 0 : 0;
    }
}
