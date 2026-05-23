package me.kingcq.testplugin.events;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SwordBlockEvent implements Listener {
    Player player = null;

    SwordBlockEvent(Player player) {
        this.player = player;
    }

    @EventHandler
    public void onBlock(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player victim)) {
            return;
        }
        if (!victim.equals(player)) {
            return;
        }
        victim.setLevel(victim.getLevel() - 1);
        victim.playSound(victim.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1f, 2f);
        event.setCancelled(true);
        if (victim.getLevel() == 0) {
            HandlerList.unregisterAll(this);
        }
    }
}
