package org.capy.anarchycoreplus.commands.namecolor;

import org.capy.anarchycoreplus.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class NameColorCommand implements CommandExecutor {

    public static HashMap<Player, String> customname = new HashMap<>();
    private final HashMap<String, String> colorCodes = new HashMap<>();

    public NameColorCommand() {
        // Initialize color codes
        colorCodes.put("black", "§0");
        colorCodes.put("darkblue", "§1");
        colorCodes.put("darkgreen", "§2");
        colorCodes.put("darkaqua", "§3");
        colorCodes.put("red", "§4");
        colorCodes.put("darkpurple", "§5");
        colorCodes.put("gold", "§6");
        colorCodes.put("gray", "§7");
        colorCodes.put("darkgray", "§8");
        colorCodes.put("blue", "§9");
        colorCodes.put("green", "§a");
        colorCodes.put("aqua", "§b");
        colorCodes.put("lightpurple", "§d");
        colorCodes.put("yellow", "§e");
        colorCodes.put("white", "§f");
        // Formatting options
        colorCodes.put("bold", "§l");
        colorCodes.put("magic", "§k");
        colorCodes.put("underline", "§n");
        colorCodes.put("strike", "§m");
        colorCodes.put("italic", "§o");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(Utils.getConfig().getString("permissions.nc"))) {
            Utils.sendMessage(player, Utils.getConfig().getString("global.no-permission-message"));
            return true;
        }

        if (args.length == 0) {
            Utils.sendMessage(player, Utils.getConfig().getString("nc-command.usage-message"));
            return true;
        }

        String arg = args[0].toLowerCase();
        if (arg.equals("reset")) {
            customname.remove(player);
            Utils.sendMessage(player, Utils.getConfig().getString("nc-command.reset-message"));
            return true;
        }

        if (colorCodes.containsKey(arg)) {
            if (isFormattingCode(arg) && !player.hasPermission(Utils.getConfig().getString("permissions.nc-format"))) {
                Utils.sendMessage(player, Utils.getConfig().getString("global.no-permission-message"));
                return true;
            }
            customname.put(player, "&f<" + colorCodes.get(arg) + player.getName() + "&f> ");
            Utils.sendMessage(player, Utils.getConfig().getString("nc-command.success-message").replace("<color>", arg));
            return true;
        }

        Utils.sendMessage(player, Utils.getConfig().getString("nc-command.error-message"));
        return true;
    }

    private boolean isFormattingCode(String code) {
        return code.equals("bold") || code.equals("magic") || code.equals("underline") || code.equals("strike") || code.equals("italic");
    }
}