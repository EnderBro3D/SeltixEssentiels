package ru.leroron.essentiels.commands.teleport.spawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.configs.MainConfig;

public class SetspawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!sender.hasPermission("essentiels.setspawn")) {
            sender.sendMessage(MainConfig.getMessage("messages.locations.setspawn.noperms"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(MainConfig.getMessage("messages.essentiels.notplayer"));
            return true;
        }
        MainConfig.setSpawn(((Player) sender).getLocation());
        sender.sendMessage(MainConfig.getMessage("messages.locations.setspawn.msg"));
        return true;
    }
}
