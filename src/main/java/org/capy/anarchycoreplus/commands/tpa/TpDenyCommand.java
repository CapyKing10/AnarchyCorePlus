package org.capy.anarchycoreplus.commands.tpa;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.capy.anarchycoreplus.utils.Utils;

import java.util.UUID;

public class TpDenyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        TpaManager tpaManager = new TpaManager();
        Player p = (Player) sender;

        UUID requestSenderId = tpaManager.getRequestSender(p.getUniqueId());
        if (requestSenderId == null) {
            Utils.sendMessage(p, Utils.getConfig().getString("tpa-command.teleport-failed"));
            return true;
        }

        Player requestSender = Bukkit.getPlayer(requestSenderId);
        if (requestSender == null) {
            Utils.sendMessage(p, Utils.getConfig().getString("tpa-command.target-not-online").replace("<p>", requestSender.getName()));
            return true;
        }

        tpaManager.removeRequest(p.getUniqueId());
        Utils.sendMessage(p, Utils.getConfig().getString("tpa-command.you-denied"));
        Utils.sendMessage(requestSender, Utils.getConfig().getString("tpa-command.denied"));
        return true;
    }
}