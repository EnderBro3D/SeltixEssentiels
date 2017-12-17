package ru.leroron.essentiels.commands.features;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;

import java.util.ArrayList;
import java.util.UUID;

public class VanishCommand implements CommandExecutor {


    public void hide(Player p) {
        Bukkit.getOnlinePlayers().forEach(p1 -> p1.hidePlayer(p));
        Main.vanish.add(p);
    }

    public void show(Player p) {
        Bukkit.getOnlinePlayers().forEach(p1 -> p1.showPlayer(p));
        Main.vanish.remove(p);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("essentiels.vanish")) {
            sender.sendMessage(MainConfig.getMessage("messages.vanish.noperms"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(MainConfig.getMessage("messages.notplayer"));
            return true;
        }
        Player player = (Player) sender;
        boolean vanish = Main.vanish.contains(player);
        if(vanish) show(player);
        else hide(player);
        sender.sendMessage(MainConfig.getMessage("messages.vanish." + vanish));
        return false;
    }
}