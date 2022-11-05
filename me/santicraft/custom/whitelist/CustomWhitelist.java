package me.santicraft.custom.whitelist;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

import me.santicraft.custom.whitelist.event.Event;
import me.santicraft.custom.whitelist.utils.Storage;
import me.santicraft.custom.whitelist.utils.Utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import me.santicraft.custom.whitelist.api.WhitelistAPI;
import me.santicraft.custom.whitelist.commands.CommandTab;
import me.santicraft.custom.whitelist.commands.WhitelistCommand;
import me.santicraft.custom.whitelist.metrics.Metrics;
import me.santicraft.custom.whitelist.utils.PlaceholderAPI;
import me.santicraft.custom.whitelist.utils.ServerType;

public class CustomWhitelist extends JavaPlugin {
	  
	
  PluginDescriptionFile pdffile = getDescription();
  public String version = pdffile.getVersion();
  public String latestversion;
  private static CustomWhitelist instance;
  public String warning = "&7[&eWarning&7] &r";
  public String error = "&7[&4Error&7] &r";
  
  public String getVersion() {
	  return this.version;
  }
  
  public class UpdateChecker {
	   
	    private final JavaPlugin plugin;
	    private final int resourceId;

	    public UpdateChecker(JavaPlugin plugin, int resourceId) {
	        this.plugin = plugin;
	        this.resourceId = resourceId;
	    }

	    public void getVersion(final Consumer<String> consumer) {
	        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
	            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
	                if (scanner.hasNext()) {
	                    consumer.accept(scanner.next());
	                }
	            } catch (IOException exception) {
	                Utility.sendConsole(nombre + error + "Unable to check for updates: " + exception.getMessage());
	            }
	        });
	    }
	}  
  
  public String getLatestversion(){
	  return this.latestversion;
  }
  
  private Storage storage;
  // public String nombre = ChatColor.WHITE+"["+ChatColor.WHITE+"Whitelist"+ChatColor.AQUA+"Plus"+ChatColor.WHITE+"]";
  public String nombre = getConfig().getString("Settings.Prefix")+" &r";

  @Override
  public void onLoad() {
	    File f = new File(getDataFolder(), "config.yml");
	    if (!f.exists()) {
	      getConfig().options().copyDefaults(true);
	      saveConfig();
	    }
  
  }
  
  @Override
  public void onEnable() {
      if (Bukkit.getVersion().contains("git-Bukkit")) {
          Utility.sendConsole(nombre + warning + "CraftBukkit Server detect, please use other fork how SpigotMC or PaperMC");
          Utility.sendConsole(nombre + warning + "In futures version CraftBukkit not working more for this plugin.");
      }
      Utility.sendConsole(ChatColor.GRAY + "------------------------------------------");
      Utility.sendConsole(nombre + ChatColor.GREEN + "Enabled");
      Utility.sendConsole(nombre + ChatColor.GRAY + "Thanks for download my plugin" + ChatColor.WHITE + " ~ " + ChatColor.YELLOW + "SantiCraft234");
      Utility.sendConsole(ChatColor.GRAY + " - Author: " + pdffile.getAuthors().toString());
//      Utility.sendConsole(System.lineSeparator());
      Utility.sendConsole(ChatColor.GRAY + " - Version: " + getDescription().getVersion());
      new UpdateChecker(this, 103636).getVersion(version -> {
          if (this.getDescription().getVersion().equals(version)) {
              Utility.sendConsole(nombre + ChatColor.GRAY + " You are using the latest version of the plugin");
          } else {
              Utility.sendConsole(nombre + ChatColor.GRAY + " There is a new version of the plugin.");
              Utility.sendConsole(nombre + ChatColor.GRAY + " Download it at: https://www.spigotmc.org/resources/whitelistplus.103636/");
          }

      });
      Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "------------------------------------------");

      if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
          Utility.sendConsole(nombre + "&2Placeholder detect, Enabling variables");
          new PlaceholderAPI(this).register();
      } else {
          Utility.sendConsole(nombre + "&4PlaceholderAPI not detect, Shutdown variables");
      }

      instance = this;
      WhitelistAPI.setInstance(this);
      getServer().getPluginManager().registerEvents(new Event(this), (Plugin) this);
      this.storage = new Storage(this);
      int pluginId = 15900;
      @SuppressWarnings("unused")
	Metrics metrics = new Metrics(this, pluginId);
    CommandRegister();
    getJavaVersion();
    ServerType();
  }    
    
  public static CustomWhitelist getInstance() {
    return instance;
  }
  
  public void onDisable() {
    this.saveConfig();
      Utility.sendConsole(ChatColor.GRAY+"------------------------------------------");
      Utility.sendConsole(nombre+ChatColor.DARK_RED+"Disabled");
      Utility.sendConsole(ChatColor.GRAY + " - Author: "+ pdffile.getAuthors().toString());
      Utility.sendConsole(ChatColor.GRAY + " - Version: " + getDescription().getVersion());
      Utility.sendConsole(ChatColor.GRAY+"------------------------------------------");
  }
  
  public Storage getStorage() {
    return this.storage;
  }
  
  public void CommandRegister() {
	  this.getCommand("whitelist").setExecutor(new WhitelistCommand(this));
	  this.getCommand("whitelist").setTabCompleter((TabCompleter)new CommandTab());
  }
  
  public void getJavaVersion() {
	    int version = -1;
	    try {
	      String[] ver = System.getProperty("java.version").split("\\.");
	      version = Integer.parseInt(ver[1]);
	    } catch (Exception exception) {}
	    if (version == 7) {
	      Utility.sendConsole(nombre+error+"&fJava 7 detected.");
	      Utility.sendConsole(nombre+error+"&fPlease update a Java 8 or new");
	      Bukkit.getPluginManager().disablePlugin((Plugin)this);
	      
	    }
  }
  
  
  public void ServerType() {
	    if(ServerType.isUsingMohist()) {
		      Utility.sendConsole(nombre+warning+"&fYour server are using Mohist, this plugin no are tested in Mohist and some contains bugs");	    }
  }
  
  }
