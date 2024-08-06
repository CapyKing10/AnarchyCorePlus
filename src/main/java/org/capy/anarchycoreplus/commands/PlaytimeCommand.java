package org.capy.anarchycoreplus.commands;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.capy.anarchycoreplus.utils.Utils;

import java.util.concurrent.TimeUnit;

public class PlaytimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        if (args.length == 0) {
            long playtime = p.getStatistic(Statistic.PLAY_ONE_MINUTE) * 50L; // Convert ticks to milliseconds
            String playtimeFormatted = formatPlaytime(playtime);
            String message = Utils.getConfig().getString("playtime.playtime-msg").replace("<pt>", playtimeFormatted);
            Utils.sendMessage(p, message);
            return true;
        }

        if (args.length == 1) {
            if (!Utils.getConfig().getBoolean("playtime.allow-other-users")) {
                Utils.sendMessage(p, Utils.getConfig().getString("global.no-permission-message"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                String message = Utils.getConfig().getString("playtime.playtime-notonline").replace("<p>", args[0]);
                Utils.sendMessage(p, message);
                return true;
            }

            if (!Utils.getConfig().getBoolean("playtime.allow-other-users")) {
                Utils.sendMessage(p, Utils.getConfig().getString("playtime.no-permission-others").replaceAll("<p>", args[0]));
                return true;
            }

            long playtime = target.getStatistic(Statistic.PLAY_ONE_MINUTE) * 50L; // Convert ticks to milliseconds
            String playtimeFormatted = formatPlaytime(playtime);
            String message = Utils.getConfig().getString("playtime.playtime-msg-other")
                    .replace("<p>", target.getName())
                    .replace("<pt>", playtimeFormatted);
            Utils.sendMessage(p, message);
            return true;
        }

        return false;
    }

    private String formatPlaytime(long playtimeMillis) {
        long days = TimeUnit.MILLISECONDS.toDays(playtimeMillis);
        long hours = TimeUnit.MILLISECONDS.toHours(playtimeMillis) % 24;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(playtimeMillis) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(playtimeMillis) % 60;

        return String.format("%d days, %02d:%02d:%02d", days, hours, minutes, seconds);
    }
}
