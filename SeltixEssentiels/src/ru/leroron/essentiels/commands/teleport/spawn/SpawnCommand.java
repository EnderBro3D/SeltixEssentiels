package ru.leroron.essentiels.commands.teleport.spawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!sender.hasPermission("essentiels.spawn")) {
            sender.sendMessage(MessageConfig.getMessage("locations.spawn.noperms"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageConfig.getMessage("notplayer"));
            return true;
        }
        Player p = (Player) sender;
        if(!MainConfig.isSpawnSet()) {
            sender.sendMessage(MessageConfig.getMessage("locations.spawn.notset"));
            return true;
        }
        p.teleport(MainConfig.getSpawn());
        sender.sendMessage(MessageConfig.getMessage("locations.spawn.msg"));
        return true;
    }
}
