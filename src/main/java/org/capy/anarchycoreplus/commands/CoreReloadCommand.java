package org.capy.anarchycoreplus.commands;

import org.capy.anarchycoreplus.AnarchyCorePlus;
import org.capy.anarchycoreplus.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoreReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (!p.hasPermission(Utils.getConfig().getString("permissions.corereload"))) {
            Utils.sendRawMessage(p, Utils.getConfig().getString("global.no-permission-message"));
        }

        AnarchyCorePlus.getPlugin().reloadConfig();
        Utils.sendRawMessage(p, Utils.getConfig().getString("core-reload.message-reload"));

        return true;
    }
}