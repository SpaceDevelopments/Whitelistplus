package me.santicraft.custom.whitelist.commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandTab implements TabCompleter {
  private List<String> arguments = new ArrayList<>();
  
  public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
    if (this.arguments.isEmpty()) {
      this.arguments.add("add");
      this.arguments.add("remove");
      this.arguments.add("list");
      this.arguments.add("on");
      this.arguments.add("off");
      this.arguments.add("reload");
    } 
    List<String> result = new ArrayList<>();
    if (args.length == 1) {
      for (String a : this.arguments) {
        if (a.toLowerCase().startsWith(args[0].toLowerCase()))
          result.add(a); 
      } 
      return result;
    } 
    return null;
  }
}
