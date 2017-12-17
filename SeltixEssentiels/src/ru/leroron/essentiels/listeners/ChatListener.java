package ru.leroron.essentiels.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;

@SuppressWarnings("All")
public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        e.setFormat(MainConfig.getMessage("messages.chat.format")
                .replace("%player", p.getName()
                .replace("%prefix", Main.getPrefix(p))
                .replace("%suffix", Main.getSuffix(p))
                .replace("%message", e.getMessage())));
    }
}