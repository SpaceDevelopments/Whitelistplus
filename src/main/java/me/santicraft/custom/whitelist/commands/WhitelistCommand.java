package me.santicraft.custom.whitelist.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import me.santicraft.custom.whitelist.CustomWhitelist;
import me.santicraft.custom.whitelist.Utility;

public class WhitelistCommand implements CommandExecutor {

	private CustomWhitelist plugin;

    public WhitelistCommand(CustomWhitelist plugin) {
        this.plugin = plugin;
    }
    String prefix;

	public boolean onCommand(CommandSender jugador, Command comando, String label, String[] args) {

//  	this.prefix = "&fWhitelist&b+ &7";
//		Player sender;
        if (jugador.hasPermission("whitelistplus.*")) {
		
        this.prefix = plugin.getConfig().getString("Prefix")+ "&r ";

        if (args.length > 0) {
        	
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.getStorage().reload();
                Utility.sendMsg(jugador, "&fWhitelist&b+ &7Config reloaded.");
                return true;
            }

            if (args[0].equalsIgnoreCase("on")) {
                plugin.getStorage().setWhitelist(Boolean.valueOf(true));
                Utility.sendMsg(jugador, String.valueOf(String.valueOf(this.prefix)) + plugin.getConfig().getString("Messages.whitelist-on"));           
                return true;
            }

            if (args[0].equalsIgnoreCase("off")) {
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
                Utility.sendMsg(jugador, String.valueOf(String.valueOf(this.prefix)) + plugin.getConfig().getString("Messages.Whitelist-List").replaceAll("%players%", names));
                return true;
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
        	Utility.sendMsg(jugador, plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("Messages.no-permission"));
        }
		return false;
	}
	

}