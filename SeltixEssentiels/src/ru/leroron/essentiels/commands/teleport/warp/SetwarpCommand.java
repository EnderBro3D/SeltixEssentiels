package ru.leroron.essentiels.commands.teleport.warp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.WarpConfig;

public class SetwarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!sender.hasPermission("essentiels.setwarp")) {
            sender.sendMessage(MainConfig.getMessage("messages.locations.setwarp.noperms"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(MainConfig.getMessage("messages.notplayer"));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(MainConfig.getMessage("messages.locations.setwarp.usage"));
            return true;
        }
        WarpConfig.setWarp(args[0], ((Player) sender).getLocation());
        sender.sendMessage(MainConfig.getMessage("messages.locations.setwarp.msg").replace("%warp", args[0]));
        return true;

    }
}
