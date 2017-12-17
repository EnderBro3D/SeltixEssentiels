package ru.leroron.essentiels.commands.features;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;

import static java.lang.Runtime.getRuntime;

public class MemoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("essentiels.kick")) {
            sender.sendMessage(MessageConfig.getMessage("memory.noperms"));
            return true;
        }
        sender.sendMessage(MessageConfig.getMessage("memory.usage")
                .replace("%maxMemory", getRuntime().maxMemory() / (1024 * 1024) + " MB")
                .replace("%freeMemory", getRuntime().freeMemory() / (1024 * 1024) + " MB")
                .replace("%totalMemory", getRuntime().totalMemory() / (1024 * 1024) + " MB")
                .replace("%server", Bukkit.getServerName()));
        return true;
    }
}