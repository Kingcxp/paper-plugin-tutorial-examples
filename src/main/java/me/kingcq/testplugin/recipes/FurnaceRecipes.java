package me.kingcq.testplugin.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class FurnaceRecipes {
    public static void registerRecipes() {
        FurnaceRecipe rottenFleshRecipe = new FurnaceRecipe(
                new NamespacedKey("test_plugin", "rotten_flesh_to_leather"),
                new ItemStack(Material.LEATHER, 2),
                Material.ROTTEN_FLESH,
                1, 20
        );
        Bukkit.addRecipe(rottenFleshRecipe);
    }
}
