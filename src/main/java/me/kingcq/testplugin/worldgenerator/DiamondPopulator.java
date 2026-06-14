package me.kingcq.testplugin.worldgenerator;

import org.bukkit.Material;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class DiamondPopulator extends BlockPopulator {
    @Override
    public void populate(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull LimitedRegion region) {
        int worldX = (chunkX << 4) + random.nextInt(16);
        int worldZ = (chunkZ << 4) + random.nextInt(16);

        int startY = 61;

        region.setType(worldX + 1, startY, worldZ, Material.DIAMOND_BLOCK);
        region.setType(worldX - 1, startY, worldZ, Material.DIAMOND_BLOCK);
        region.setType(worldX, startY + 1, worldZ, Material.DIAMOND_BLOCK);
        region.setType(worldX, startY + 2, worldZ, Material.DIAMOND_BLOCK);
    }
}
