package ru.leroron.essentiels.commands.features;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;

public class GodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("essentiels.god")) {
            return true;
        }
        if(!(sender instanceof Player)) {
            sender.sendMessage(MainConfig.getMessage("messages.notplayer"));
            return true;
        }
        Player p = (Player) sender;
        boolean god = Main.god.contains(p);

        if(god) Main.god.remove(p);
        else Main.god.add(p);
        sender.sendMessage(MainConfig.getMessage("messages.god." + god));
        return false;
    }
}