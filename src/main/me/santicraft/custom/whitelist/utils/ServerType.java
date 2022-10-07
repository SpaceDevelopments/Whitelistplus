package me.santicraft.custom.whitelist.utils;

public class ServerType {
	
// The code in are class is extracted and modified of the plugin ProtocolLib
	
	  public static boolean classExists(String className) {
		    try {
		      Class.forName(className);
		      return true;
		    } catch (ClassNotFoundException ex) {
		      return false;
		    } 
		  }
	  
	 private static final boolean mohist = classExists("com.mohistmc.MohistConfigUtil");
	  
	  public static boolean isUsingMohist() {
	    return mohist;
	  }  	  
}
