package me.kingcq.testplugin;

import me.kingcq.testplugin.commands.RocketUI;
import me.kingcq.testplugin.events.RocketUIClick;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestPlugin extends JavaPlugin {
    public static TestPlugin plugin = null;

    @Override
    public void onEnable() {
        plugin = this;

        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(Component.text("Hello from TestPlugin!", NamedTextColor.GOLD));

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new RocketUIClick(), this);

        registerCommand("/rocket_ui", new RocketUI());
    }

    @Override
    public void onDisable() {
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(Component.text("Goodbye from TestPlugin!", NamedTextColor.LIGHT_PURPLE));
    }
}
