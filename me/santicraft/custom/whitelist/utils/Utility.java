package me.santicraft.custom.whitelist.utils;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
public final class Utility {
  @SuppressWarnings("unused")
private static final int CENTER_PX = 154;
  
  public static final Class<?> getClass(String classname) {

    String servversion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    try {
      return Class.forName("net.minecraft.server." + servversion + "." + classname);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    } 
  }

    public static String FOOTER_HELP_MESSAGE = String.valueOf(String.valueOf(ChatColor.WHITE.toString())) + ChatColor.STRIKETHROUGH.toString() + "+---------------------------------+";
    public static String HEADER_HELP_MESSAGE = String.valueOf(String.valueOf(ChatColor.translateAlternateColorCodes('&', "&m&f+---------" + "&fWhitelist&b+ &fHelp" + "&m&f---------+")));
    public static String LIST_MESSAGE = String.valueOf(String.valueOf(ChatColor.translateAlternateColorCodes('&', "&m&f+---------" + "&fWhitelist&b+ &m&f----" + "&m&f---------+")));
    
  public static final void sendMsg(CommandSender b, String msg) {
    b.sendMessage(TransColor(msg));
  }
  
  public static final void sendConsole(String msg) {
    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
  }
  
  public static final String TransColor(String c) {
    return ChatColor.translateAlternateColorCodes('&', c);
  }
  
  public static final String[] TransColor(String[] c) {
    String strf = "";
    int length = c.length;
    int cr = 0;
    byte b;
    int i;
    String[] arrayOfString;
    for (i = (arrayOfString = c).length, b = 0; b < i; ) {
      String str = arrayOfString[b];
      cr++;
      if (cr != length) {
        strf = String.valueOf(String.valueOf(strf)) + str + ";";
      } else {
        strf = String.valueOf(String.valueOf(strf)) + str;
      } 
      b = (byte)(b + 1);
    } 
    strf = TransColor(strf);
    return strf.split(";");
  }
  
  public static final List<String> TransColor(List<String> strlist) {
    for (int x = 0; x < strlist.size(); x++)
      strlist.set(x, TransColor(strlist.get(x))); 
    return strlist;
  }

}
