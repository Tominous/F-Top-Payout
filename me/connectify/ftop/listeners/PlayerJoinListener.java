package me.connectify.ftop.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.connectify.ftop.utils.Constants.translate;

public class PlayerJoinListener implements Listener {

    private final FileConfiguration config;

    public PlayerJoinListener(FileConfiguration config) {
        this.config = config;
    }

    @EventHandler void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (config.get("players." + player.getName()) == null) return;

        player.sendMessage(translate(config.getString("messages.join-message")));
    }
}
