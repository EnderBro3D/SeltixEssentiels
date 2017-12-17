package ru.leroron.essentiels.commands.teleport;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;

public class TpAcceptCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("essentiels.call")) {
            sender.sendMessage(MessageConfig.getMessage("call.noperms"));
            return true;
        }
        Main.accept((Player) sender);
        return true;

    }
}
