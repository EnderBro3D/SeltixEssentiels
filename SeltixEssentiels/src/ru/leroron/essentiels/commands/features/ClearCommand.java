package ru.leroron.essentiels.commands.features;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.configs.MainConfig;

public class ClearCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("essentiels.invclear")) {
            sender.sendMessage(MainConfig.getMessage("messages.inventory.noperms"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(MainConfig.getMessage("messages.inventory.notplayer"));
            return true;
        }
        Player p = (Player) sender;
        p.getInventory().clear();
        p.getEquipment().clear();
        sender.sendMessage(MainConfig.getMessage("messages.inventory.clear"));
        return false;
    }
}