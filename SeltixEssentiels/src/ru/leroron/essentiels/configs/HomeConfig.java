package ru.leroron.essentiels.configs;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;

public class HomeConfig {
    public static void loadConfig(Main plugin) {
        ConfigManager.loadConfiguration(1, "homes.yml", plugin, false, true, null);
    }

    public static void reloadConfig() {
        ConfigManager.reloadConfiguration(1);
    }

    public static void saveConfig() {
        ConfigManager.saveConfiguration(1);
    }

    public static YamlConfiguration getConfig() {
        return ConfigManager.getConfiguration(1);
    }

    public static void setHome(Player p, Location loc) {
        getConfig().set("homes." + p.getName().toLowerCase(), loc);
        saveConfig();
    }

    public static Location getHome(Player p) {
        return (Location) getConfig().get("homes." + p.getName().toLowerCase());
    }

    public static boolean isHomeSet(Player p) {
        return getConfig().contains("homes." + p.getName().toLowerCase());
    }
}
