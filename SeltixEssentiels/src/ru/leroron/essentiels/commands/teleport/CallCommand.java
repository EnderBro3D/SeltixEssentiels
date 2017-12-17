package ru.leroron.essentiels.commands.teleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;

public class CallCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("essentiels.call")) {
            sender.sendMessage(MainConfig.getMessage("messages.call.noperms"));
            return true;
        }
        if(!(sender instanceof Player)) {
            sender.sendMessage(MainConfig.getMessage("messages.notplayer"));
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage(MainConfig.getMessage("messages.call.usage"));
            return true;
        }
        String s = args[0];
        Player p1 = (Player) sender;
        Player p = Bukkit.getPlayer(s);
        if (p == null) {
            String s1 = MainConfig.getMessage("messages.call.notonline")
                    .replace("%player", s);
            sender.sendMessage(s1);
            return true;
        }

        p.sendMessage(MainConfig.getMessage("messages.call.requestmsg").replace("%player", Main.getPrefix(p1) + p1.getName()));
        sender.sendMessage(MainConfig.getMessage("messages.call.send").replace("%player", Main.getPrefix(p) + p.getName()));
        Main.requests.put((Player) sender, p);
        return true;

    }
}
