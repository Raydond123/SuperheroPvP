package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Pistol implements Listener {

    SuperheroPvP plugin;

    public Pistol(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<Player> shooting = new HashSet<Player>();
    HashMap<String, Integer> data = new HashMap<String, Integer>();

    @EventHandler
    public void onShoot(PlayerInteractEvent e) {

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (e.getPlayer().getItemInHand().getType() == Material.IRON_HOE) {

                final Player player = e.getPlayer();

                if(!shooting.contains(player)) {

                    int ammoLeft = data.get(player.getName());

                    if(ammoLeft != 0) {

                        plugin.fireGun.shoot(player, 6.0, 30, 0.15, 10);
                        player.getWorld().playSound(player.getLocation(), Sound.IRONGOLEM_HIT, 1, 2);
                        player.getWorld().playSound(player.getLocation(), Sound.ZOMBIE_METAL, 1, 2);
                        shooting.add(player);

                        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                            @Override
                            public void run() {

                                shooting.remove(player);

                            }

                        }, 6L);

                    } else {

                        player.sendMessage(ChatColor.BLUE + "This weapon is out of ammo!");

                    }

                }

            }

        }

    }

    public void startClock() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            @Override
            public void run() {

                for(Player player : Bukkit.getOnlinePlayers()) {

                    if (data.get(player.getName()) == null) {

                        data.put(player.getName(), plugin.getConfig().getInt("pistol-ammo"));

                    } else {

                        int ammoLeft = data.get(player.getName());

                        if(ammoLeft < plugin.getConfig().getInt("pistol-ammo")) {

                            ammoLeft++;
                            data.put(player.getName(), ammoLeft);

                        }

                    }

                }

            }

        }, 0L, 20L * plugin.getConfig().getInt("pistol-reload-speed"));

    }

}
