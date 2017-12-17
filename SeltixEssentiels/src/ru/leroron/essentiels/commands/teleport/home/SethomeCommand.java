package ru.leroron.essentiels.commands.teleport.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.HomeConfig;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;

public class SethomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!sender.hasPermission("essentiels.sethome")) {
            sender.sendMessage(MessageConfig.getMessage("locations.sethome.noperms"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageConfig.getMessage("notplayer"));
            return true;
        }
        HomeConfig.setHome((Player) sender, ((Player) sender).getLocation());
        sender.sendMessage(MessageConfig.getMessage("locations.sethome.msg"));
        return true;
    }
}
