package me.kingcq.testplugin.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class RocketUI implements BasicCommand {
    public static NamespacedKey rocketTriggerKey = new NamespacedKey("test_plugin", "ui_rocket_trigger");

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, String @NotNull [] args) {
        CommandSender sender = commandSourceStack.getSender();
        if (!(sender instanceof Player player)) {
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, 27, Component.text("🚀 火箭 UI", NamedTextColor.GREEN));
        ItemStack rocketTrigger = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta rocketMeta = rocketTrigger.getItemMeta();
        rocketMeta.displayName(Component.text("火箭启动！", NamedTextColor.GOLD));
        rocketTrigger.setItemMeta(rocketMeta);
        rocketTrigger.editPersistentDataContainer(container -> container.set(
                rocketTriggerKey,
                PersistentDataType.BOOLEAN,
                true
        ));
        inventory.setItem(13, rocketTrigger);
        player.openInventory(inventory);
    }
}
