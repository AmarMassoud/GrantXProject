package me.amar.grantxproject;

import me.amar.grantxproject.BotEvents.SetLogsChannel;
import me.amar.grantxproject.Files.DataYml;
import me.amar.grantxproject.MinecraftEvents.DataYmlReload;
import me.amar.grantxproject.MinecraftEvents.GrantEvent;
import me.amar.grantxproject.Utils.Utils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.exceptions.ContextException;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.internal.requests.Requester;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.net.UnknownHostException;

public final class GrantXProject extends JavaPlugin {

    static JDA jda;
    @Override
    public void onEnable() {
        loadConfigManager();
            if(DataYml.getDataYml().getString("bot-token").equalsIgnoreCase("0")) {
                Utils.sendError(0, "Token is not set in data.yml", "The bot token has not been set in the data.yml.");
            } else {
                try {
                setJda(JDABuilder.createDefault(DataYml.getDataYml().getString("bot-token")).build());
                jda.addEventListener(new SetLogsChannel());
                System.out.println("[GrantX Bot] The plugin has been enabled successfully.");
                } catch (LoginException e ) {
                    Utils.sendError(2, "Failed to enable the bot", "The plugin was unable to enable the bot, this usually happens if you didn't input the token correctly.");
                } catch(ErrorResponseException e) {
                    Utils.sendError(1, "Failed to connnect to the Discord API", "Failed to connect to the Discord API. This usually occurs when no internet connection is found.");

                }
            }

        getServer().getPluginManager().registerEvents(new GrantEvent(), this);
        getCommand("gtx").setExecutor(new DataYmlReload());
    }

    @Override
    public void onDisable() {
        jda.shutdown();
        getLogger().info("Plugin disabled.");
    }
    public static JDA getJda() {
        return jda;
    }

    public void setJda(JDA jda) {
        this.jda = jda;
    }

    public void loadConfigManager() {
        DataYml.setUpDataYml();
        DataYml.reloadDataYml();
        DataYml.getDataYml().addDefault("bot-token", "0");
        DataYml.getDataYml().addDefault("logs-channel-id", 0);
        DataYml.getDataYml().options().copyDefaults(true);
        DataYml.saveDataYml();
    }
}
