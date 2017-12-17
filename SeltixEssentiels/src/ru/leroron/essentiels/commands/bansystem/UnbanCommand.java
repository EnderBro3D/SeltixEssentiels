package ru.leroron.essentiels.commands.bansystem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.storage.BanStorage;

public class UnbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!sender.hasPermission("essentiels.unban")) {
            sender.sendMessage(MainConfig.getMessage("messages.bans.noperms"));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(MainConfig.getMessage("messages.bans.unban.usage"));
            return true;
        }
        if (BanStorage.isBan(Bukkit.getOfflinePlayer(args[0]))) {
            sender.sendMessage(MainConfig.getMessage("messages.bans.unban.notban"));
            return true;
        }
        sender.sendMessage(MainConfig.getMessage("messages.bans.unban.msg").replace("%player", args[0]));
        return true;
    }
}
