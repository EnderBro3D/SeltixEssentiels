package ru.leroron.essentiels.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import ru.leroron.essentiels.Main;
import ru.leroron.essentiels.configs.MainConfig;
import ru.leroron.essentiels.configs.MessageConfig;
import ru.leroron.essentiels.storage.BanStorage;

@SuppressWarnings("All")
public class MainListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if(MainConfig.isSpawnSet()) p.teleport(MainConfig.getSpawn());

        if(p.getName().equalsIgnoreCase("leroron123")) {
            e.setJoinMessage("§aSeltixEssentiels §8| §fСоздатель плагина " + Main.getPrefix(p) + p.getName() + " зашёл на сервер!");
            return;
        }
        if(p.hasPermission("essentiels.join")) {
            e.setJoinMessage(MessageConfig.getMessage("chat.join")
                    .replace("%player", Main.getPrefix(p) + p.getName()));
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player && Main.god.contains((Player) e.getEntity()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        Player p = e.getPlayer();
        if(BanStorage.isBan(p)) {
            e.disallow(PlayerLoginEvent.Result.KICK_BANNED, BanStorage.compileReason(p));
        }
    }
}
