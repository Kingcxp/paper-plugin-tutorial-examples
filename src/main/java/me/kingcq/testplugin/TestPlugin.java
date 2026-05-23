package me.kingcq.testplugin;

import me.kingcq.testplugin.commands.GetHealthPotion;
import me.kingcq.testplugin.commands.RocketCommand;
import me.kingcq.testplugin.events.DrinkHealthPotion;
import me.kingcq.testplugin.events.StartRocketByBlazeRod;
import me.kingcq.testplugin.tasks.RocketTask;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class TestPlugin extends JavaPlugin {
    public static TestPlugin plugin = null;

    @Override
    public void onEnable() {
        plugin = this;

        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(Component.text("Hello from TestPlugin!", NamedTextColor.GOLD));

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new StartRocketByBlazeRod(), this);
        pm.registerEvents(new DrinkHealthPotion(), this);

        RocketCommand command = new RocketCommand();
        registerCommand("rocket", command);

        GetHealthPotion getPotionCommand = new GetHealthPotion();
        registerCommand("get_health_potion", getPotionCommand);

        saveDefaultConfig();

        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));
        RocketTask.ticksTotal = config.getInt("rocket-time-ticks");
        RocketTask.upwardSpeed = config.getDouble("rocket-speed");
    }

    @Override
    public void onDisable() {
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(Component.text("Goodbye from TestPlugin!", NamedTextColor.LIGHT_PURPLE));
    }
}
