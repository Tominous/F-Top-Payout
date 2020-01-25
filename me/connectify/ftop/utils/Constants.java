package me.connectify.ftop.utils;

import org.bukkit.ChatColor;

public class Constants {

    public static String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static final String NO_PERMS = translate("&cNo Permission.");
    public static final String GLOBAL_PERM = "ftop.";

}

