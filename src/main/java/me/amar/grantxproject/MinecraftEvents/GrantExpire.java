package me.amar.grantxproject.MinecraftEvents;

import dev.demeng.grantx.api.Grant;
import dev.demeng.grantx.api.event.GrantExpireEvent;
import me.amar.grantxproject.GrantXProject;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;

public class GrantExpire implements Listener {
    private static GrantXProject plugin = GrantXProject.getPlugin(GrantXProject.class);
    boolean isEnabled = plugin.getConfig().getBoolean("expire.is-enabled");
    @EventHandler
    public void onGrantExpire(GrantExpireEvent e) {
        if(isEnabled) {
            Grant grant = e.getGrant();
            OfflinePlayer target = grant.getTarget();
            if (!plugin.getConfig().getString("revoke.channel-id").equalsIgnoreCase("0")) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(0xda600a);
                embed.addField("**Expire**", "**" + target.getName() + "**'s rank expired.", false);
                embed.addField("**Grant reason**", grant.getReason().replace("&9&l", ""), true);
                embed.addField("**Rank duration**", grant.getDuration() + "", true);
                GrantXProject.getJda().getTextChannelById(plugin.getConfig().getString("grant.channel-id")).sendMessage(embed.build()).queue();
            }
        }
    }
    public void setEnabled(boolean enabled) {
        plugin.getConfig().set("expire.is-enabled", enabled);
        isEnabled = enabled;
    }
}
