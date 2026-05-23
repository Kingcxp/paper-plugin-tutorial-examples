package me.kingcq.testplugin.events;

import me.kingcq.testplugin.TestPlugin;
import me.kingcq.testplugin.tasks.RocketTask;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class StartRocketByBlazeRod implements Listener {

    @EventHandler
    public void onBlazeRodUse(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!item.getType().equals(Material.BLAZE_ROD)) {
            return;
        }
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            return;
        }

        RocketTask task = new RocketTask(player);
        task.runTaskTimer(TestPlugin.plugin, 0, 0);
    }
}
