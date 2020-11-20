package me.amar.grantxproject.MinecraftEvents;

import me.amar.grantxproject.Files.DataYml;
import me.amar.grantxproject.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DataYmlReload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("grantxBot.reload")) {
                DataYml.reloadDataYml();
                sender.sendMessage(Utils.colorize("&cData.yml has been reloaded successfully."));
            } else {
                sender.sendMessage(Utils.colorize("&cYou do not have permission to execute this command."));
            }
        } else {
            sender.sendMessage(Utils.colorize("&cUnknown command."));
        }







        return true;
    }
}
