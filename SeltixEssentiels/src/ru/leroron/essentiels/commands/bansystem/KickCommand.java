package ru.leroron.essentiels.commands.bansystem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.storage.BanStorage;

public class KickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("essentiels.kick")) {
            sender.sendMessage(MainConfig.getMessage("messages.kick.noperms"));
            return true;

        }
        if (args.length == 0) {
            sender.sendMessage(MainConfig.getMessage("messages.kick.usage"));
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(MainConfig.getMessage("messages.kick.notonline").replace("%player", args[0]));
            return true;
        }

        if (args.length == 1) {
            BanStorage.kick(sender instanceof Player ? (Player) sender : null, player, null);
            return true;
        }

        StringBuilder reasonBuilder = new StringBuilder();
        for(int i = 1;i < args.length;i++) reasonBuilder.append(args[i] + " ");
        BanStorage.kick(sender instanceof Player ? (Player) sender : null, player, reasonBuilder.toString());
        return true;
    }
}
