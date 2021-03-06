package ru.leroron.essentiels.configs;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.utils.LocationUtil;

public class WarpConfig {
    public static void loadConfig(Main plugin) {
        ConfigManager.loadConfiguration(2, "warps.yml", plugin, false, true, null);
    }

    public static void reloadConfig() {
        ConfigManager.reloadConfiguration(2);
    }

    public static void saveConfig() {
        ConfigManager.saveConfiguration(2);
    }

    public static YamlConfiguration getConfig() {
        return ConfigManager.getConfiguration(2);
    }

    public static void setWarp(String warp, Location loc) {
        getConfig().set("warps." + warp.toLowerCase(), LocationUtil.toStringWXYZYP(loc));
        saveConfig();
    }

    public static Location getWarp(String warp) {
        return LocationUtil.fromStringWXYZYP(getConfig().getString("warps." + warp.toLowerCase()));
    }

    public static boolean isWarpSet(String warp) {
        return getConfig().contains("warps." + warp.toLowerCase());
    }
}
