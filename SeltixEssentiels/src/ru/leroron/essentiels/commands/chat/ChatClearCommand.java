package ru.leroron.essentiels.commands.chat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;

public class ChatClearCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("essentiels.chatclear")) {
            sender.sendMessage(MainConfig.getMessage("messages.chat.clear.noperms"));
        }
        String name = sender instanceof Player ? Main.getPrefix((Player) sender) + sender.getName() : "Console";
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < 100; i++) b.append(" \n");

        String msg = MainConfig.getMessage("messages.chat.clear.send")
                .replace("%player", name);
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.sendMessage(b.toString());
            online.sendMessage(msg);
        }
        return false;
    }
}
