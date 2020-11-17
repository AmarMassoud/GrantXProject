package me.amar.grantxproject;

import me.amar.grantxproject.events.GrantEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class GrantXProject extends JavaPlugin {

    JDA jda;
    @Override
    public void onEnable() {
        try {
            setJda(JDABuilder.createDefault("NzI4NDE4ODI4OTYyNDk2NTMz.Xv6G5g.LgR-P2TjXxPFlzASkwkg0cNJ99Q").build());
        } catch (LoginException e) {
            System.out.println("Failed to enable the bot.");
        }
        getServer().getPluginManager().registerEvents(new GrantEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public JDA getJda() {
        return jda;
    }

    public void setJda(JDA jda) {
        this.jda = jda;
    }

}
