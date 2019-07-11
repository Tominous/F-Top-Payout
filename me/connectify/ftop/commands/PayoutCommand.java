package me.connectify.ftop.commands;

import me.connectify.ftop.Main;
import me.connectify.ftop.utils.Constants;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayoutCommand implements CommandExecutor {

    private Main main;

    public PayoutCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission(Constants.GLOBAL_PERM + "payout")) {
                if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {

                        main.getConfig().set("players." + target.getName(), args[1]);
                        main.saveConfig();
                        player.sendMessage(Constants.translate(main.getConfig().getString("messages.payout-successful")).replace("%target", target.getName()).replace("%code", args[1]));
                    } else {
                        player.sendMessage(Constants.translate(main.getConfig().getString("messages.target-offline")));
                    }
                } else {
                    player.sendMessage(Constants.translate("&cUsage: /Payout <name> <Store-Code>"));
                }
            } else {
                player.sendMessage(Constants.NO_PERMS);
            }
        } else {
            sender.sendMessage(Constants.PLAYER_ONLY);
        }
        return true;
    }
}
