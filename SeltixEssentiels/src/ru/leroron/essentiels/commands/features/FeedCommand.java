package ru.leroron.essentiels.commands.features;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.configs.MainConfig;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender.hasPermission("essentiels.feed"))) {
            sender.sendMessage(MainConfig.getMessage("messages.feed.noperms"));

        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(MainConfig.getMessage("messages.feed.notplayer"));
            return true;

        }
        Bukkit.getPlayer(sender.getName()).setFoodLevel(20);
        sender.sendMessage(MainConfig.getMessage("messages.feed.usage"));
        return false;
    }
}