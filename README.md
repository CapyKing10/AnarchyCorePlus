![logo](src/main/resources/images/logo.png)

[![Discord](https://img.shields.io/discord/1154497694597910620?style=flat-square&logo=discord&label=discord&color=blue)](https://dsc.gg/capyking10)
![Generic badge](https://img.shields.io/badge/Developing-v1.0.0-blue.svg?style=flat-square)
![Generic badge](https://img.shields.io/badge/Bukkit-1.20.4-red.svg?style=flat-square)

## Information
### About
> A lightweight, handy, fully-configurable Paper plugin that serves as core-plugin for anarchy servers.

> Original plugin by banimania can be found [here](https://github.com/banimania/AnarchyCore/tree/main)!
### Features:
* Very useful for anarchy servers.
* Fully customizable.
* Easy to configure.
* You can set random MOTDS from configuration.
* You can set custom Tab List header and footers.
* You can patch nether roof and nether bottom!
* You're able to disable every feature on the plugin, including commands!
* Includes a random respawn system so players that don't have a bed will not spawn in the same place.
* Active support in the Discord server
* Has a 32k patch, if enabled, people will not be able to deal damage using them.
* Anti-Illegals system

### Commands:
* /help - Helps you.
* /stats - Shows info about the world
* /bed - Locates your current bed.
* /joindate - Shows joindate.
* /ping - Shows players ping.
* /discord - Shows your Discord Link
* /toggledeathmsgs - Toggles death msgs.
* /togglejoinmsgs - Toggles join msgs.
* /nc - Changes your color nickname in chat.
* /kill - Kills you.
* /corereload - Reloads the plugins config (only for operators).
* /ping - Shows your ping.
* /playtime - shows your or someone else's playtime.
* /ignore - block someone's messages from showing

# Configuration
<details>
    <summary>Settings</summary>
    
```yaml
#Global settings for the plugin
global:
  #Name shown in tab
  name: "AnarchyCorePlus"
  #Prefix shown on commands and messages from the plugin
  prefix: "&8[&9&lAnarchy&3&lCore&1&lPlus&8] &r"
  #Info shown on joining and in the tab
  news: "[AnarchyCorePlus] Change this in config.yml!"
  #No-permission message
  no-permission-message: "&cYou're not allowed to do this!"

#Links of the server
links:
  discord: "yourserver.com/discord"
  discord-message: "&6Join our Discord server: &c"

#Command whitelist
#When a normal player runs a command, if it's not on this list, it will return a specified message.
command-whitelist:
  enabled: true
  whitelist-message: "&fUnknown command. Type \"/help\" for help."
  whitelisted-commands:
    - "help"
    - "tps"
    - "ignore"
    - "ignorelist"
    - "w"
    - "msg"
    - "tell"
    - "r"
    - "reply"
    - "w"
    - "l"
    - "last"
    - "pm"
    - "tell"
    - "togglemsgs"
    - "togglejoinmsgs"
    - "togglechat"
    - "toggledeathmsgs"
    - "stats"
    - "kill"
    - "ping"
    - "bed"
    - "joindate"
    - "dmt"
    - "discord"
    - "nc"

#Manage the enabled commands
enabled-commands:
  #Set a command to false to fully disable it. Also check command whitelist config above.
  help: true
  bed: true
  discord: true
  stats: true
  joindate: true
  ping: true
  toggledeathmsgs: true
  togglejoinmsgs: true
  corereload: true
  kill: true
  nc: true
  ignore: true
  ignorelist: true
  playtime: true

#Manage the plugin permissions
permissions:
  corereload: "anarchycore.corereload"
  nc: "anarchycore.nc"
  nc-format: "anarchycore.nc-format"
  bypass-whitelist: "anarchycore.bypasswhitelist"
  bypass-illegals: "anarchycore.bypassillegals"
```
</details>  
