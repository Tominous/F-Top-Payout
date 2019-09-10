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
            //Checking if player has permission.
            if (!player.hasPermission(Constants.GLOBAL_PERM + "payout")) {
                player.sendMessage(Constants.NO_PERMS);
                return true;
            }
            //Checking if arguments are less than 2 and sending a usage message.
            if (args.length < 1) {
                player.sendMessage(Constants.translate("&cUsage: /Payout <name> <Store-Code>"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            //Making sure the target is online
            if (target != null) {
                main.getConfig().set("players." + target.getName(), args[1]);
                main.saveConfig();
                player.sendMessage(Constants.translate(main.getConfig().getString("messages.payout-successful")).replace("%target", target.getName()).replace("%code", args[1]));
                return true;
            }
            player.sendMessage(Constants.translate(main.getConfig().getString("messages.target-offline")));
            return true;
        }
        sender.sendMessage(Constants.PLAYER_ONLY);
        return true;
    }
}
