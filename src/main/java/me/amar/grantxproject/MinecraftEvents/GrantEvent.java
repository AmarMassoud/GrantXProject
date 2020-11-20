package me.amar.grantxproject.MinecraftEvents;


import dev.demeng.grantx.api.Grant;
import dev.demeng.grantx.api.event.GrantActivateEvent;
import me.amar.grantxproject.GrantXProject;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GrantEvent implements Listener {
    private static GrantXProject plugin = GrantXProject.getPlugin(GrantXProject.class);

    @EventHandler
    public void GrantEvent(GrantActivateEvent e) {
        Grant grant = e.getGrant();
        OfflinePlayer player = grant.getIssuer();
        OfflinePlayer target = grant.getTarget();
        if(!plugin.getConfig().getString("grant.channel-id").equalsIgnoreCase("0")) {
            GrantXProject.getJda().getTextChannelById(plugin.getConfig().getString("grant.channel-id")).sendMessage("**" + player.getName() + "** has granted **" + target.getName() + "** the **" + e.getGrant().getRank() + "** rank.").queue();
        }
    }
}
