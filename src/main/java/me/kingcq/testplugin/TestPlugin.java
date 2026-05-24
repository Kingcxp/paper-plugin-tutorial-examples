package me.kingcq.testplugin;

import me.kingcq.testplugin.commands.GetCustomSword;
import me.kingcq.testplugin.commands.RocketUI;
import me.kingcq.testplugin.commands.ToAlienDimension;
import me.kingcq.testplugin.events.RocketUIClick;
import me.kingcq.testplugin.events.ScoreboardAndBar;
import me.kingcq.testplugin.recipes.FurnaceRecipes;
import me.kingcq.testplugin.worldgenerator.MyBiomeProvider;
import me.kingcq.testplugin.worldgenerator.MyWorldGenerator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.generator.ChunkGenerator;
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

//        WorldCreator creator = new WorldCreator("alien_dimension");
//
//        creator.generator(new MyWorldGenerator());
//        creator.generateStructures(false);
//        creator.biomeProvider(new MyBiomeProvider());
//
//        Bukkit.createWorld(creator);
//
//        registerCommand("to_alien", new ToAlienDimension());
        registerCommand("get_sword", new GetCustomSword());
    }

    @Override
    public void onDisable() {
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(Component.text("Goodbye from TestPlugin!", NamedTextColor.LIGHT_PURPLE));
    }
}
