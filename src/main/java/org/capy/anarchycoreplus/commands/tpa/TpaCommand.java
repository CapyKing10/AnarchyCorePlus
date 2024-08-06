package org.capy.anarchycoreplus.commands.tpa;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.capy.anarchycoreplus.utils.Utils;

public class TpaCommand  implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        TpaManager tpaManager = new TpaManager();

        Player p = (Player) sender;

        if (args.length != 1) {
            Utils.sendMessage(p, Utils.getConfig().getString("tpa-command.no-user-provided"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            Utils.sendMessage(p, Utils.getConfig().getString("tpa-command.target-not-online").replace("<p>", args[0]));
            return true;
        }

        if (target == p) {
            Utils.sendMessage(p, Utils.getConfig().getString("tpa-command.cant-tpa-to-self"));
            return true;
        }

        tpaManager.sendTpaRequest(p, target);
        Utils.sendMessage(p, Utils.getConfig().getString("tpa-command.sent").replace("<p>", target.getName()));
        Utils.sendMessage(target, Utils.getConfig().getString("tpa-command.received").replace("<p>", p.getName()));
        return true;
    }
}