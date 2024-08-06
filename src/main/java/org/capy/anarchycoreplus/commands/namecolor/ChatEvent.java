package org.capy.anarchycoreplus.commands.namecolor;

import org.capy.anarchycoreplus.AnarchyCorePlus;
import org.capy.anarchycoreplus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if (!NameColorCommand.customname.containsKey(p)) {
            return;
        }

        String customName = NameColorCommand.customname.get(p);
        if (customName == null) {
            return;
        }

        e.setCancelled(true);
        Bukkit.getScheduler().runTask(AnarchyCorePlus.getPlugin(AnarchyCorePlus.class), () -> {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                Utils.sendRawMessage(pl, customName + e.getMessage());
            }
        });
    }
}