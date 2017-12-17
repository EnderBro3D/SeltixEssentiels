package ru.leroron.essentiels.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.configs.HomeConfig;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.WarpConfig;
import ru.leroron.essentiels.storage.BanStorage;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!sender.hasPermission("essentiels.help")) {
            sender.sendMessage(MainConfig.getMessage("messages.essentiels.noperms"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(MainConfig.getMessage("messages.essentiels.notplayer"));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§6Essentiels §8| §7by Leroron123 v1.11 §8| §bMineHack Community");
            sender.sendMessage(" §6• §fСменить игровой режим - §6/gm [0/1/2/3]");
            sender.sendMessage(" §6• §fВключить режим полета - §6/fly");
            sender.sendMessage(" §6• §fТелепортироваться к игроку - §6/call [ник]");
            sender.sendMessage("  §e• §fПринять запрос на телепортацию - §e/tpaccept §fили §e/tpyes");
            sender.sendMessage("  §e• §fОтклонить запрос на телепортацию - §e/tpdeny §fили §e/tpno");
            sender.sendMessage(" §6• §fКикнуть игрока - §6/kick [ник] [причина]");
            sender.sendMessage(" §6• §fОчистить инвентарь - §6/clear");
            sender.sendMessage(" §6• §fПокормит себя - §6/feed");
            sender.sendMessage(" §6• §fВылечить себя - §6/heal");
            sender.sendMessage(" §6• §fВключить/выключить режим полета - §6/fly");
            sender.sendMessage(" §6• §fВключить/выключить режим невидимости - §6/vanish");
            sender.sendMessage(" §6• §fПоказать статистику памяти сервера - §6/memory");
            sender.sendMessage(" §6• §fПодать жалобу на игрока - §6/report [ник] [причина]");
            sender.sendMessage(" §6• §fЗадать вопрос персоналу сервера - §6/chat [сообщение]");
            sender.sendMessage("  §e• §fОтветить на вопрос игрока - §e/askreply [ник] [сообщение]");
            sender.sendMessage(" §6• §fВключить/выключить режим Бога - §6/god");
            sender.sendMessage(" §6• §fУстановить точку спавна - §6/setspawn");
            sender.sendMessage(" §6• §fТелепортироваться на спавн - §6/spawn");
            sender.sendMessage(" §6• §fУстановить точку дома - §6/sethome");
            sender.sendMessage(" §6• §fТелепортироваться домой - §6/home");
            sender.sendMessage(" §6• §fПоставить варп - §6/setwarp [имя]");
            sender.sendMessage(" §6• §fТелепортироваться на варп - §6/warp [имя]");
            sender.sendMessage(" §6• §fОткрыть помощь по экономике - §6/economy");
            sender.sendMessage(" §6• §fСделать объявление на сервер - §6/broadcast [сообщение]");
            sender.sendMessage(" §6• §fНаписать в чат персонала - §6/staffchat [сообщение]");
            sender.sendMessage(" §6• §fОчистить чат - §6/chatclear");
            sender.sendMessage(" §6• §fНаписать в личные сообщения - §6/msg [ник] [сообщение]");
            sender.sendMessage(" §6• §fОтветить в личные сообщения - §6/reply");
            sender.sendMessage(" §6• §fИгнорировать игрока - §6/ignore [ник]");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload": {
                BanStorage.reloadConfig();
                HomeConfig.reloadConfig();
                WarpConfig.reloadConfig();
                MainConfig.reloadConfig();
                sender.sendMessage(MainConfig.getMessage("messages.essentiels.reload"));
                break;
            }
            case "info": {
                sender.sendMessage("§6Info §8| §fПлагин разработан §6Leroron123 §fдля §bMineHack Community");
                break;
            }
            default: {
                sender.sendMessage("§6Essentiels §8| §fПодкоманда не найдена!");
                break;
            }
        }
        return true;
    }
}