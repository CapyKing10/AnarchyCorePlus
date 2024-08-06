package org.capy.anarchycoreplus.anti_illegals;

import org.bukkit.GameMode;
import org.bukkit.inventory.ItemStack;
import org.capy.anarchycoreplus.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class AntiBlockPlaceEvent implements Listener {
    List<String> illegalBlocks = Utils.getConfig().getStringList("antiblockplaces.blocks");

    @EventHandler
    public void onPlaceEvent(org.bukkit.event.block.BlockPlaceEvent e) {
        if(e.getPlayer().isOp() || e.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }

        if(!Utils.getConfig().getBoolean("antiblockplaces.enabled")){
            return;
        }

        String blockType = e.getBlock().getType().toString();
        if (illegalBlocks.contains(blockType)) {
            e.setCancelled(true);
            Utils.sendMessage(e.getPlayer(), Utils.getConfig().getString("antiblockplaces.message").replaceAll("<block>", blockType.toLowerCase()));

            ItemStack itemInHand = e.getPlayer().getInventory().getItemInMainHand();
            itemInHand.setAmount(0);
        }
    }
}
