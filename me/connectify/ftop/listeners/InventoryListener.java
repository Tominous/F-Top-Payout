package me.connectify.ftop.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (e.getInventory().getTitle().equalsIgnoreCase("Payout")) {
            e.setCancelled(true);
        }
    }
}
