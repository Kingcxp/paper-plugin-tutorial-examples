package me.kingcq.testplugin.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.kingcq.testplugin.TestPlugin;
import me.kingcq.testplugin.tasks.RocketTask;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RocketCommand implements BasicCommand {
    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, String @NotNull [] args) {
        CommandSender sender = commandSourceStack.getSender();
        if (!(sender instanceof Player player)) {
            return;
        }
        RocketTask task = new RocketTask(player);
        task.runTaskTimer(TestPlugin.plugin, 0, 0);
    }

    @Override
    public boolean canUse(@NotNull CommandSender sender) {
        return sender.isOp();
    }
}
