package me.amar.grantxproject.Files;

import me.amar.grantxproject.GrantXProject;
import me.amar.grantxproject.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataYml {
    private static GrantXProject plugin = GrantXProject.getPlugin(GrantXProject.class);

    public static FileConfiguration dataYml;
    public static File dataFile;

    public static void setUpDataYml() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        dataFile = new File(plugin.getDataFolder(), "data.yml");


        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
                Bukkit.getConsoleSender().sendMessage(Utils.colorize("&eLoaded data.yml &asuccessfully"));
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage(Utils.colorize("&cCould not load data.yml"));
                e.printStackTrace();
            }

        }

        dataYml = YamlConfiguration.loadConfiguration(dataFile);


    }

    public static FileConfiguration getDataYml() {
        return dataYml;
    }

    public static void saveDataYml() {
        try {
            dataYml.save(dataFile);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(Utils.colorize("&cCould not save data.yml"));
            e.printStackTrace();
        }
    }

    public static void reloadDataYml() {
        dataYml = YamlConfiguration.loadConfiguration(dataFile);
    }
}
