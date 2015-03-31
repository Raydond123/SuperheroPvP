package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;

public class InvisibilityCloak implements Listener {

    SuperheroPvP plugin;

    public InvisibilityCloak(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            final Player player = e.getPlayer();

            if(player.getItemInHand().getType() == Material.CHAINMAIL_CHESTPLATE && player.getItemInHand().hasItemMeta()) {

                if(!cooldown.contains(player.getName())) {

                    cooldown.add(player.getName());

                    for(Player player1 : Bukkit.getOnlinePlayers()) {

                        player1.hidePlayer(player);

                    }

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            for(Player player1 : Bukkit.getOnlinePlayers()) {

                                player1.showPlayer(player);

                            }

                            player.sendMessage(ChatColor.BLUE + "You are now unhidden from players!");

                        }

                    }, 20L * plugin.getConfig().getInt("inviscloak-time"));

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your Invisibility Cloak ability is ready to use again!");

                        }

                    }, 20L * plugin.getConfig().getInt("inviscloak-cooldown"));

                    player.sendMessage(ChatColor.BLUE + "You used Invisiblity Cloak!");

                } else {

                    player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

                }

            }

        }

    }

}
