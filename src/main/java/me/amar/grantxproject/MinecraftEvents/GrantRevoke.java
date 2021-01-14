package me.amar.grantxproject.MinecraftEvents;

import dev.demeng.grantx.api.Grant;
import dev.demeng.grantx.api.event.GrantExpireEvent;
import dev.demeng.grantx.api.event.GrantRevokeEvent;
import me.amar.grantxproject.GrantXProject;
import me.amar.grantxproject.Utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;

public class GrantRevoke implements Listener {
    private static GrantXProject plugin = GrantXProject.getPlugin(GrantXProject.class);

    @EventHandler
    public void onGrantRevoke(GrantRevokeEvent e) {
        if(plugin.getConfig().getBoolean("revoke.is-enabled")) {
            Grant grant = e.getGrant();
            OfflinePlayer player = grant.getIssuer();
            OfflinePlayer target = grant.getTarget();
            if (!plugin.getConfig().getString("revoke.channel-id").equalsIgnoreCase("0")) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.red);
                embed.addField("**Revoke**", "**" + target.getName() + "**'s rank **" + grant.getRank() + "** was revoked by **"+ player.getName() + "**", false);
                embed.addField("**Grant reason**", grant.getReason().replace("&9&l", ""), true);
                if(e.getGrant().getDuration() == -1){
                    embed.addField("**Rank duration**","Permanent", true);

                } else {
                    embed.addField("**Rank duration**", Utils.humanize(grant.getDuration()), true);

                }
                GrantXProject.getJda().getTextChannelById(plugin.getConfig().getString("revoke.channel-id")).sendMessage(embed.build()).queue();
            } else {
                System.out.println("Unable to do that");
            }
        } else {
            System.out.println("Error, investigate.");
        }
    }
    public void setEnabled(boolean enabled) {
        plugin.getConfig().set("revoke.is-enabled", enabled);
        plugin.saveConfig();
    }
}
