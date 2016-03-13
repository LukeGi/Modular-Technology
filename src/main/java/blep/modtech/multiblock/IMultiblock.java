package blep.modtech.multiblock;

/**
 * Created by Blue <boo122333@gmail.com>.
 */
public interface IMultiblock
{
    /**
     * <p>
     *     This method should return the Multiblock's pattern.
     * </p>
     * @return This IMultiblock's instance of a <code>MultiblockParttern</code>
     */
    MultiblockPattern getPattern();

    /**
     * <p>
     *     This method should be called to check of the IMultiblock has been formed correctly.
     * </p>
     * @return True if is Formed. False if not Formed
     */
    boolean checkFormed();

    /**
     * <p>
     *     This is a method that should be called to check there is space for the IMultiblock to be formed.
     * </p>
     * @return True if there is enough space and false if there isn't enough space.
     */
    boolean isSpace();

    /**
     * <p>
     *     This should create the IMultiblock using the IMultiblock's MultiblockPatternm,
     *     which can be obtained using <code>this#getLibrary();</code>,
     *     but only if there <code>this#isSpace();</code>
     * </p>
     */
    void create();
}
