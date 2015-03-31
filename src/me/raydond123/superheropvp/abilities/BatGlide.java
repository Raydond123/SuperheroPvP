package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashSet;

public class BatGlide implements Listener {

    SuperheroPvP plugin;

    public BatGlide(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onRClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.FEATHER && held.hasItemMeta()) {

            if(!cooldown.contains(player.getName())) {

                Vector direction = player.getLocation().getDirection();
                Vector vector = direction.multiply(2).setY(0);

                player.setVelocity(vector);

                cooldown.add(player.getName());

                player.sendMessage(ChatColor.BLUE + "You used BatGlide!");

                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                    @Override
                    public void run() {

                        cooldown.remove(player.getName());
                        player.sendMessage(ChatColor.BLUE + "Your BatGlide ability is ready to use again!");

                    }

                }, 20L * plugin.getConfig().getInt("batglide-cooldown"));

            } else {

                player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

            }

        }

    }

}
