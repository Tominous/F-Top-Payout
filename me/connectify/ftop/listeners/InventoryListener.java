package me.connectify.ftop.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    private final FileConfiguration config;

    public InventoryListener(FileConfiguration config) {
        this.config = config;
    }

    @EventHandler void onInventoryClick(InventoryClickEvent e) {
        if (!e.getInventory().getTitle().equalsIgnoreCase(config.getString("gui.title"))) return;

        e.setCancelled(true);
    }
}
