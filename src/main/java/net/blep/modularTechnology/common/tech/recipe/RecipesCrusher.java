package net.blep.modularTechnology.common.tech.recipe;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

/**
 * @author TheEpicTekkit
 */
public class RecipesCrusher
{
    private Map<ItemStack, List<ItemStack>> recipeList = Maps.newHashMap();
    private Map<ItemStack, Float> experienceList = Maps.newHashMap();

    private RecipesCrusher()
    {
        addRecipe("oreCopper", null, null, null, 1.0F);
    }

    public void addRecipe(String oreDict, ItemStack stack1, ItemStack stack2, ItemStack stack3, float exp)
    {
        ArrayList<ItemStack> input = OreDictionary.getOres(oreDict);

        for (ItemStack s : input)
        {
            try
            {
                addBlockRecipe(Block.getBlockFromItem(s.getItem()), stack1, stack2, stack3, exp);
            } catch (Exception ignore) {}

            try
            {
                addItemRecipe(s.getItem(), stack1, stack2, stack3, exp);
            } catch (Exception ignore) {}
        }
    }

    public void addBlockRecipe(Block block, ItemStack stack1, ItemStack stack2, ItemStack stack3, float exp)
    {
        this.addItemRecipe(Item.getItemFromBlock(block), stack1, stack2, stack3, exp);
    }

    public void addItemRecipe(Item item, ItemStack stack1, ItemStack stack2, ItemStack stack3, float exp)
    {
        ArrayList<ItemStack> outputs = Lists.newArrayList();
        outputs.add(stack1);
        outputs.add(stack2);
        outputs.add(stack3);

        addRecipeGeneric(new ItemStack(item, 1, 32767), outputs, exp);
    }

    public void addRecipeGeneric(ItemStack input, List<ItemStack> outputs, float exp)
    {
        this.recipeList.put(input, outputs);
        this.experienceList.put(input, Float.valueOf(exp));
    }

    public List<ItemStack> getResult(ItemStack input)
    {
        if (recipeList.containsKey(input))
            return recipeList.get(input);
        return null;
    }

    private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_)
    {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

    public Map getRecipeList()
    {
        return this.recipeList;
    }

    public float func_151398_b(ItemStack p_151398_1_)
    {
        float ret = p_151398_1_.getItem().getSmeltingExperience(p_151398_1_);
        if (ret != -1) return ret;

        Iterator iterator = this.experienceList.entrySet().iterator();
        Map.Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Map.Entry)iterator.next();
        }
        while (!this.func_151397_a(p_151398_1_, (ItemStack) entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }
}