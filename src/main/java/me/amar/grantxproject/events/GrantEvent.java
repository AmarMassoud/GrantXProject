package me.amar.grantxproject.events;


import dev.demeng.grantx.api.Grant;
import dev.demeng.grantx.api.event.GrantActivateEvent;
import me.amar.grantxproject.GrantXProject;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GrantEvent implements Listener {
    GrantXProject grantx = new GrantXProject();

    @EventHandler
    public void GrantEvent(GrantActivateEvent e) {
        grantx.getJda().getTextChannelById(696146110535827569L).sendMessage("test").queue();
    }
}
