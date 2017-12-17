package ru.leroron.essentiels.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;

@SuppressWarnings("All")
public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        e.setFormat(MessageConfig.getMessage("chat.format")
                .replace("%player", "%1$s")
                .replace("%prefix", Main.getPrefix(p))
                .replace("%suffix", Main.getSuffix(p))
                .replace("%message", "%2$s"));
    }
}