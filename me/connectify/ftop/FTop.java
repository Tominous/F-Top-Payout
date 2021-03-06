package me.connectify.ftop;

import me.connectify.ftop.commands.ClaimPayoutCommand;
import me.connectify.ftop.commands.PayoutCommand;
import me.connectify.ftop.listeners.InventoryListener;
import me.connectify.ftop.listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;

public class FTop extends JavaPlugin {

    public void onEnable() {
        createConfig();
        registerCommands();
        registerListeners();
    }

    private void registerCommands() {
        getCommand("payout").setExecutor(new PayoutCommand(this));
        getCommand("claimpayout").setExecutor(new ClaimPayoutCommand(getConfig()));
    }

    private void registerListeners() {
        Arrays.asList(
                new InventoryListener(getConfig()),
                new PlayerJoinListener(getConfig())
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating!");

                getConfig().addDefault("messages.payout-successful", "&7You have successfully set &c%target &7Store code to &c%code");
                getConfig().addDefault("messages.target-offline", "&cTarget is currently offline.");
                getConfig().addDefault("messages.join-message", "&7You were given a &cStore Code &7by staff, do &c/claimpayout &7To claim it.");

                getConfig().addDefault("gui.title", "Payout");
                getConfig().addDefault("gui.no-code", "&7Store Code: &cNone");
                getConfig().addDefault("gui.code", "&7Store Code: %code");
                getConfig().addDefault("gui.item-name", "&c&lPayout Code");
                getConfig().options().copyDefaults(true);
                saveConfig();
                return;
            }
            getLogger().info("Config.yml found, loading!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
