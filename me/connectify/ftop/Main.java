package me.connectify.ftop;

import me.connectify.ftop.commands.ClaimPayoutCommand;
import me.connectify.ftop.commands.PayoutCommand;
import me.connectify.ftop.listeners.InventoryListener;
import me.connectify.ftop.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    public void onEnable() {
        createConfig();
        registerCommands();
        registerListeners();

    }

    private void registerCommands() {
        getCommand("payout").setExecutor(new PayoutCommand(this));
        getCommand("claimpayout").setExecutor(new ClaimPayoutCommand(this));

    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);

    }

    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating!");

                saveDefaultConfig();
            } else {
                getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
