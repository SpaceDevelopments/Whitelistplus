package me.santicraft.custom.whitelist;

import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;

import me.santicraft.custom.whitelist.CustomWhitelist;
// import me.santicraft.custom.whitelist.utils.HexSupport;

public class Storage {
  private CustomWhitelist plugin;

  private ArrayList<String> whitelists = new ArrayList<>();
  
  private boolean WhitelistEnabled = false;
  
  private String nowhitelistmsg = "";

  public Storage(CustomWhitelist plugin) {
    this.plugin = plugin;
    reload();
  }

  public void reload() {
    this.plugin.reloadConfig();
    FileConfiguration config = this.plugin.getConfig();
    this.whitelists = new ArrayList<>(config.getStringList("whitelisted"));
    this.WhitelistEnabled = config.getBoolean("whitelist");
    this.nowhitelistmsg = Utility.TransColor(config.getString("no_whitelisted"));
  }

  public void saveWhitelists() {
    FileConfiguration config = this.plugin.getConfig();
    config.set("whitelisted", this.whitelists);
    config.set("whitelist", Boolean.valueOf(isWhitelisting()));
    this.plugin.saveConfig();
  }

  public boolean isWhitelisted(String name) {
    return this.whitelists.contains(name.toLowerCase());
  }

  public void addWhitelist(String name) {
    if (this.whitelists.contains(name.toLowerCase()))
      return; 
    this.whitelists.add(name.toLowerCase());
    saveWhitelists();
  }
  
  public void removeWhitelist(String name) {
    if (!this.whitelists.contains(name.toLowerCase()))
      return; 
    this.whitelists.remove(name.toLowerCase());
    saveWhitelists();
  }
  
  public void setWhitelist(Boolean onoff) {
    this.WhitelistEnabled = onoff.booleanValue();
    saveWhitelists();
  }
  
  public ArrayList<String> getWhiteLists() {
    return this.whitelists;
  }
  
  public boolean isWhitelisting() {
    return this.WhitelistEnabled;
  }
  
  public String getNoWhitelistMsg() {
//    return HexSupport.getColoredMessage(this.nowhitelistmsg);
	return this.nowhitelistmsg;
  }
 
  }
  
