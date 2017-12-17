package ru.leroron.essentiels.commands.chat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;

public class AskCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("essentiels.ask")) {
            sender.sendMessage(MessageConfig.getMessage("ask.noperms"));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(MessageConfig.getMessage("ask.usage"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageConfig.getMessage("notplayer"));
            return true;
        }

        Player p = (Player) sender;

        StringBuilder msgBuilder = new StringBuilder();
        for (String arg : args) msgBuilder.append(arg + " ");

        String msg = MessageConfig.getMessage("ask.adminmode.message")
                .replace("%sender", Main.getPrefix(p) + p.getName())
                .replace("%message", msgBuilder.toString());
        Bukkit.getOnlinePlayers()
                .stream()
                .filter(player -> player.hasPermission("essentiels.ask.reply"))
                .forEach(player -> player.sendMessage(msg));
        sender.sendMessage(MessageConfig.getMessage("ask.send")
                .replace("%sender", Main.getPrefix(p) + p.getName())
                .replace("%message", msg));
        Main.asks.put(p, msgBuilder.toString());
        return false;
    }
}
