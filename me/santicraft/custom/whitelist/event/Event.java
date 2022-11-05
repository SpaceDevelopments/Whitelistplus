package me.santicraft.custom.whitelist.event;

import me.santicraft.custom.whitelist.CustomWhitelist;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class Event implements Listener {
  private CustomWhitelist plugin;
  
  public Event(CustomWhitelist plugin) {
    this.plugin = plugin;
  }
  
  @EventHandler
  public void onConnect(PlayerLoginEvent e) {
    Player p = e.getPlayer();    
    if (p == null)
      return; 
    
   if (!this.plugin.getStorage().isWhitelisting())
      return; 
    if (this.plugin.getStorage().isWhitelisted(p.getName()))
      return; 
    if(plugin.getConfig().getString("Settings.OP-Bypass").equals("true")) {
    	if(p.isOp()) {
    		return;
    	}
    }
    if(plugin.getConfig().getString("Settings.Bypass").equals("true")) {
    	if(p.hasPermission("whitelistplus.bypass")) {
    		return;
    	}
    }
    
    e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, this.plugin.getStorage().getNoWhitelistMsg());
  }
  
}
