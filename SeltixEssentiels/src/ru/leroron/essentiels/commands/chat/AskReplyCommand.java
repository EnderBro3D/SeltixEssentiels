package ru.leroron.essentiels.commands.chat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;

public class AskReplyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("essentiels.ask.reply")) {
            sender.sendMessage(MessageConfig.getMessage("ask.noperms"));
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage(MessageConfig.getMessage("ask.adminmode.reply.usage"));
            return true;
        }
        if(!(sender instanceof Player)) {
            sender.sendMessage(MessageConfig.getMessage("notplayer"));
            return true;
        }
        Player p1 = (Player) sender;
        Player p = Bukkit.getPlayer(args[0]);
        if(p == null) {
            sender.sendMessage(MessageConfig.getMessage("ask.notonline").replace("%player", args[0]));
            return true;
        }
        if(!Main.asks.containsKey(p)) {
            sender.sendMessage(MessageConfig.getMessage("ask.notask").replace("%player", Main.getPrefix(p) + p.getName()));
            return true;
        }


        StringBuilder msgBuilder = new StringBuilder();
        for (int i = 1; i < args.length; i++) msgBuilder.append(args[i] + " ");

        sender.sendMessage(MessageConfig.getMessage("ask.adminmode.reply.send")
                .replace("%player", Main.getPrefix(p) + p.getName()));
        p.sendMessage(MessageConfig.getMessage("ask.adminmode.reply.answer")
                .replace("%reply", msgBuilder.toString())
                .replace("%sender",  Main.getPrefix(p1) + p1.getName()));
        Main.asks.remove(p);
        return true;
    }
}