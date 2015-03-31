package me.raydond123.superheropvp.commands;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandExecutor{

    SuperheroPvP plugin;

    public KitCommand(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player) sender;

            if(plugin.kits.get(player) == null) {

                player.openInventory(plugin.kitGUI.inv);

            } else {

                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You already have a kit!");

            }

        } else {

            plugin.logger.info("That command can only be used by a player!");

        }

        return false;

    }
}
