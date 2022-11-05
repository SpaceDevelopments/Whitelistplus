package me.santicraft.custom.whitelist.api;

import me.santicraft.custom.whitelist.CustomWhitelist;
import me.santicraft.custom.whitelist.utils.Utility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class WhitelistAPI {

    private static CustomWhitelist plugin;

    public static CustomWhitelist getCustomWhitelist() {
        return plugin;
    }

    public static void setInstance(CustomWhitelist plugin) {
        if (WhitelistAPI.plugin != null)
            throw new UnsupportedOperationException("Cannot initialize plugin instance after it was initialized already!");
        WhitelistAPI.plugin = plugin;
    }

    private static ArrayList<String> whitelists = new ArrayList<>();


    @Contract(pure = true)
    public static String getVersion(){
        return plugin.getDescription().getVersion();
    }

    @Contract(pure = true)
    public static double getAPIVersion(){
        return 1.0;
    }

    public static void setWhitelist(boolean status) {
        plugin.getStorage().setWhitelist(status);
        	if(WhitelistAPI.isEnabled()) {
                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    if (!plugin.getStorage().isWhitelisted(target.getName()) || !target.hasPermission("whitelistplus.bypass") || !target.isOp()) {
                        target.kickPlayer(Utility.TransColor(plugin.getConfig().getString("no_whitelisted")));
                    	}
                	}
        	}
    }

    public static void addPlayer(String name) {
        plugin.getStorage().addWhitelist(name);
    }

    public static void removePlayer(String name) {plugin.getStorage().removeWhitelist(name);}

    @Nullable
    public static ArrayList<String> getPlayers() {
        whitelists = new ArrayList<>(plugin.getConfig().getStringList("whitelisted"));
        plugin.getStorage().getWhiteLists();
        return whitelists;
    }

    public static boolean isWhitelisted(String name) {
        if (plugin.getStorage().isWhitelisted(name)) {
            return true;
        }else{
            return false;
        }
    }

    public static boolean isEnabled() {
        if (plugin.getConfig().getString("whitelist").equals("true")) {
            return true;
        }else{
            return false;
        }
    }

    public static void setWhitelistMessage(String message){
        plugin.getConfig().set("no_whitelisted", message);
    }

}
