package me.amar.grantxproject.Utils;

import org.bukkit.ChatColor;

public class Utils {
    static String  prefix = "[GrantX Bot] ";
    public static String colorize(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public static void sendError(int errorID, String error, String errorDescription) {
        System.out.println(prefix + " ");
        System.out.println(prefix + "!-----------------------------------------------------!");
        System.out.println(prefix + "An internal error has occured in GrantX Bot");
        System.out.println(prefix + "Error ID: " + errorID);
        System.out.println(prefix + "Error: " + error);
        System.out.println(prefix + "Error description: " + errorDescription);
        System.out.println(prefix + "If you think this is an error please contact the Support team at (link)");
        System.out.println(prefix + "!-----------------------------------------------------!");
        System.out.println(prefix + " ");
    }
}
