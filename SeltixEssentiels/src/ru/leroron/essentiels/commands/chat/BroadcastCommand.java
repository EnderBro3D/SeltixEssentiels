package ru.leroron.essentiels.commands.chat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;

public class BroadcastCommand implements CommandExecutor {
    

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("essentiels.staffchat")) {
            sender.sendMessage(MessageConfig.getMessage("chat.broadcast.noperms"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageConfig.getMessage("notplayer"));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(MessageConfig.getMessage("chat.broadcast.usage"));
            return true;
        }
        Player p = (Player) sender;
        StringBuilder msgBuilder = new StringBuilder();
        for(String arg : args) msgBuilder.append(arg + " ");

        String msg = MessageConfig.getMessage("chat.broadcast.send")
                .replace("%message", msgBuilder.toString())
                .replace("%player", Main.getPrefix(p) + p.getName());
        Bukkit.getOnlinePlayers().forEach(p1 -> p1.sendMessage(msg));
        return true;
    }
}