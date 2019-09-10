package me.connectify.ftop.commands;

import me.connectify.ftop.Main;
import me.connectify.ftop.utils.Constants;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ClaimPayoutCommand implements CommandExecutor {

    private Main main;

    public ClaimPayoutCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        //Console checking
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory payout = Bukkit.createInventory(player, 9, main.getConfig().getString("gui.title"));
            ItemStack item = new ItemStack(Material.BOOK);
            List<String> lore = new ArrayList<>();

            ItemMeta itemMeta = item.getItemMeta();
            //Checking if a player has a code or not
            if (main.getConfig().get("players." + player.getName()) != null) {
                lore.add(Constants.translate(main.getConfig().getString("gui.code")).replace("%code", main.getConfig().getString("players." + player.getName())));
            } else {
                lore.add(Constants.translate(main.getConfig().getString("gui.no-code")));
            }

            //Setting the lore based on the results
            itemMeta.setLore(lore);
            itemMeta.setDisplayName(Constants.translate(main.getConfig().getString("gui.item-name")));
            item.setItemMeta(itemMeta);
            payout.setItem(4, item);

            //Opening inventory
            player.openInventory(payout);
            return true;
        }
        sender.sendMessage(Constants.PLAYER_ONLY);
        return true;
    }
}
