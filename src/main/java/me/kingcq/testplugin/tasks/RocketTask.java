package me.kingcq.testplugin.tasks;

import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class RocketTask extends BukkitRunnable {

    private long ticks = 0;
    private Player player = null;

    public static long ticksTotal = 100;
    public static double upwardSpeed = 1;

    public RocketTask(@NotNull Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        player.setVelocity(new Vector(0, upwardSpeed, 0));
        World world = player.getWorld();
        world.spawnParticle(Particle.FLAME, player.getLocation(), 5, null);
        ++ticks;
        if (ticks >= ticksTotal) {
            cancel();
        }
    }
}
