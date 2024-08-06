package org.capy.anarchycoreplus.commands.ignore;

import org.capy.anarchycoreplus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;


public class IgnoreCommand implements CommandExecutor {
    public static HashMap<String, ArrayList<String>> ignoredlist = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player ignorer = (Player) sender;
        if (args.length == 0){
            Utils.sendMessage(ignorer, Utils.getConfig().getString("ignore-command.usage"));
            return true;
        }

        if (Bukkit.getPlayer(args[0]) == null) {
            Utils.sendMessage(ignorer, Utils.getConfig().getString("ignore-command.player-doesntexist"));
            return true;
        }

        Player toignore = Bukkit.getPlayer(args[0]);

        if (ignoredlist.get(ignorer) == null){
            ignorenew(ignorer, toignore);
            Utils.sendMessage(ignorer, Utils.getConfig().getString("ignore-command.ignored").replaceAll("<player>", toignore.getName()));
            return true;
        }

        if (ignoredlist.get(ignorer).contains(toignore.getUniqueId().toString())) {
            unignore(ignorer, toignore);
            Utils.sendMessage(ignorer, Utils.getConfig().getString("ignore-command.unignored").replaceAll("<player>", toignore.getName()));
            return true;
        }

        ignore(ignorer, toignore);
        Utils.sendMessage(ignorer, Utils.getConfig().getString("ignore-command.ignored").replaceAll("<player>", toignore.getName()));
        return true;
    }

    public void unignore(Player ignorer, Player toignore){
        ArrayList<String> ignoreduuids = ignoredlist.get(ignorer);
        ignoreduuids.remove(toignore.getUniqueId().toString());
        ignoredlist.put(ignorer.getUniqueId().toString(), ignoreduuids);
    }

    public void ignore(Player ignorer, Player toignore){
        ArrayList<String> ignoreduuids = ignoredlist.get(ignorer);
        ignoreduuids.add(toignore.getUniqueId().toString());
        ignoredlist.put(ignorer.getUniqueId().toString(), ignoreduuids);
    }

    public void ignorenew(Player ignorer, Player toignore){
        ArrayList<String> ignoreduuids = new ArrayList<>();
        ignoreduuids.add(toignore.getUniqueId().toString());
        ignoredlist.put(ignorer.getUniqueId().toString(), ignoreduuids);
    }
}