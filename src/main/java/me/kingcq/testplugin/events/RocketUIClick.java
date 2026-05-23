package me.kingcq.testplugin.events;

import me.kingcq.testplugin.TestPlugin;
import me.kingcq.testplugin.commands.RocketUI;
import me.kingcq.testplugin.tasks.RocketTask;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class RocketUIClick implements Listener {
    @EventHandler
    public void onRocketClick(@NotNull InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }
        ItemStack item = event.getCurrentItem();
        if (item == null) {
            return;
        }
        if (Boolean.TRUE.equals(item.getPersistentDataContainer().get(RocketUI.rocketTriggerKey, PersistentDataType.BOOLEAN))) {
            return;
        }
        event.setCancelled(true);
        player.closeInventory();
        RocketTask task = new RocketTask(player);
        task.runTaskTimer(TestPlugin.plugin, 0, 0);
    }
}
