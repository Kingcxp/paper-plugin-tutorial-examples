package me.kingcq.testplugin.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ToAlienDimension implements BasicCommand {
    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, String @NotNull [] args) {
        CommandSender sender = commandSourceStack.getSender();
        if (sender instanceof Player player) {
            World world = Bukkit.getWorld("alien_dimension");
            if (world != null) {
                player.teleport(new Location(world, 0, 70, 0));
            }
        }
    }
}
