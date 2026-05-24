package me.kingcq.testplugin.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Consumable;
import io.papermc.paper.datacomponent.item.FoodProperties;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.inventory.meta.components.FoodComponent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetCustomSword implements BasicCommand {
    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, String @NotNull [] args) {
        CommandSender sender = commandSourceStack.getSender();
        if (!(sender instanceof Player player)) {
            return;
        }
        ItemStack item = new ItemStack(Material.COPPER_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setItemModel(new NamespacedKey("test_plugin", "sword"));

        CustomModelDataComponent cmd = meta.getCustomModelDataComponent();
        cmd.setStrings(List.of("diamond", "golden"));
        meta.setCustomModelDataComponent(cmd);

        item.setItemMeta(meta);
        item.setData(
                DataComponentTypes.CONSUMABLE,
                Consumable.consumable().consumeSeconds(1.6f).build()
        );
        item.setData(
                DataComponentTypes.FOOD,
                FoodProperties.food().canAlwaysEat(true).nutrition(100).saturation(100).build()
        );

        player.getInventory().addItem(item);
    }
}
