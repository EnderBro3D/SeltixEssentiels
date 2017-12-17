package ru.leroron.essentiels.commands.features;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;

public class ReportCommand implements CommandExecutor {

    public void report(Player reporter, Player p, String reason) {
        if (reason == null) reason = "§eНет причины";
        Main.reports.put(p, reason);

        String msg = MessageConfig.getMessage("report.adminmode.message")
                .replace("%player", Main.getPrefix(p) + p.getName())
                .replace("%sender", Main.getPrefix(reporter) + reporter.getName())
                .replace("%reason", reason);
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> player.hasPermission("essentiels.staffreport"))
                .forEach(player -> player.sendMessage(msg));
        reporter.sendMessage(MessageConfig.getMessage("report.send")
                .replace("%player", Main.getPrefix(p) + p.getName())
                .replace("%reason", "§eНет причины"));
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("essentiels.report")) {
            sender.sendMessage(MessageConfig.getMessage("report.noperms"));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(MessageConfig.getMessage("report.usage"));
            return true;
        }
        String name = args[0];
        Player p1 = Bukkit.getPlayer(name);
        if (p1 == null) {
            sender.sendMessage(MessageConfig.getMessage("report.notonline"));
            return true;
        }
        if (Main.reports.containsKey(p1)) {
            sender.sendMessage(MessageConfig.getMessage("report.alreadyreport")
                    .replace("%player", Main.getPrefix(p1) + p1.getName()));
            return true;
        }

        if (args.length == 1) report((Player) sender, p1, null);
        else {
            StringBuilder msg = new StringBuilder();
            for (int i = 1; i < args.length; i++) msg.append(args[i] + " ");
            report((Player) sender, p1, msg.toString());
            return true;
        }
        return true;
    }
}
