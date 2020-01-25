package me.connectify.ftop.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

import static me.connectify.ftop.utils.Constants.translate;

public class ClaimPayoutCommand implements CommandExecutor {

    private final FileConfiguration config;

    public ClaimPayoutCommand(FileConfiguration config) {
        this.config = config;
    }

    @Override public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return true;

        Inventory payout = Bukkit.createInventory(null, 9, config.getString("gui.title"));
        ItemStack itemStack = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        Player player = (Player) sender;

        if (config.get("players." + player.getName()) != null) {
            itemMeta.setLore(Collections.singletonList(translate(config.getString("gui.code")).replace("%code", config.getString("players." + player.getName()))));
        } else {
            itemMeta.setLore(Collections.singletonList(translate(config.getString("gui.no-code"))));
        }

        itemMeta.setDisplayName(translate(config.getString("gui.item-name")));
        itemStack.setItemMeta(itemMeta);
        payout.setItem(4, itemStack);
        player.openInventory(payout);
        return true;
    }
}
