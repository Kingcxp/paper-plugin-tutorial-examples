package me.kingcq.testplugin.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class SlimeShieldEvent implements Listener {
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player victim)) {
            return;
        }
        Entity entity = event.getDamager();
        ItemStack item = victim.getInventory().getItemInMainHand();
        if (item.getType().equals(Material.SLIME_BLOCK)) {
            event.setCancelled(true);
            victim.playSound(victim.getLocation(), Sound.BLOCK_SLIME_BLOCK_BREAK, 1f, 1.2f);
            if (item.getAmount() == 1) {
                victim.getInventory().setItemInMainHand(null);
            } else {
                item.setAmount(item.getAmount() - 1);
            }
        }
        Vector direction = entity.getLocation().getDirection();
        direction.multiply(-1);
        direction.normalize();
        direction.multiply(15);
        entity.setVelocity(direction);
    }
}
