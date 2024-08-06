package org.capy.anarchycoreplus.commands.tpa;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.capy.anarchycoreplus.utils.Utils;

import java.util.UUID;

public class TpAcceptCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        TpaManager tpaManager = new TpaManager();
        int delay = tpaManager.getDelay();

        Player p = (Player) sender;

        UUID requestSenderId = tpaManager.getRequestSender(p.getUniqueId());
        if (requestSenderId == null) {
            Utils.sendMessage(p, Utils.getConfig().getString("tpa-command.teleport-failed"));
            return true;
        }

        Player requestSender = Bukkit.getPlayer(requestSenderId);
        if (requestSender == null) {
            Utils.sendMessage(p, Utils.getConfig().getString("tpa-command.target-not-online").replaceAll("<p>", requestSender.getName()));
            return true;
        }

        tpaManager.removeRequest(p.getUniqueId());
        Utils.sendMessage(p, Utils.getConfig().getString("tpa-command.you-accepted"));
        Utils.sendMessage(requestSender, Utils.getConfig().getString("tpa-command.accepted"));

        Bukkit.getScheduler().runTaskLater((Plugin) p, () -> {
            if (requestSender.isOnline() && p.isOnline()) {
                requestSender.teleport(p.getLocation());
                Utils.sendMessage(requestSender, Utils.getConfig().getString("tpa-command.teleporting").replace("<p>", p.getName()));
            } else {
                Utils.sendMessage(requestSender, Utils.getConfig().getString("tpa-command.teleport-failed"));
            }
        }, delay * 20L);

        return true;
    }
}