package ru.leroron.essentiels.configs;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.leroron.essentiels.Main;

@SuppressWarnings("All")
public class MessageConfig {
    public static void loadConfig(Main plugin) {
        ConfigManager.loadConfiguration(4, "messages.yml", plugin, true, true, null);
    }

    public static void reloadConfig() {
        ConfigManager.reloadConfiguration(4);
    }

    public static void saveConfig() {
        ConfigManager.saveConfiguration(4);
    }

    public static YamlConfiguration getConfig() {
        return ConfigManager.getConfiguration(4);
    }

    public static String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages." + path));
    }
}