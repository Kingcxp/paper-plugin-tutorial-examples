package me.kingcq.testplugin.events;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

public class ScoreboardAndBar implements Listener {
    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendActionBar(Component.text("你正在发光！", NamedTextColor.GOLD));
        player.setGlowing(true);
        BossBar captureBar = BossBar.bossBar(
                Component.text("A 点占领中..."),
                0.5f, BossBar.Color.RED,
                BossBar.Overlay.NOTCHED_10
        );
        player.showBossBar(captureBar);

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective(
                "game_stats",
                Criteria.DUMMY,
                Component.text("★ 战区数据 ★")
        );
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.getScore("当前击杀:").setScore(5);
        obj.getScore("剩余存活:").setScore(12);
        player.setScoreboard(board);

        Team redTeam = board.registerNewTeam("Red");

        redTeam.prefix(Component.text("[红队] ", NamedTextColor.RED));
        redTeam.color(NamedTextColor.RED);

        redTeam.setAllowFriendlyFire(false);

        redTeam.addEntry(player.getName());
        redTeam.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
    }
}
