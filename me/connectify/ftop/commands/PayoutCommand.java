package me.connectify.ftop.commands;

import me.connectify.ftop.FTop;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static me.connectify.ftop.utils.Constants.*;

public class PayoutCommand implements CommandExecutor {

    private final FTop main;
    private final FileConfiguration config;

    public PayoutCommand(FTop main) {
        this.main = main;
        this.config = main.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        //Checking if player has permission.
        if (!player.hasPermission(GLOBAL_PERM + "payout")) {
            player.sendMessage(NO_PERMS);
            return true;
        }
        //Checking if arguments are less than 2 and sending a usage message.
        if (args.length < 1) {
            player.sendMessage(translate("&cUsage: /Payout <name> <Store-Code>"));
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        //Making sure the target is online
        if (target != null) {
            config.set("players." + target.getName(), args[1]);
            main.saveConfig();
            player.sendMessage(translate(config.getString("messages.payout-successful")).replace("%target", target.getName()).replace("%code", args[1]));
            return true;
        }
        player.sendMessage(translate(config.getString("messages.target-offline")));
        return true;
    }
}
