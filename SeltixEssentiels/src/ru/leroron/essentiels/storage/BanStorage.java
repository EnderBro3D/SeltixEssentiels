package ru.leroron.essentiels.storage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.ConfigManager;
import ru.leroron.essentiels.configs.MainConfig;

@SuppressWarnings("All")
public class BanStorage {

    public static void loadBans(JavaPlugin plugin) {
        ConfigManager.loadConfiguration(3, "bans.yml", plugin, false, true, null);
    }

    public static void reloadConfig() {
        ConfigManager.reloadConfiguration(3);
    }

    public static void saveConfig() {
        ConfigManager.saveConfiguration(3);
    }

    public static YamlConfiguration getConfig() {
        return ConfigManager.getConfiguration(3);
    }


    public static boolean isBan(OfflinePlayer player) {
        return getConfig().contains("bans." + player.getName().toLowerCase());
    }

    public static String getReason(OfflinePlayer player) {
        if (!isBan(player)) return null;
        return getConfig().getString("bans." + player.getName().toLowerCase() + ".reason");
    }

    public static String compileReason(OfflinePlayer player) {
        return MainConfig.getMessage("messages.bans.reason")
                .replace("%reason", getReason(player))
                .replace("%whoban", getWhoban(player));
    }

    public static void kick(Player whoKick, Player player, String reason) {
        player.kickPlayer(MainConfig.getMessage("messages.kick.reason")
                .replace("%reason", reason == null ? "Нет причины" : reason)
                .replace("%whokick", whoKick == null ? "Console" : Main.getPrefix(whoKick) + whoKick.getName()));

        whoKick.sendMessage(MainConfig.getMessage("messages.kick.msg"));
        Bukkit.getOnlinePlayers().stream()
                .filter(p -> p.hasPermission("essentiels.kick.see"))
                .forEach(p -> p.sendMessage(MainConfig.getMessage("messages.kick.kickall")
                        .replace("%player", player.getName())
                        .replace("%whoKick", whoKick == null ? "Console" : Main.getPrefix(whoKick) + whoKick.getName())
                        .replace("%reason", reason)));
    }

    public static void unban(OfflinePlayer player) {
        getConfig().set("bans." + player.getPlayer().toString(), null);
        saveConfig();
    }

    public static String getWhoban(OfflinePlayer player) {
        if (!isBan(player)) return null;
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString("bans." + player.getName().toLowerCase() + ".whoban"));
    }

    public static void ban(Player whoBan, OfflinePlayer player, String reason) {
        getConfig().set("bans." + player.getName().toLowerCase() + ".reason", reason == null ? "Нет причины" : reason);
        getConfig().set("bans." + player.getName().toLowerCase() + ".whoban", whoBan == null ? "Console" : Main.getPrefix(whoBan) + whoBan.getName());
        saveConfig();
        if (player.isOnline()) player.getPlayer().kickPlayer(compileReason(player));

        whoBan.sendMessage(MainConfig.getMessage("messages.bans.msg"));
        Bukkit.getOnlinePlayers().stream()
                .filter(p -> p.hasPermission("essentiels.ban.see"))
                .forEach(p -> p.sendMessage(MainConfig.getMessage("messages.bans.banall")
                        .replace("%player", player.getName())
                        .replace("%whoBan", whoBan == null ? "Console" : Main.getPrefix(whoBan) + whoBan.getName())
                        .replace("%reason", reason)));
    }
}
/*
© While Inside 2017
Вы можете использовать данный материал
только для чтения. 
Копировать его можете только при условии, что оставите
ссылку на мой ВК.
http://vk.com/EnderBro3D

Также вы можете задавать вопросы мне в лс.
*/