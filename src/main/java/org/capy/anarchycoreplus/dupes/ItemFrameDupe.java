package org.capy.anarchycoreplus.dupes;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.capy.anarchycoreplus.utils.Utils;

/*
    code from https://github.com/notlightbeat/FrameDupe/
*/

public class ItemFrameDupe implements Listener {
    @EventHandler
    private void onFrameBreak(EntityDamageByEntityEvent event) {
        if (!Utils.getConfig().getBoolean("frame-dupe.enabled")) {
            return;
        }

        if (event.getEntityType() == EntityType.ITEM_FRAME) {
            int rng = (int)Math.round(Math.random() * 100);
            if (rng < Utils.getConfig().getInt("frame-dupe.probability")) {
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), ((ItemFrame) event.getEntity()).getItem());
            }
        }
    }
}
