package me.amar.grantxproject.BotEvents;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class HelpCommand extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equalsIgnoreCase("xhelp")) {
            try {
                EmbedBuilder embed = new EmbedBuilder();

                embed.addField("**xSetLogs (Command)**", "=> Administrators can use this command to setup logs in a channel. ", false);
                embed.addField("**Usage:**", "`xSetLogs grant|revoke|expire|all #channel`", false);
                embed.addField("-----------------------------","", true);
                embed.addField("-----------------------------","", true);
                embed.addBlankField(true);
                embed.addField("**Need support?**", "Join https://discord.gg/5XDzE2Q and ask <@426327139533717504> or <@245611604626898945> for help. Do __not__ make a ticket for support on this bot or add-on, as Demeng did not make it.", false);
                event.getMessage().getChannel().sendMessage(embed.build()).queue();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
