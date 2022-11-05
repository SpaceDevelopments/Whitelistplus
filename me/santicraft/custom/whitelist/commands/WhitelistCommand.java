package me.santicraft.custom.whitelist.commands;

import me.santicraft.custom.whitelist.CustomWhitelist;
import me.santicraft.custom.whitelist.utils.Utility;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.command.CommandExecutor;

public class WhitelistCommand implements CommandExecutor {

	private CustomWhitelist plugin;

   public WhitelistCommand(CustomWhitelist plugin) {
        this.plugin = plugin;
    }
    String prefix;

    public boolean onCommand(CommandSender jugador, Command comando, String label, String[] args) {

//  	this.prefix = "&fWhitelist&b+ &7";
        if (jugador.hasPermission("whitelistplus.commands.use")) {
		
        this.prefix = plugin.getConfig().getString("Settings.Prefix")+ "&r ";

        if (args.length > 0) {
        	
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.getStorage().reload();
                Utility.sendMsg(jugador, "&fWhitelist&b+ &7Config reloaded.");
                return true;
            }

            if (args[0].equalsIgnoreCase("on")) {
            	if(plugin.getConfig().getString("Settings.Broadcast-Message").equals("true")) {
            	Bukkit.broadcastMessage(Utility.TransColor(plugin.getConfig().getString("Broadcast-Messages.whitelist-on")));
            	}
            	if(plugin.getConfig().getString("Settings.Kick-All").equals("true")) {
                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    if (!plugin.getStorage().isWhitelisted(target.getName()) || !target.hasPermission("whitelistplus.bypass") || !target.isOp()) {
                        target.kickPlayer(Utility.TransColor(plugin.getConfig().getString("no_whitelisted")));
                    	}
                	} 
                } 
            	
        		plugin.getStorage().setWhitelist(Boolean.valueOf(true));
                Utility.sendMsg(jugador, String.valueOf(String.valueOf(this.prefix)) + plugin.getConfig().getString("Messages.whitelist-on"));           
                return true;
            }

            if (args[0].equalsIgnoreCase("off")) {
            	if(plugin.getConfig().getString("Settings.Broadcast-Message").equals("true")) {
            	Bukkit.broadcastMessage(Utility.TransColor(plugin.getConfig().getString("Broadcast-Messages.whitelist-off")));
            	}
                plugin.getStorage().setWhitelist(Boolean.valueOf(false));
                Utility.sendMsg(jugador, String.valueOf(String.valueOf(this.prefix)) + plugin.getConfig().getString("Messages.whitelist-off"));
                return true;
            }
            if (args[0].equalsIgnoreCase("add")) {
                if (args.length < 2) {
                    Utility.sendMsg(jugador, String.valueOf(String.valueOf(this.prefix)) + plugin.getConfig().getString("Messages.requiere-name"));
                }

                if (args.length == 1) {
                    return false;
                }

                String name = args[1];
                if (args.length > 2)
                    name = String.valueOf(name) + " " + args[2];
                if (args.length > 3)
                    name = String.valueOf(name) + " " + args[2] + " " + args[3];
                plugin.getStorage().addWhitelist(name);
                Utility.sendMsg(jugador, String.valueOf(String.valueOf(this.prefix)) + plugin.getConfig().getString("Messages.Whitelist-Add").replaceAll("%player%", name));
                return true;
            }       
            
            if (args[0].equalsIgnoreCase("remove")) {
                if (args.length < 2) {
                    Utility.sendMsg(jugador, String.valueOf(String.valueOf(this.prefix)) + plugin.getConfig().getString("Messages.requiere-name"));
                }

                if (args.length == 1) {
                    return false;
                }

                String name = args[1];
                if (args.length > 2)
                    name = String.valueOf(name) + " " + args[2];
                if (args.length > 3)
                    name = String.valueOf(name) + " " + args[2] + " " + args[3];
                plugin.getStorage().removeWhitelist(name);
                Utility.sendMsg(jugador, String.valueOf(String.valueOf(this.prefix)) + plugin.getConfig().getString("Messages.Whitelist-Remove").replaceAll("%player%", name));
                return true;

            }
            
            if (args[0].equalsIgnoreCase("list")) {
                String names = "";
                for (String str1 : plugin.getStorage().getWhiteLists())
                    names = String.valueOf(String.valueOf(names)) + str1 + "&e&l, &7";
                Utility.sendMsg(jugador, Utility.LIST_MESSAGE);
                Utility.sendMsg(jugador, plugin.getConfig().getString("Messages.Whitelist-List").replaceAll("%players%", names));
                Utility.sendMsg(jugador, Utility.FOOTER_HELP_MESSAGE);
                return true;

            }else{
              Utility.sendMsg(jugador, plugin.nombre + plugin.getConfig().getString("Messages.unknown-command"));
            }
        }else {
            Utility.sendMsg(jugador, Utility.HEADER_HELP_MESSAGE);

            Utility.sendMsg(jugador, "&7  &l/whitelist &a&ladd &f[name]");
            Utility.sendMsg(jugador, "&7  &l/whitelist &4&lremove &f[name]");
            Utility.sendMsg(jugador, "&7  &l/whitelist &f&llist");
            Utility.sendMsg(jugador, "&7  &l/whitelist &a&lon");
            Utility.sendMsg(jugador, "&7  &l/whitelist &4&loff");
            Utility.sendMsg(jugador, "&7  &l/whitelist &f&lreload");
            Utility.sendMsg(jugador, Utility.FOOTER_HELP_MESSAGE);
            return true;
        	}
        }else {
        	Utility.sendMsg(jugador, plugin.nombre + plugin.getConfig().getString("Messages.no-permission"));
        }
		return false;
	}
	

}