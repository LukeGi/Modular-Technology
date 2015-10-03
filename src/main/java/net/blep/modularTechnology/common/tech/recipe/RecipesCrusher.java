package net.blep.modularTechnology.common.tech.recipe;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import javafx.util.Pair;
import net.blep.modularTechnology.common.core.ModContent;
import net.blep.modularTechnology.common.tech.items.TechItemHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import static net.blep.modularTechnology.common.core.ModContent.*;

import java.util.*;

/**
 * @author TheEpicTekkit
 */
public class RecipesCrusher
{
    private Map<ItemStack, Map<ItemStack, Float>> recipeList = Maps.newHashMap();

    private RecipesCrusher()
    {
        addRecipe("oreCopper", new ItemStack(getTechModItems().lumpCopper), new ItemStack(getTechModItems().dustCopper), 0.8F, new ItemStack(getTechModItems().dustTin), 0.08F);
    }

    public void addRecipe(String oreDict, ItemStack out, ItemStack byp1, float chance1, ItemStack byp2, float chance2)
    {
        ArrayList<ItemStack> input = OreDictionary.getOres(oreDict);

        for (ItemStack s : input)
        {
            try
            {
                addBlockRecipe(Block.getBlockFromItem(s.getItem()), out, byp1, chance1, byp2, chance2);
            } catch (Exception ignore) {}

            try
            {
                addItemRecipe(s.getItem(), out, byp1, chance1, byp2, chance2);
            } catch (Exception ignore) {}
        }
    }

    public void addBlockRecipe(Block block, ItemStack out, ItemStack byp1, float chance1, ItemStack byp2, float chance2)
    {
        this.addItemRecipe(Item.getItemFromBlock(block), out, byp1, chance1, byp2, chance2);
    }

    public void addItemRecipe(Item item, ItemStack out, ItemStack byp1, float chance1, ItemStack byp2, float chance2)
    {
        Map<ItemStack, Float> outputs = Maps.newHashMap();
        outputs.put(out, 1.0F);
        outputs.put(byp1, chance1);
        outputs.put(byp2, chance2);

        addRecipeGeneric(new ItemStack(item, 1, 32767), outputs);
    }

    public void addRecipeGeneric(ItemStack input, Map<ItemStack, Float> outputs)
    {
        this.recipeList.put(input, outputs);
    }

    public List<Pair<ItemStack, Float>> getResultsFor(ItemStack input)
    {
        List<Pair<ItemStack, Float>> result = Lists.newArrayList();

        Map<ItemStack, Float> map = recipeList.get(input);

        for (ItemStack key : map.keySet())
        {
            result.add(new Pair(key, map.get(key)));
        }

        return result;
    }

//    public List<ItemStack> getResult(ItemStack input)
//    {
//        if (recipeList.containsKey(input))
//            return recipeList.get(input);
//        return null;
//    }
//
//    private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_)
//    {
//        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
//    }
//
//    public Map getRecipeList()
//    {
//        return this.recipeList;
//    }
//
//    public float func_151398_b(ItemStack p_151398_1_)
//    {
//        float ret = p_151398_1_.getItem().getSmeltingExperience(p_151398_1_);
//        if (ret != -1) return ret;
//
//        Iterator iterator = this.experienceList.entrySet().iterator();
//        Map.Entry entry;
//
//        do
//        {
//            if (!iterator.hasNext())
//            {
//                return 0.0F;
//            }
//
//            entry = (Map.Entry)iterator.next();
//        }
//        while (!this.func_151397_a(p_151398_1_, (ItemStack) entry.getKey()));
//
//        return ((Float)entry.getValue()).floatValue();
//    }
}