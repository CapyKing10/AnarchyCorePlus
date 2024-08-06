package org.capy.anarchycoreplus.commands;


import org.capy.anarchycoreplus.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ToggleDeathMsgsCommand implements CommandExecutor {
    public static List<Player> muted = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if (!Utils.getConfig().getBoolean("death-messages.enabled")) {
            for(Player player : muted){
                muted.remove(player);
            }
            return true;
        }

        if (muted.contains(p)) {
            muted.remove(p);
            Utils.sendMessage(p, Utils.getConfig().getString("death-messages.unmuted"));
            return true;
        }

        muted.add(p);
        Utils.sendMessage(p, Utils.getConfig().getString("death-messages.muted"));

        return true;
    }
}
