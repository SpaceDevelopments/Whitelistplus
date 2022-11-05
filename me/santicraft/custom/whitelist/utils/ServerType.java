package me.santicraft.custom.whitelist.utils;

public class ServerType {

	  public static boolean classExists(String className) {
		    try {
		      Class.forName(className);
		      return true;
		    } catch (ClassNotFoundException ex) {
		      return false;
		    } 
		  }
	  
	 private static final boolean mohist = classExists("com.mohistmc.MohistConfigUtil");

	 private static final boolean paper = classExists("com.destroystokyo.paper.PaperConfig");

	private static final boolean paperspigot = classExists("org.github.paperspigot.PaperSpigotConfig");
	
	public static boolean isUsingMohist() {
	    return mohist;
	  }

	public static boolean isUsingPaper() {
		return paper;
	}

	public static boolean isUsingPaperSpigot() {
		return paperspigot;
	}

	public static boolean isUsingFloodgate() {
		return paperspigot;
	}

}
