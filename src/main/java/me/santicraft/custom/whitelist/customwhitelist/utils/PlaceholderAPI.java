package me.santicraft.custom.whitelist.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.santicraft.custom.whitelist.CustomWhitelist;

public class PlaceholderAPI extends PlaceholderExpansion{

    private CustomWhitelist plugin;
 
    public PlaceholderAPI(CustomWhitelist plugin) {
    	this.plugin = plugin;
    }
 
    @Override
    public boolean persist(){
        return true;
    }
    @Override
    public boolean canRegister(){
        return true;
    }
 
    @Override
    public String getAuthor(){
        return "SantiCraft234";
    }
 
    @Override
    public String getIdentifier(){
        return "whitelistplus";
    }
 

    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }
 
    // %whitelistplus_status%
 
    @Override
    public String onPlaceholderRequest(Player player, String identifier){
 
        if(player == null){
            return "";
        }
 
        if(identifier.equals("status")){
        	if(plugin.getConfig().getString("whitelist").equals("true")){
        		return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Placeholder_messages.enabled"));
        	}else {
                return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Placeholder_messages.disabled"));
        	}
        }

        // We return null if an invalid placeholder (f.e. %someplugin_placeholder3%) 
        // was provided
        return null;
    }
}
