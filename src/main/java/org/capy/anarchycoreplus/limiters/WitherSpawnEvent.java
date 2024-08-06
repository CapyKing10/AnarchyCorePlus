package org.capy.anarchycoreplus.limiters;

import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.capy.anarchycoreplus.utils.Utils;

public class WitherSpawnEvent implements Listener {
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        if (e.getEntityType() != EntityType.WITHER) return;

        Chunk chunk = e.getLocation().getChunk();
        int witherCount = 0;
        int witherLimit = Utils.getConfig().getInt("wither-limit.limit");

        for (Entity entity : chunk.getEntities()) {
            if (entity.getType() == EntityType.WITHER) {
                witherCount++;
            }
        }

        if (witherCount >= witherLimit) {
            e.setCancelled(true);
        }
    }
}
