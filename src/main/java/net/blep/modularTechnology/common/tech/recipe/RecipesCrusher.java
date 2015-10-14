package net.blep.modularTechnology.common.tech.recipe;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import javafx.util.Pair;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static net.blep.modularTechnology.common.core.ModContent.getTechModItems;

/**
 * @author TheEpicTekkit
 */
public class RecipesCrusher
{
    private static Map<ItemStack, List<Pair<ItemStack, Float>>> recipeList = Maps.newHashMap();

    public static void init()
    {
        addRecipe(new String[]{"oreBauxite", "oreAluminium", "oreAluminum"}, new ItemStack(getTechModItems().lumpAluminium, 2), new ItemStack(getTechModItems().dustAluminium), 0.21F, new ItemStack(getTechModItems().dustStone), 0.04F);
        addRecipe(new String[]{"oreChromium", "oreChrome"}, new ItemStack(getTechModItems().lumpChromium, 2), new ItemStack(getTechModItems().dustChromium), 0.16F, new ItemStack(getTechModItems().dustStone), 0.04F);
        addRecipe(new String[]{"oreCopper"}, new ItemStack(getTechModItems().lumpCopper, 2), new ItemStack(getTechModItems().dustCopper), 0.17F, new ItemStack(getTechModItems().dustStone), 0.04F);
        addRecipe(new String[]{"oreLead"}, new ItemStack(getTechModItems().lumpLead, 2), new ItemStack(getTechModItems().dustLead), 0.09F, new ItemStack(getTechModItems().dustStone), 0.04F);
        addRecipe(new String[]{"oreMagnesium"}, new ItemStack(getTechModItems().dustMagnesium, 2), new ItemStack(getTechModItems().dustMagnesium), 0.095F, new ItemStack(getTechModItems().dustStone), 0.04F);
        addRecipe(new String[]{"oreManganese"}, new ItemStack(getTechModItems().lumpManganese, 2), new ItemStack(getTechModItems().dustManganese), 0.035F, new ItemStack(getTechModItems().dustStone), 0.04F);
        addRecipe(new String[]{"orePlatinum", "oreShiny"}, new ItemStack(getTechModItems().lumpPlatinum, 2), new ItemStack(getTechModItems().dustPlatinum), 0.025F, new ItemStack(getTechModItems().dustStone), 0.07F);
        addRecipe(new String[]{"oreSilver"}, new ItemStack(getTechModItems().lumpSilver, 2), new ItemStack(getTechModItems().dustSilver), 0.06F, new ItemStack(getTechModItems().dustStone), 0.05F);
        addRecipe(new String[]{"oreTin"}, new ItemStack(getTechModItems().lumpTin, 2), new ItemStack(getTechModItems().dustTin), 0.19F, new ItemStack(getTechModItems().dustStone), 0.04F);
        addRecipe(new String[]{"oreTitanium", "oreRutile"}, new ItemStack(getTechModItems().lumpLead, 2), new ItemStack(getTechModItems().dustLead), 0.115F, new ItemStack(getTechModItems().dustStone), 0.04F);
        addRecipe(new String[]{"oreYttrium"}, new ItemStack(getTechModItems().lumpYttrium, 2), new ItemStack(getTechModItems().dustYttrium), 0.08F, new ItemStack(getTechModItems().dustStone), 0.04F);
        addRecipe(new String[]{"oreZinc"}, new ItemStack(getTechModItems().lumpZinc, 2), new ItemStack(getTechModItems().dustZinc), 0.155F, new ItemStack(getTechModItems().dustStone), 0.04F);
        addRecipe(new String[]{"oreZirconium"}, new ItemStack(getTechModItems().lumpZirconium, 2), new ItemStack(getTechModItems().dustZirconium), 0.15F, new ItemStack(getTechModItems().dustStone), 0.04F);

        addRecipe(new String[]{"ingotBauxite", "ingotAluminium", "ingotAluminum"}, new ItemStack(getTechModItems().dustAluminium, 2), null, 0.0F, null, 0.0F);
        addRecipe(new String[]{"ingotChromium", "ingotChrome"}, new ItemStack(getTechModItems().dustChromium, 2), null, 0.0F, null, 0.0F);
        addRecipe(new String[]{"ingotCopper"}, new ItemStack(getTechModItems().dustCopper, 2), null, 0.0F, null, 0.0F);
        addRecipe(new String[]{"ingotLead"}, new ItemStack(getTechModItems().dustLead, 2), null, 0.0F, null, 0.0F);
        addRecipe(new String[]{"ingotMagnesium"}, new ItemStack(getTechModItems().dustMagnesium, 2), null, 0.0F, null, 0.0F);
        addRecipe(new String[]{"ingotManganese"}, new ItemStack(getTechModItems().dustManganese, 2), null, 0.0F, null, 0.0F);
        addRecipe(new String[]{"ingotPlatinum", "ingotPlatinum"}, new ItemStack(getTechModItems().dustPlatinum, 2), null, 0.0F, null, 0.0F);
        addRecipe(new String[]{"ingotSilver"}, new ItemStack(getTechModItems().dustSilver, 2), null, 0.0F, null, 0.0F);
        addRecipe(new String[]{"ingotTin"}, new ItemStack(getTechModItems().dustTin, 2), null, 0.0F, null, 0.0F);
        addRecipe(new String[]{"ingotTitanium"}, new ItemStack(getTechModItems().dustLead, 2), null, 0.0F, null, 0.0F);
        addRecipe(new String[]{"ingotYttrium"}, new ItemStack(getTechModItems().dustYttrium, 2), null, 0.0F, null, 0.0F);
        addRecipe(new String[]{"ingotZinc"}, new ItemStack(getTechModItems().dustZinc, 2), null, 0.0F, null, 0.0F);
        addRecipe(new String[]{"ingotZirconium"}, new ItemStack(getTechModItems().dustZirconium, 2), null, 0.0F, null, 0.0F);
    }

    public static void addRecipe(String[] oreDictNames, ItemStack out, ItemStack byp1, float chance1, ItemStack byp2, float chance2)
    {
        for (String oreDict : oreDictNames)
        {
            List<ItemStack> inputs = OreDictionary.getOres(oreDict);

            for (ItemStack i : inputs)
            {
                addRecipe(i.copy(), out != null ? out.copy() : null, byp1 != null ? byp1.copy() : null, chance1, byp2 != null ? byp2.copy() : null, chance2);
            }
        }
    }

    public static void addRecipe(ItemStack input, ItemStack out, ItemStack byp1, float chance1, ItemStack byp2, float chance2)
    {
        List<Pair<ItemStack, Float>> outputs = Lists.newArrayList();

        outputs.add(new Pair<>(out, 1.0F));
        if (byp1 != null)
        {
            outputs.add(new Pair<>(byp1, chance1));
        }
        if (byp2 != null)
        {
            outputs.add(new Pair<>(byp2, chance2));
        }

        recipeList.put(input, Collections.unmodifiableList(outputs));
    }

    public static List<Pair<ItemStack, Float>> getResultsFor(ItemStack input)
    {
        if (input != null && hasRecipeFor(input))
        {
            return new ArrayList<>(recipeList.get(Item.getIdFromItem(input.getItem())));
        }
        return null;

    }

    public static boolean hasRecipeFor(ItemStack input)
    {
        if (input == null) return false;
        return recipeList.containsKey(input);
    }
}