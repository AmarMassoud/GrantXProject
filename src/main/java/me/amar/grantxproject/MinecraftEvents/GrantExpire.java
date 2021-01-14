package me.amar.grantxproject.MinecraftEvents;

import dev.demeng.grantx.api.Grant;
import dev.demeng.grantx.api.event.GrantExpireEvent;
import me.amar.grantxproject.GrantXProject;
import me.amar.grantxproject.Utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;

public class GrantExpire implements Listener {
    private static GrantXProject plugin = GrantXProject.getPlugin(GrantXProject.class);

    @EventHandler
    public void onGrantExpire(GrantExpireEvent e) {
        if(plugin.getConfig().getBoolean("expire.is-enabled")) {
            final Grant grant = e.getGrant();
            OfflinePlayer target = grant.getTarget();
            if (!plugin.getConfig().getString("expire.channel-id").equalsIgnoreCase("0")) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(0xda600a);
                embed.addField("**Expire**", "**" + target.getName() + "**'s rank expired.", false);
                embed.addField("**Grant reason**", grant.getReason().replace("&9&l", ""), true);
                embed.addField("**Rank duration**", Utils.humanize(grant.getDuration()), true);
                GrantXProject.getJda().getTextChannelById(plugin.getConfig().getString("expire.channel-id")).sendMessage(embed.build()).queue();
            } else {
                System.out.println("Unable to do that");

            }
        }
    }
    public void setEnabled(boolean enabled) {
        plugin.getConfig().set("expire.is-enabled", enabled);
        plugin.saveConfig();
    }
}
