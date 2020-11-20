package me.amar.grantxproject.MinecraftEvents;


import dev.demeng.grantx.api.Grant;
import dev.demeng.grantx.api.event.GrantActivateEvent;
import me.amar.grantxproject.Files.DataYml;
import me.amar.grantxproject.GrantXProject;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GrantEvent implements Listener {


    @EventHandler
    public void GrantEvent(GrantActivateEvent e) {
        Grant grant = e.getGrant();
        OfflinePlayer player = e.getGrant().getIssuer();
        OfflinePlayer target = e.getGrant().getTarget();
        if(!DataYml.getDataYml().getString("logs-channel-id").equalsIgnoreCase("0")) {
            GrantXProject.getJda().getTextChannelById(DataYml.getDataYml().getString("logs-channel-id")).sendMessage("*" + player.getName() + "* has granted *" + target.getName() + "* the *" + e.getGrant().getRank() + "* rank.").queue();
        }
    }
}
