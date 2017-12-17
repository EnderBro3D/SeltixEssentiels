package ru.leroron.essentiels.commands.bansystem;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.storage.BanStorage;

public class BanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!sender.hasPermission("essentiels.ban")) {
            sender.sendMessage(MainConfig.getMessage("messages.bans.noperms"));
            return true;

        }
        if (args.length > 1) {
            OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
            if(BanStorage.isBan(p)) {
                sender.sendMessage(MainConfig.getMessage("messages.bans.alreadyban")
                        .replace("%player", args[0]));
                return true;
            }

            if(args.length == 1) {
                BanStorage.ban(sender instanceof Player ? null : (Player) sender, p, null);
                return true;
            }
            StringBuilder builder = new StringBuilder();
            for(int i = 1;i < args.length;i++) builder.append(args[i]);
            BanStorage.ban(sender instanceof Player ? (Player) sender : null, p, builder.toString());
            return true;
        }

        sender.sendMessage(MainConfig.getMessage("messages.ban.usage"));
        return false;
    }
}