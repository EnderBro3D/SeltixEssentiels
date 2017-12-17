package ru.leroron.essentiels.commands.features;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.configs.MessageConfig;

@SuppressWarnings("All")
public class KnockbackCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!sender.hasPermission("essentiels.knockback")) {
            sender.sendMessage(MessageConfig.getMessage("knockback.noperms"));
            return true;
        }
        if(!(sender instanceof Player)) {
            sender.sendMessage(MessageConfig.getMessage("notplayer"));
            return true;
        }
        Player p = (Player) sender;

        return false;
    }
}