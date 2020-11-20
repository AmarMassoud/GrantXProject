package me.amar.grantxproject.BotEvents;

import me.amar.grantxproject.Files.DataYml;
import me.amar.grantxproject.GrantXProject;
import me.amar.grantxproject.MinecraftEvents.GrantEvent;
import me.amar.grantxproject.MinecraftEvents.GrantExpire;
import me.amar.grantxproject.MinecraftEvents.GrantRevoke;
import me.amar.grantxproject.Utils.Utils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SetLogsChannel extends ListenerAdapter {
    private static GrantXProject plugin = GrantXProject.getPlugin(GrantXProject.class);
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split("\\s+");

            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("xsetlogs")) {
                    if (e.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                        try {
                            GrantXProject.getJda().getTextChannelById(args[2].replace("#", "").replace("<", "").replace(">", "").replace("!", ""));
                        } catch (Exception e1) {
                            System.out.println(e1);
                            e.getMessage().getChannel().sendMessage("Command usage: xsetlogs `grant|revoke|expire|all` #channel").queue();
                        }
                        GrantExpire grantExpire = new GrantExpire();
                        GrantRevoke revokeEvent = new GrantRevoke();
                        GrantEvent grantEvent = new GrantEvent();
                        switch (args[1].toLowerCase()) {
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
                }
            }

        }


}
