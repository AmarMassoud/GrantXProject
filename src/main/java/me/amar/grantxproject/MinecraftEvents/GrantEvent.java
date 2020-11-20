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



    boolean isEnabled = plugin.getConfig().getBoolean("grant.is-enabled");
    @EventHandler
    public void GrantEvent(GrantActivateEvent e) {
        if(isEnabled) {
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
                    embed.addField("**Duration**", grant.getDuration() + "", true);

                }
                GrantXProject.getJda().getTextChannelById(plugin.getConfig().getString("grant.channel-id")).sendMessage(embed.build()).queue();


                //ok you muted then
                //
            }
           /*


            */
        }
    }
    public void setEnabled(boolean enabled) {
        plugin.getConfig().set("grant.is-enabled", enabled);
        isEnabled = enabled;
    }
}
