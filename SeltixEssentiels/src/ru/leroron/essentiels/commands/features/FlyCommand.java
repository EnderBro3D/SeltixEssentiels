package ru.leroron.essentiels.commands.features;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!sender.hasPermission("essentiels.fly")) {
            sender.sendMessage(MessageConfig.getMessage("fly.noperms"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageConfig.getMessage("fly.notplayer"));
            return true;
        }
        Player p = (Player) sender;
        boolean fly = p.getAllowFlight();
        p.setAllowFlight(!fly);
        p.setFlying(!fly);
        p.sendMessage(MessageConfig.getMessage("fly." + (fly ? "false" : "true")));
        p.playEffect(p.getLocation(), Effect.CLOUD, 1);
        return true;
    }
}