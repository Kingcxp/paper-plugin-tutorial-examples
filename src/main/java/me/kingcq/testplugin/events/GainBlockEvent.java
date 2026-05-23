package me.kingcq.testplugin.events;

import me.kingcq.testplugin.TestPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

public class GainBlockEvent implements Listener {
    @EventHandler
    public void onGainBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (
            !event.getAction().equals(Action.RIGHT_CLICK_AIR)
            && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
        ) {
            return;
        }
        if (!item.getType().equals(Material.IRON_SWORD) || player.getLevel() == 3) {
            return;
        }
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1f, 2f);
        player.setLevel(3);
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new SwordBlockEvent(player), TestPlugin.plugin);
    }
}
