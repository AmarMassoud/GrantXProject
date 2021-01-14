package me.amar.grantxproject.MinecraftEvents;


import dev.demeng.grantx.api.Grant;
import dev.demeng.grantx.api.event.GrantActivateEvent;
import me.amar.grantxproject.GrantXProject;
import me.amar.grantxproject.Utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;

public class GrantEvent implements Listener {
    private static GrantXProject plugin = GrantXProject.getPlugin(GrantXProject.class);




    @EventHandler
    public void GrantEvent(GrantActivateEvent e) {
        if(plugin.getConfig().getBoolean("grant.is-enabled")) {
            Grant grant = e.getGrant();
            OfflinePlayer player = grant.getIssuer();
            OfflinePlayer target = grant.getTarget();
            if (!plugin.getConfig().getString("grant.channel-id").equalsIgnoreCase("0")) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.green);
                embed.addField("**Grant**", "**" + target.getName() + "** was given the rank **" + grant.getRank() + "** by **"+ player.getName() + "**", false);
                embed.addField("**Reason**", grant.getReason().replace("&9&l", ""), true);
                if(e.getGrant().getDuration() == -1){
                    embed.addField("**Duration**","Permanent", true);

                } else {
                    embed.addField("**Duration**", Utils.humanize(grant.getDuration()), true);

                }
                GrantXProject.getJda().getTextChannelById(plugin.getConfig().getString("grant.channel-id")).sendMessage(embed.build()).queue();
            } else{
            System.out.println("No channel ID");
           }
        }
    }
    public void setEnabled(boolean enabled) {
        plugin.getConfig().set("grant.is-enabled", enabled);
        plugin.saveConfig();
        System.out.println(plugin.getConfig().getBoolean("grant.is-enabled"));
    }
}
