package ru.leroron.essentiels.commands.teleport.warp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;
import ru.leroron.essentiels.configs.WarpConfig;

public class SetwarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!sender.hasPermission("essentiels.setwarp")) {
            sender.sendMessage(MessageConfig.getMessage("locations.setwarp.noperms"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageConfig.getMessage("notplayer"));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(MessageConfig.getMessage("locations.setwarp.usage"));
            return true;
        }
        WarpConfig.setWarp(args[0], ((Player) sender).getLocation());
        sender.sendMessage(MessageConfig.getMessage("locations.setwarp.msg").replace("%warp", args[0]));
        return true;

    }
}
