package net.blep.modtech.api;

//import net.minecraft.world.World;

import net.minecraft.world.World;

/**
 * Created by Blue <boo122333@gmail.com>
 */
public interface IWrenchable {

    /**
     * This should make the blocks front face, face the side of the side param.
     * @param side side that is being wrenched
     * @param world world block being wrenched is in
     * @param x x position of wrenched block
     * @param y y position of wrenched block
     * @param z z position of wrenched block
     */
    void makeFront(int side, World world, int x, int y, int z);


    /**
     * If a block doesnt want to be wrenched or only wants to hbe wrenched under certain conditions, the returned boolean can control that
     * TODO: this
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
     //boolean onWrenched(World world, int x, int y, int z);
}
