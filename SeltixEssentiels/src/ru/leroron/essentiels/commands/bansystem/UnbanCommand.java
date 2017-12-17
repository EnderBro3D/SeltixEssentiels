package ru.leroron.essentiels.commands.bansystem;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;
import ru.leroron.essentiels.storage.BanStorage;

public class UnbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!sender.hasPermission("essentiels.unban")) {
            sender.sendMessage(MessageConfig.getMessage("bans.noperms"));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(MessageConfig.getMessage("bans.unban.usage"));
            return true;
        }
        OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
        if (!BanStorage.isBan(player)) {
            sender.sendMessage(MessageConfig.getMessage("bans.unban.notban"));
            return true;
        }
        BanStorage.unban(player);
        sender.sendMessage(MessageConfig.getMessage("bans.unban.msg").replace("%player", args[0]));
        return true;
    }
}
