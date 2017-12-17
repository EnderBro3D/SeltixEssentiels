package ru.leroron.essentiels.configs;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.utils.LocationUtil;

public class MainConfig {

    public static void loadConfig(Main plugin) {
        ConfigManager.loadConfiguration(0, "config.yml", plugin, true, true, null);
    }

    public static void reloadConfig() {
        ConfigManager.reloadConfiguration(0);
    }

    public static void saveConfig() {
        ConfigManager.saveConfiguration(0);
    }

    public static YamlConfiguration getConfig() {
        return ConfigManager.getConfiguration(0);
    }

    public static void setSpawn(Location loc) {
        getConfig().set("locations.spawn", LocationUtil.toStringWXYZYP(loc));
        saveConfig();
    }

    public static boolean isSpawnSet() {
        return getConfig().contains("locations.spawn");
    }

    public static Location getSpawn() {
        return LocationUtil.fromStringWXYZYP(getConfig().getString("locations.spawn"));
    }
}
