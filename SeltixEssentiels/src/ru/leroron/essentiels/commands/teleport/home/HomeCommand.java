package ru.leroron.essentiels.commands.teleport.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.HomeConfig;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!sender.hasPermission("essentiels.spawn")) {
            sender.sendMessage(MessageConfig.getMessage("locations.home.noperms"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageConfig.getMessage("notplayer"));
            return true;
        }
        Player p = (Player) sender;
        if(!HomeConfig.isHomeSet(p)) {
            sender.sendMessage(MessageConfig.getMessage("locations.home.notset"));
            return true;
        }
        p.teleport(HomeConfig.getHome((Player) sender));
        sender.sendMessage(MessageConfig.getMessage("locations.home.msg"));
        return true;
    }
}
