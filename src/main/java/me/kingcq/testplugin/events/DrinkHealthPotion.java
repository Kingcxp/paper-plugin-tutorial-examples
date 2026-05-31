package me.kingcq.testplugin.events;

import me.kingcq.testplugin.commands.GetHealthPotion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.Objects;

public class DrinkHealthPotion implements Listener {

    @EventHandler
    public void onHealthPotionDrink(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (!item.getType().equals(Material.HONEY_BOTTLE)) {
            return;
        }
        if (item.getPersistentDataContainer().get(GetHealthPotion.maxUses, PersistentDataType.INTEGER) != null) {
            int maxUses = item.getPersistentDataContainer().get(
                    GetHealthPotion.maxUses,
                    PersistentDataType.INTEGER
            );
            int useLeft = item.getPersistentDataContainer().get(
                    GetHealthPotion.usesLeft,
                    PersistentDataType.INTEGER
            );
            useLeft -= 1;
            player.heal(6d);
            if (useLeft == 0) {
                player.sendMessage(Component.text("The potion has been used up!", NamedTextColor.GREEN));
                player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1f, 1f);
                event.setReplacement(new ItemStack(Material.AIR));
            } else {
                item.lore(List.of(
                        Component.text("A simple health potion", NamedTextColor.LIGHT_PURPLE),
                        Component.text("Uses left : " + useLeft + " / " + maxUses)
                ));
                int finalUseLeft = useLeft;
                item.editPersistentDataContainer((container) -> container.set(
                        GetHealthPotion.usesLeft,
                        PersistentDataType.INTEGER,
                        finalUseLeft
                ));
                event.setReplacement(item);
            }
        }
    }
}
