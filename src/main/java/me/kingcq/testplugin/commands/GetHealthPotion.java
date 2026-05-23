package me.kingcq.testplugin.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class GetHealthPotion implements BasicCommand {

    public static NamespacedKey usesLeft = new NamespacedKey("test_plugin", "health_potion_use_left");
    public static NamespacedKey maxUses = new NamespacedKey("test_plugin", "health_potion_max_use");

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        CommandSender sender = commandSourceStack.getSender();
        if (!(sender instanceof Player player)) {
            return;
        }
        ItemStack potion = new ItemStack(Material.HONEY_BOTTLE);
        potion.editPersistentDataContainer(container -> {
            container.set(
                    usesLeft,
                    PersistentDataType.INTEGER,
                    5
            );
            container.set(
                    maxUses,
                    PersistentDataType.INTEGER,
                    5
            );
        });
        ItemMeta meta = potion.getItemMeta();
        meta.displayName(Component.text("Health Potion", NamedTextColor.GREEN));
        meta.lore(List.of(
                Component.text("A simple health potion", NamedTextColor.LIGHT_PURPLE),
                Component.text("Uses left : 5 / 5")
        ));
        potion.setItemMeta(meta);
        player.getInventory().addItem(potion);
    }
}
