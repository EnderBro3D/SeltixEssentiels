package ru.leroron.essentiels.configs;

import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.leroron.essentiels.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@SuppressWarnings("All")
public class ConfigManager {
    private static Map<Integer, YamlConfiguration> configurationMap = Maps.newHashMap();
    private static Map<Integer, File> fileMap = Maps.newHashMap();

    private static final String MESSAGE_LOAD = "Loading %s configuration",
            MESSAGE_CHECK = "Checking file: %s",
            MESSAGE_PROPS = "%s properties: {resource=%s, async=%s}";

    public static void saveConfiguration(int id) {
        if(!configurationMap.containsKey(id)) return;

        try {
            configurationMap.get(id).save(fileMap.get(id));
        } catch (IOException e) {
            System.err.println(e + ": Cannot save a " + fileMap.get(id) + " configuration");
        }
    }

    private static void disable(JavaPlugin plugin, String message) {
        System.err.println(message);
        Main.getPlugin().getPluginLoader().disablePlugin(plugin);
    }

    public static void reloadConfiguration(int id) {
        if(!configurationMap.containsKey(id)) return;

        try {
            configurationMap.get(id).load(fileMap.get(id));
        } catch (IOException e) {
            System.err.println(e + ": Cannot load a " + fileMap.get(id) + " configuration");
        } catch (InvalidConfigurationException e) {
            System.err.println(e + ": " + fileMap.get(id) + " invalid configuration");
        }
    }

    public static YamlConfiguration getConfiguration(int id) {
        return configurationMap.get(id);
    }

    public static void loadConfiguration(int id, String name, JavaPlugin plugin, boolean resource, boolean async, Consumer<YamlConfiguration> onLoad) {


        Runnable r = new Runnable() {
            @Override
            public void run() {

                Main.a(MESSAGE_LOAD, name);
                File configFile = new File(plugin.getDataFolder(), name);

                try {
                    Main.a(MESSAGE_CHECK, configFile);
                    if (!configFile.getParentFile().exists()) configFile.getParentFile().mkdirs();
                    if (configFile.isDirectory()) configFile.isDirectory();
                    if (!configFile.exists()) configFile.createNewFile();
                } catch (IOException e) {
                    disable(plugin, "Error while checking a file. " + e + ": " + e.getMessage());
                    return;
                }


                Main.a(MESSAGE_PROPS, name, resource, async);

                if (resource) {
                    try {
                        byte[] bytes = IOUtils.toByteArray(plugin.getResource(name));
                        Files.write(configFile.toPath(), bytes);
                    } catch (IOException e) {
                        disable(plugin, "Error while copying a file. " + e + ": " + e.getMessage());
                        return;
                    }
                }

                YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configFile);
                fileMap.put(id, configFile);
                configurationMap.put(id, configuration);
                if(onLoad != null) onLoad.accept(configuration);

                System.out.println("Load successfully completed.");
            }
        };

        if(async) Executors.newSingleThreadExecutor().execute(r);
        else r.run();
    }
}