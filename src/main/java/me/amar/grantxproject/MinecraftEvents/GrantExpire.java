package me.amar.grantxproject.MinecraftEvents;

import dev.demeng.grantx.api.Grant;
import dev.demeng.grantx.api.event.GrantExpireEvent;
import me.amar.grantxproject.GrantXProject;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GrantExpire implements Listener {
    private static GrantXProject plugin = GrantXProject.getPlugin(GrantXProject.class);
    boolean isEnabled = plugin.getConfig().getBoolean("expire.is-enabled");
    @EventHandler
    public void onGrantExpire(GrantExpireEvent e) {
        if(isEnabled) {
            Grant grant = e.getGrant();
            OfflinePlayer player = grant.getIssuer();
            OfflinePlayer target = grant.getTarget();

            if (!plugin.getConfig().getString("expire.channel-id").equalsIgnoreCase("0")) {
                GrantXProject.getJda().getTextChannelById(plugin.getConfig().getString("expire.channel-id")).sendMessage("**" + target.getName() + "**'s rank (**" + grant.getRank() + "**) has expired.").queue();
            }
        }
    }
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
