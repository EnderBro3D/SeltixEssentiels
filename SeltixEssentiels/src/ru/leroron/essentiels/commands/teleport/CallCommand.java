package ru.leroron.essentiels.commands.teleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;

public class CallCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("essentiels.call")) {
            sender.sendMessage(MessageConfig.getMessage("call.noperms"));
            return true;
        }
        if(!(sender instanceof Player)) {
            sender.sendMessage(MessageConfig.getMessage("notplayer"));
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage(MessageConfig.getMessage("call.usage"));
            return true;
        }
        String s = args[0];
        Player p1 = (Player) sender;
        Player p = Bukkit.getPlayer(s);
        if (p == null) {
            String s1 = MessageConfig.getMessage("call.notonline")
                    .replace("%player", s);
            sender.sendMessage(s1);
            return true;
        }

        p.sendMessage(MessageConfig.getMessage("call.requestmsg").replace("%player", Main.getPrefix(p1) + p1.getName()));
        sender.sendMessage(MessageConfig.getMessage("call.send").replace("%player", Main.getPrefix(p) + p.getName()));
        Main.requests.put((Player) sender, p);
        return true;

    }
}
