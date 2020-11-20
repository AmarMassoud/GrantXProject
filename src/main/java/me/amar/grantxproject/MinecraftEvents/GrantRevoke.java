package me.amar.grantxproject.MinecraftEvents;

import dev.demeng.grantx.api.Grant;
import dev.demeng.grantx.api.event.GrantExpireEvent;
import dev.demeng.grantx.api.event.GrantRevokeEvent;
import me.amar.grantxproject.GrantXProject;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GrantRevoke implements Listener {
    private static GrantXProject plugin = GrantXProject.getPlugin(GrantXProject.class);
    @EventHandler
    public void onGrantRevoke(GrantRevokeEvent e) {
        Grant grant = e.getGrant();
        OfflinePlayer player = grant.getIssuer();
        OfflinePlayer target = grant.getTarget();
        if(!plugin.getConfig().getString("revoke.channel-id").equalsIgnoreCase("0")) {
            GrantXProject.getJda().getTextChannelById(plugin.getConfig().getString("revoke.channel-id")).sendMessage("**" + player.getName() + "** has granted **" + target.getName() + "** the **" + e.getGrant().getRank() + "** rank.").queue();
        }
    }
}
