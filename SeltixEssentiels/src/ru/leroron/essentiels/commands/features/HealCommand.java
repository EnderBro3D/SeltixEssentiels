package ru.leroron.essentiels.commands.features;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.configs.MainConfig;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender.hasPermission("essentiels.heal"))) {
            sender.sendMessage(MainConfig.getMessage("messages.heal.noperms"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(MainConfig.getMessage("messages.heal.notplayer"));
            return true;
        }
        Player p = (Player) sender;
        sender.sendMessage(MainConfig.getMessage("messages.heal.usage"));
        p.setHealth(20);
        return false;
    }
}