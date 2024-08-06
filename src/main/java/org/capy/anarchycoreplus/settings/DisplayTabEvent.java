package org.capy.anarchycoreplus.settings;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.capy.anarchycoreplus.commands.namecolor.NameColorCommand;
import org.capy.anarchycoreplus.utils.Utils;

import java.util.List;

public class DisplayTabEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (Utils.getConfig().getBoolean("tab-list.enabled")) {
            updateTab();
        }
    }

    public static void updateTab() {
        List<String> headerList = Utils.getConfig().getStringList("tab-list.header");
        List<String> footerList = Utils.getConfig().getStringList("tab-list.footer");

        String header = String.join("\n", headerList).replaceAll("&", "§");
        String footer = String.join("\n", footerList).replaceAll("&", "§");

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setPlayerListHeaderFooter(header, footer);

            if (NameColorCommand.customname.containsKey(player)) {
                String customName = NameColorCommand.customname.get(player);
                if (customName != null) {
                    player.setPlayerListName(customName);
                }
            } else {
                player.setPlayerListName(player.getName());
            }
        }
    }
}
