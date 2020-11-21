package me.amar.grantxproject.BotEvents;

import me.amar.grantxproject.Files.DataYml;
import me.amar.grantxproject.GrantXProject;
import me.amar.grantxproject.MinecraftEvents.GrantEvent;
import me.amar.grantxproject.MinecraftEvents.GrantExpire;
import me.amar.grantxproject.MinecraftEvents.GrantRevoke;
import me.amar.grantxproject.Utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Commands extends ListenerAdapter {
    private static GrantXProject plugin = GrantXProject.getPlugin(GrantXProject.class);
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split("\\s+");

            if (args.length <= 4) {
                if (args[0].equalsIgnoreCase(GrantXProject.getPrefix())) {
                    switch (args[1].toLowerCase()){
                        case "setlogs":
                            if (e.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                                try {
                                    GrantXProject.getJda().getTextChannelById(args[3].replace("#", "").replace("<", "").replace(">", "").replace("!", ""));
                                } catch (Exception e1) {
                                    System.out.println(e1);
                                    e.getMessage().getChannel().sendMessage("Command usage: xsetlogs `grant|revoke|expire|all` #channel").queue();
                                }
                                GrantExpire grantExpire = new GrantExpire();
                                GrantRevoke revokeEvent = new GrantRevoke();
                                GrantEvent grantEvent = new GrantEvent();
                                switch (args[2].toLowerCase()) {
                                    case "grant":
                                        plugin.getConfig().set("grant.channel-id", Utils.getChannelID(e.getMessage()));
                                        grantEvent.setEnabled(true);
                                        e.getMessage().getChannel().sendMessage("Grant logs were set successfully!").queue();
                                        plugin.saveConfig();
                                        break;
                                    case "revoke":
                                        plugin.getConfig().set("revoke.channel-id", Utils.getChannelID(e.getMessage()));
                                        revokeEvent.setEnabled(true);
                                        e.getMessage().getChannel().sendMessage("Revoke logs were set successfully!").queue();
                                        plugin.saveConfig();
                                        break;
                                    case "expire":
                                        plugin.getConfig().set("expire.channel-id", Utils.getChannelID(e.getMessage()));
                                        grantExpire.setEnabled(true);
                                        e.getMessage().getChannel().sendMessage("Expire logs were set successfully!").queue();
                                        plugin.saveConfig();
                                        break;
                                    case "all":
                                        plugin.getConfig().set("grant.channel-id", Utils.getChannelID(e.getMessage()));
                                        grantEvent.setEnabled(true);
                                        plugin.getConfig().set("revoke.channel-id", Utils.getChannelID(e.getMessage()));
                                        revokeEvent.setEnabled(true);
                                        plugin.getConfig().set("expire.channel-id", Utils.getChannelID(e.getMessage()));
                                        grantExpire.setEnabled(true);
                                        e.getMessage().getChannel().sendMessage("All logs were set successfully!").queue();
                                        break;
                                    default:
                                        e.getMessage().getChannel().sendMessage("Command usage: xsetlogs `grant|revoke|expire|all` #channel").queue();
                                        break;
                                }

                            }
                            else {
                                e.getMessage().getChannel().sendMessage("Only Administrators can use this command.").queue();
                            }
                            break;
                        case "help":
                            if (args[1].equalsIgnoreCase("help")) {
                                try {
                                    EmbedBuilder embed = new EmbedBuilder();
                                    embed.setColor(Color.green);
                                    embed.addField("**xSetLogs (Command)**", "=> Administrators can use this command to setup logs in a channel. ", false);
                                    embed.addField("**Usage:**", "```x SetLogs grant|revoke|expire|all #channel```", false);
                                    embed.addField("-----------------------------------------","", true);
                                    embed.addField("**DisableLogs (Command)**", "=> Administrators can use this command to disable specefic (or all) logs.", false);
                                    embed.addField("**Usage:**", "```x DisableLogs grant|revoke|expire|all```", false);
                                    embed.addField("-----------------------------------------","", true);
                                    embed.addField("**Need support?**", "Join https://discord.gg/5XDzE2Q and ask <@426327139533717504> or <@245611604626898945> for help. Do __not__ make a ticket for support on this bot or add-on, as Demeng did not make it.", false);
                                    e.getMessage().getChannel().sendMessage(embed.build()).queue();
                                } catch (Exception e1) {
                                    System.out.println(e1);
                                }
                            }
                            break;
                        case "disable":
                                if(e.getMember().hasPermission(Permission.ADMINISTRATOR)){
                                    GrantExpire grantExpire = new GrantExpire();
                                    GrantEvent grantEvent = new GrantEvent();
                                    GrantRevoke grantRevoke = new GrantRevoke();
                                        switch (args[2].toLowerCase()){
                                            case "grant":
                                                grantEvent.setEnabled(false);
                                                e.getMessage().getChannel().sendMessage("Disabled grant logs successfully.").queue();
                                                break;
                                            case "revoke":
                                                grantRevoke.setEnabled(false);
                                                e.getMessage().getChannel().sendMessage("Disabled revoke logs successfully.").queue();
                                                break;
                                            case "expire":
                                                grantExpire.setEnabled(false);
                                                e.getMessage().getChannel().sendMessage("Disabled expire logs successfully.").queue();
                                                break;
                                            case "all":
                                                grantEvent.setEnabled(false);
                                                grantRevoke.setEnabled(false);
                                                grantExpire.setEnabled(false);
                                                e.getMessage().getChannel().sendMessage("Disabled all logs successfully.").queue();
                                                break;
                                            default:
                                                e.getMessage().getChannel().sendMessage("If you want help, type `" + GrantXProject.getPrefix() + " help`!").queue();
                                            break;
                                        }
                                }
                                else {
                                    e.getMessage().getChannel().sendMessage("Only Administrators can use this command.").queue();
                                }
                            break;
                        default:
                            e.getMessage().getChannel().sendMessage("If you want help, type `" + GrantXProject.getPrefix() + " help`!").queue();
                            break;
                    }

                }
            }

        }


}
