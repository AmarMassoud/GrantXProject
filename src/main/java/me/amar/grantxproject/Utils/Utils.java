package me.amar.grantxproject.Utils;

import net.dv8tion.jda.api.entities.Message;
import org.bukkit.ChatColor;

public class Utils {
    static String prefix = "[GrantX Bot] ";

    public static String colorize(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void sendError(int errorID, String error, String errorDescription) {
        System.out.println(prefix + " ");
        System.out.println(prefix + "!-----------------------------------------------!");
        System.out.println(prefix + "An internal error has occured in GrantX Bot");
        System.out.println(prefix + "Error ID: " + errorID);
        System.out.println(prefix + "Error: " + error);
        System.out.println(prefix + "Error description: " + errorDescription);
        System.out.println(prefix + "If you think this is a mistake, please contact");
        System.out.println(prefix + "the Support team at https://discord.gg/JNNANeQsAJ");
        System.out.println(prefix + "!-----------------------------------------------!");
        System.out.println(prefix + " ");
    }


    public static String getChannelID(Message message) {
        if (message.getMentionedChannels().isEmpty()) {
            return null;
        } else {
            return message.getMentionedChannels().get(0).getId();
        }
    }


    public static String humanize(long seconds) {
        StringBuilder time = new StringBuilder();
        long secondss = seconds;
        boolean loop = true;
        long[] times = {0/*week*/, 0 /*day*/, 0 /*hour*/, 0 /*minute*/, 0/*s*/};
        while (loop) {
            if (secondss > (3600 * 24 * 7) - 1) {
                secondss -= (3600 * 24 * 7);
                times[0] += 1;
            } else if (secondss > (3600 * 24) - 1) {
                secondss -= (3600 * 24);
                times[1] += 1;
            } else if (secondss > 3599) {
                secondss -= 3600;
                times[2] += 1;
            } else if (secondss > 59) {
                secondss -= 60;
                times[3] += 1;
            } else {
                times[4] += secondss;
                loop = false;
            }
        }
        loop = true; // this variable is being recycled cuz why not, I dont want more ram usage ok
        final String[] sts = {"w", "d", "h", "m", "s"};
        for (int t = 0; t < 5; t++) {
            if (times[t] != 0) {
                if (loop) {
                    loop = false;
                    time.append(times[t]).append(" ").append(sts[t]);
                } else {
                    time.append(", ").append(times[t]).append(" ").append(sts[t]);
                }
            }
        }
        return time.toString();
    }
}
