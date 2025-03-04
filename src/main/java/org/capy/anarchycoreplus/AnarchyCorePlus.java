package org.capy.anarchycoreplus;

import org.bukkit.permissions.Permission;
import org.capy.anarchycoreplus.anti_illegals.AntiBlockPlaceEvent;
import org.capy.anarchycoreplus.anti_illegals.OpenInventoryEvent;
import org.capy.anarchycoreplus.anti_illegals.PlayerDealDamageEvent;
import org.capy.anarchycoreplus.commands.*;
import org.capy.anarchycoreplus.commands.ignore.IgnoreChatEvent;
import org.capy.anarchycoreplus.commands.ignore.IgnoreCommand;
import org.capy.anarchycoreplus.commands.namecolor.ChatEvent;
import org.capy.anarchycoreplus.commands.namecolor.NameColorCommand;
import org.capy.anarchycoreplus.commands.tpa.TpAcceptCommand;
import org.capy.anarchycoreplus.commands.tpa.TpDenyCommand;
import org.capy.anarchycoreplus.commands.tpa.TpaCommand;
import org.capy.anarchycoreplus.dupes.ItemFrameDupe;
import org.capy.anarchycoreplus.limiters.BlockPlaceEvent;
import org.capy.anarchycoreplus.limiters.WitherSpawnEvent;
import org.capy.anarchycoreplus.settings.*;
import org.capy.anarchycoreplus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class AnarchyCorePlus extends JavaPlugin {
    Logger logger = getLogger();
    public static boolean usingPAPI = false;

    public void onEnable() {
        //PlaceholderAPI
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            usingPAPI = true;
        }

        //bStats
        int pluginId = 10837;
        Metrics metrics = new Metrics(this, pluginId);

        saveDefaultConfig();
        registerCommands();
        registerEvents();
        registerPermissions();
        logBanner();
        logger.info("Loaded succesfully");
    }

    public void registerEvents(){
        Bukkit.getServer().getPluginManager().registerEvents(new MoveEvents(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CommandEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiBlockPlaceEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new RespawnEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OpenInventoryEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MotdEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDealDamageEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new IgnoreChatEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ItemFrameDupe(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WitherSpawnEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DisplayTabEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPlaceEvent(), this);
    }

    public void registerCommands(){
        if(Utils.getConfig().getBoolean("enabled-commands.help")){
            this.getCommand("help").setExecutor(new HelpCommand());
        }
        if(Utils.getConfig().getBoolean("enabled-commands.bed")){
            this.getCommand("bed").setExecutor(new BedCommand());
        }
        if(Utils.getConfig().getBoolean("enabled-commands.discord")){
            this.getCommand("discord").setExecutor(new DiscordCommand());
        }
        if(Utils.getConfig().getBoolean("enabled-commands.stats")){
            this.getCommand("stats").setExecutor(new InfoCommand());
        }
        if(Utils.getConfig().getBoolean("enabled-commands.joindate")){
            this.getCommand("joindate").setExecutor(new JoindateCommand());
        }
        if(Utils.getConfig().getBoolean("enabled-commands.ping")){
            this.getCommand("ping").setExecutor(new PingCommand());
        }
        if(Utils.getConfig().getBoolean("enabled-commands.toggledeathmsgs")){
            this.getCommand("toggledeathmsgs").setExecutor(new ToggleDeathMsgsCommand());
        }
        if(Utils.getConfig().getBoolean("enabled-commands.togglejoinmsgs")){
            this.getCommand("togglejoinmsgs").setExecutor(new ToggleJoinMessages());
        }
        if(Utils.getConfig().getBoolean("enabled-commands.corereload")){
            this.getCommand("corereload").setExecutor(new CoreReloadCommand());
        }
        if(Utils.getConfig().getBoolean("enabled-commands.kill")){
            this.getCommand("kill").setExecutor(new KillCommand());
        }
        if(Utils.getConfig().getBoolean("enabled-commands.nc")){
            this.getCommand("nc").setExecutor(new NameColorCommand());
        }
        if(Utils.getConfig().getBoolean("enabled-commands.ignore")){
            this.getCommand("ignore").setExecutor(new IgnoreCommand());
        }
        if(Utils.getConfig().getBoolean("enabled-commands.playtime")){
            this.getCommand("playtime").setExecutor(new PlaytimeCommand());
        }
        if(Utils.getConfig().getBoolean("enabled-commands.tpa")) {
            this.getCommand("tpa").setExecutor(new TpaCommand());
            this.getCommand("tpaccept").setExecutor(new TpAcceptCommand());
            this.getCommand("tpdeny").setExecutor(new TpDenyCommand());
        }
    }

    public void registerPermissions() {
        Bukkit.getServer().getPluginManager().addPermission(new Permission(Utils.getConfig().getString("permissions.corereload")));
        Bukkit.getServer().getPluginManager().addPermission(new Permission(Utils.getConfig().getString("permissions.nc")));
        Bukkit.getServer().getPluginManager().addPermission(new Permission(Utils.getConfig().getString("permissions.nc-format")));
        Bukkit.getServer().getPluginManager().addPermission(new Permission(Utils.getConfig().getString("permissions.bypass-whitelist")));
        Bukkit.getServer().getPluginManager().addPermission(new Permission(Utils.getConfig().getString("permissions.bypass-illegals")));
    }

    public void logBanner() {
        logger.info("");
        logger.info(" █████╗     ██████╗   ██████╗ ");
        logger.info("██╔══██╗   ██╔════╝   ██╔══██╗");
        logger.info("███████║   ██║        ██████╔╝");
        logger.info("██╔══██║   ██║        ██╔═══╝ ");
        logger.info("██║  ██║██╗╚██████╗██╗██║     ");
        logger.info("╚═╝  ╚═╝╚═╝ ╚═════╝╚═╝╚═╝     ");
        logger.info("");
    }

    public static AnarchyCorePlus getPlugin(){
        return getPlugin(AnarchyCorePlus.class);
    }
}
