package ru.leroron.essentiels.commands.features;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;

public class GamemodeCommand implements CommandExecutor {
    public void setGamemode(Player p1, Player p, String s) {
        GameMode mode;
        try {
            int i = Integer.parseInt(s);
            p.setGameMode(mode = GameMode.getByValue(i));
        } catch (NumberFormatException e) {
            p.setGameMode(mode = GameMode.valueOf(s.toUpperCase()));
        }
        p1.sendMessage(MessageConfig.getMessage("gamemode.msg")
                .replace("%player", Main.getPrefix(p) + p.getName())
                .replace("%mode", mode.toString()));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!sender.hasPermission("essentiels.gamemode")) {
            sender.sendMessage(MessageConfig.getMessage("gamemode.noperms"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageConfig.getMessage("gamemode.notplayer"));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(MessageConfig.getMessage("gamemode.usage"));
            return true;
        }
        if(args.length == 1) {
            setGamemode((Player) sender, (Player) sender, args[0]);
        } else {
            Player p = Bukkit.getPlayer(args[1]);
            if(p == null) {
                sender.sendMessage(MessageConfig.getMessage("gamemode.notonline"));
                return true;
            }
            setGamemode((Player) sender, p, args[0]);
        }
        return false;
    }
}