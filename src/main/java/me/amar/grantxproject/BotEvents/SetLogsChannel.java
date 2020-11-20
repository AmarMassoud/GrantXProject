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
            if (args[0].equalsIgnoreCase("-setLogs")) {
                if(!e.getMessage().getMentionedChannels().isEmpty()) {
                    DataYml.getDataYml().set("logs-channel-id", e.getMessage().getMentionedChannels().get(0).getId());
                    e.getChannel().sendMessage("Grant logs has been set to <#" + e.getMessage().getMentionedChannels().get(0).getId() + ">").queue();
                }
            }
        } else {
            e.getChannel().sendMessage("Invalid arguments.").queue();
        }

    }
}
