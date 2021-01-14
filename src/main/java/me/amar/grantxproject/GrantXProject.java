package me.amar.grantxproject;

import me.amar.grantxproject.BotEvents.Commands;
import me.amar.grantxproject.Files.DataYml;
import me.amar.grantxproject.MinecraftEvents.ConfigReload;
import me.amar.grantxproject.MinecraftEvents.GrantEvent;
import me.amar.grantxproject.MinecraftEvents.GrantExpire;
import me.amar.grantxproject.MinecraftEvents.GrantRevoke;
import me.amar.grantxproject.Utils.Utils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class GrantXProject extends JavaPlugin {
    static String prefix = "logx";
    static JDA jda;
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        saveDefaultConfig();
            if(getConfig().getString("bot-token").equalsIgnoreCase("0")) {
                Utils.sendError(0, "Token is not set in config.yml", "The bot token has not been set.");
            } else {
                try {
                setJda(JDABuilder.createDefault(getConfig().getString("bot-token")).build());
                jda.retrieveApplicationInfo().queue(m -> {
                    if(m.isBotPublic()){
                        System.out.println("!-----------------------------------------------------!");
                        System.out.println("[GrantX Bot] WARNING!");
                        System.out.println("=> The bot is public.");
                        System.out.println("=> Set the bot to private to prevent others from inviting");
                        System.out.println("   it and messing with the logging channels and commands.");
                        System.out.println("!-----------------------------------------------------!");
                    }
                });
                jda.addEventListener(new Commands());
                jda.getPresence().setActivity(Activity.watching("Type \"logx help\" For Help!"));
                System.out.println("[GrantX Bot] The plugin has been enabled successfully.");
                } catch (LoginException e ) {
                    Utils.sendError(1, "Failed to enable the bot", "This usually happens if the token is invalid.");
                } catch(ErrorResponseException e) {
                    Utils.sendError(2, "Failed to connnect to the Discord API", "This usually occurs when there's no internet connection.");

                }
            }

            getServer().getPluginManager().registerEvents(new GrantEvent(), this);
            getServer().getPluginManager().registerEvents(new GrantExpire(), this);
            getServer().getPluginManager().registerEvents(new GrantRevoke(), this);
            getCommand("gtx").setExecutor(new ConfigReload());
    }

    @Override
    public void onDisable() {
        jda.shutdownNow();
        getLogger().info("Plugin disabled.");
    }
    public static JDA getJda() {
        return jda;
    }

    public void setJda(JDA jda) {
        this.jda = jda;
    }
    public static String getPrefix(){
        return prefix;
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
