package me.connectify.ftop.listeners;

import me.connectify.ftop.Main;
import me.connectify.ftop.utils.Constants;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private Main main;

    public PlayerJoinListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (main.getConfig().get("players." + player.getName()) != null) {
            player.sendMessage(Constants.translate("&7You were given a &cStore Code &7by staff, do &c/claimpayout &7To claim it."));
        }

    }
}
