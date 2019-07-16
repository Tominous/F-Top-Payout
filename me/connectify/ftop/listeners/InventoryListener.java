package me.connectify.ftop.listeners;

import me.connectify.ftop.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    private Main main;

    public InventoryListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (e.getInventory().getTitle().equalsIgnoreCase(main.getConfig().getString("gui.title"))) {
            e.setCancelled(true);
        }
    }
}
