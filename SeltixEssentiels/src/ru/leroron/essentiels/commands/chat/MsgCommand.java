package ru.leroron.essentiels.commands.chat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;

public class MsgCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("essentiels.msg")) {
            sender.sendMessage(MainConfig.getMessage("messages.msg.noperms"));
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage(MainConfig.getMessage("messages.msg.usage"));
            return true;
        }
        if(!(sender instanceof Player)) {
            sender.sendMessage(MainConfig.getMessage("messages.notplayer"));
            return false;
        }
        Player p1 = (Player) sender;
        Player p = Bukkit.getPlayer(args[0]);
        if (p == null) {
            sender.sendMessage(MainConfig.getMessage("messages.msg.notonline")
            .replace("%player", args[0]));
            return true;
        }
        StringBuilder msg = new StringBuilder();
        for (int i = 1; i < args.length; i++) msg.append(args[i] + " ");

        sender.sendMessage(MainConfig.getMessage("messages.msg.msgformat.you"
                .replace("%player", Main.getPrefix(p) + p.getName()))
                .replace("%message", msg.toString()));
        p.sendMessage(MainConfig.getMessage("messages.msg.msgformat.to"
                .replace("%player", Main.getPrefix(p1) + p1.getName()))
                .replace("%message", msg.toString()));
        return true;
    }
}
