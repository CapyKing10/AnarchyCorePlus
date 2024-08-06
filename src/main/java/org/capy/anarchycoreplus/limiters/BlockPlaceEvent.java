package org.capy.anarchycoreplus.limiters;

import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.capy.anarchycoreplus.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockPlaceEvent implements Listener {
    private final Map<Material, Integer> chunkLimits = new HashMap<>();

    private void loadChunkLimits() {
        FileConfiguration config = Utils.getConfig();
        for (String key : config.getConfigurationSection("chunk-limits").getKeys(false)) {
            Material material = Material.getMaterial(key);
            if (material != null) {
                chunkLimits.put(material, config.getInt("chunk-limits." + key));
            }
        }
    }

    @EventHandler
    public void onBlockPlace(org.bukkit.event.block.BlockPlaceEvent event) {
        loadChunkLimits();
        Material material = event.getBlock().getType();
        if (chunkLimits.containsKey(material)) {
            Chunk chunk = event.getBlock().getChunk();
            long count = countBlocksInChunk(chunk, material);
            int limit = chunkLimits.get(material);
            if (count >= limit) {
                event.setCancelled(true);
                Utils.sendMessage(event.getPlayer(), Utils.getConfig().getString("chunk-limits.limited-message").replaceAll("<limit>", String.valueOf(limit)));
            }
        }
    }

    private long countBlocksInChunk(Chunk chunk, Material material) {
        long count = 0;
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < chunk.getWorld().getMaxHeight(); y++) {
                    if (chunk.getBlock(x, y, z).getType() == material) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
