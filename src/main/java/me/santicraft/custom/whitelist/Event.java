package me.santicraft.custom.whitelist;

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
    e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, this.plugin.getStorage().getNoWhitelistMsg());
  }
}
