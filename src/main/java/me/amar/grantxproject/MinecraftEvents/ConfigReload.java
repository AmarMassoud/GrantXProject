package me.amar.grantxproject.MinecraftEvents;

import me.amar.grantxproject.GrantXProject;
import me.amar.grantxproject.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ConfigReload implements CommandExecutor {
    private static GrantXProject plugin = GrantXProject.getPlugin(GrantXProject.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("grantxBot.reload")) {
                plugin.reloadConfig();
                sender.sendMessage(Utils.colorize("&cconfig.yml has been reloaded successfully."));
            } else {
                sender.sendMessage(Utils.colorize("&cYou do not have permission to execute this command."));
            }
        } else {
            sender.sendMessage(Utils.colorize("&cUnknown command."));
        }







        return true;
    }
}
