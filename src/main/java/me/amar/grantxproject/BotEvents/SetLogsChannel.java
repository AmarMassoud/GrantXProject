package me.amar.grantxproject.BotEvents;

import me.amar.grantxproject.Files.DataYml;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SetLogsChannel extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split("\\s+");
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("-setLlogs")) {
                if(isTextChannelValid(args[1], e.getGuild())) {
                    DataYml.getDataYml().set("logs-channel-id", Integer.parseInt(args[1]));
                }
            }
        }
    }



    public static boolean isTextChannelValid(String textChannelID, Guild guild) {
        for (TextChannel textchannels : guild.getTextChannels()) {
            if (textChannelID == textchannels.getId()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


}
