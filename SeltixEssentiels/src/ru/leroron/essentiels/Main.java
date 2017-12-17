package ru.leroron.essentiels;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import ru.leroron.essentiels.commands.MainCommand;
import ru.leroron.essentiels.commands.bansystem.BanCommand;
import ru.leroron.essentiels.commands.bansystem.KickCommand;
import ru.leroron.essentiels.commands.bansystem.UnbanCommand;
import ru.leroron.essentiels.commands.chat.*;
import ru.leroron.essentiels.commands.features.*;
import ru.leroron.essentiels.commands.teleport.CallCommand;
import ru.leroron.essentiels.commands.teleport.TpAcceptCommand;
import ru.leroron.essentiels.commands.teleport.TpDenyCommand;
import ru.leroron.essentiels.commands.teleport.home.HomeCommand;
import ru.leroron.essentiels.commands.teleport.home.SethomeCommand;
import ru.leroron.essentiels.commands.teleport.spawn.SetspawnCommand;
import ru.leroron.essentiels.commands.teleport.spawn.SpawnCommand;
import ru.leroron.essentiels.commands.teleport.warp.SetwarpCommand;
import ru.leroron.essentiels.commands.teleport.warp.WarpCommand;
import ru.leroron.essentiels.configs.*;
import ru.leroron.essentiels.listeners.ChatListener;
import ru.leroron.essentiels.listeners.MainListener;
import ru.leroron.essentiels.storage.BanStorage;

import java.util.Map;
import java.util.Set;

public class Main extends JavaPlugin {

    public static final Map<Player, String> asks = Maps.newHashMap(), reports = Maps.newHashMap();
    public static final Map<Player, Player> requests = Maps.newHashMap();
    public static final Set<Player> god = Sets.newHashSet(), vanish = Sets.newHashSet();

    private static Chat chat;

    public static Chat getChat() {
        return chat;
    }

    private static Main plugin;

    public Main() {
        plugin = this;
    }

    private boolean setupChat()
    {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    public static void accept(Player p) {
        if (check(p)) {
            Player p1 = requests.get(p);
            p1.teleport(p);
            requests.remove(p);
            p.sendMessage(MessageConfig.getMessage("call.accept.accept").replace("%player", getPrefix(p1) + p1.getName()));
        }
    }

    public static boolean check(Player p) {
        if (!requests.containsKey(p)) {
            String s = MessageConfig.getMessage("call.notrequests");
            p.sendMessage(s);
            return false;
        }
        return true;
    }

    public static void deny(Player p) {
        if (check(p)) {
            Player p1 = requests.get(p);
            p.sendMessage(MessageConfig.getMessage("call.deny.deny").replace("%player", getPrefix(p1) + p1.getName()));
            requests.remove(p);
        }
    }

    public void onEnable() {

        if(!setupChat()) {
            getLogger().severe("Please install chat plugin. Disabling...");
            getPluginLoader().disablePlugin(this);
            return;
        }

        MainConfig.loadConfig(this);
        HomeConfig.loadConfig(this);
        WarpConfig.loadConfig(this);
        BanStorage.loadBans(this);
        MessageConfig.loadConfig(this);

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ChatListener(), this);
        pluginManager.registerEvents(new MainListener(), this);

        CommandRegister.reg(this, new GamemodeCommand(), new String[]{"gamemode", "gm"});
        CommandRegister.reg(this, new MainCommand(), new String[]{"essentiels", "ess", "seltixess", "seltixessentiels", "se"});
        CommandRegister.reg(this, new BanCommand(), new String[]{"ban", "permban"});
        CommandRegister.reg(this, new FlyCommand(), new String[]{"fly", "flight"});
        CommandRegister.reg(this, new ClearCommand(), new String[]{"clear", "invclear", "inventoryclear"});
        CommandRegister.reg(this, new KickCommand(), new String[]{"kick"});
        CommandRegister.reg(this, new HealCommand(), new String[]{"heal"});
        CommandRegister.reg(this, new GodCommand(), new String[]{"god"});
        CommandRegister.reg(this, new SethomeCommand(), new String[]{"sethome"});
        CommandRegister.reg(this, new HomeCommand(), new String[]{"home"});
        CommandRegister.reg(this, new SetwarpCommand(), new String[]{"setwarp"});
        CommandRegister.reg(this, new WarpCommand(), new String[]{"warp"});
        CommandRegister.reg(this, new UnbanCommand(), new String[]{"unban", "pardon"});
        CommandRegister.reg(this, new SpawnCommand(), new String[]{"spawn"});
        CommandRegister.reg(this, new SetspawnCommand(), new String[]{"setspawn"});
        CommandRegister.reg(this, new CallCommand(), new String[]{"call", "tpa"});
        CommandRegister.reg(this, new ReportCommand(), new String[]{"report"});
        CommandRegister.reg(this, new FeedCommand(), new String[]{"feed"});
        CommandRegister.reg(this, new AskCommand(), new String[]{"ask"});
        CommandRegister.reg(this, new AskReplyCommand(), new String[]{"askreply"});
        CommandRegister.reg(this, new MsgCommand(), new String[]{"msg", "m", "message"});
        CommandRegister.reg(this, new MemoryCommand(), new String[]{"gc", "memory", "память"});
        CommandRegister.reg(this, new CallCommand(), new String[]{"call", "tpa"});
        CommandRegister.reg(this, new TpAcceptCommand(), new String[]{"tpaccept", "tpyes"});
        CommandRegister.reg(this, new TpDenyCommand(), new String[]{"tpdeny", "tpno"});
        CommandRegister.reg(this, new BroadcastCommand(), new String[]{"bc", "broadcast"});
        CommandRegister.reg(this, new VanishCommand(), new String[]{"vanish", "v"});
        CommandRegister.reg(this, new ChatClearCommand(), new String[]{"chatclear", "cclear"});
    }

    public static void a(String s, Object... o) {
        System.out.println(String.format(s, o));
    }

    public static Main getPlugin() {
        return plugin;
    }
    
    public static String getPrefix(Player p) {
        return ChatColor.translateAlternateColorCodes('&', chat.getGroupPrefix(p.getWorld(), chat.getPrimaryGroup(p)));
    }

    public static String getSuffix(Player p) {
        return ChatColor.translateAlternateColorCodes('&', chat.getGroupSuffix(p.getWorld(), chat.getPrimaryGroup(p)));
    }
}
