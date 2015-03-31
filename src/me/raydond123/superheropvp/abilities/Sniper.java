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

import java.util.HashMap;
import java.util.HashSet;

public class Sniper implements Listener {

    SuperheroPvP plugin;

    public Sniper(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();
    HashMap<String, Integer> data = new HashMap<String, Integer>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if(held.getType() == Material.WOOD_HOE && held.hasItemMeta()) {

                int ammoLeft = data.get(player.getName());

                if(ammoLeft != 0) {

                    shoot(player);
                    ammoLeft--;
                    data.put(player.getName(), ammoLeft);

                } else {

                    player.sendMessage(ChatColor.BLUE + "This weapon is out of ammo!");

                }

            }

        }

    }

    public void shoot(Player player) {

        plugin.fireGun.shoot(player, 20.0, 75, 0.15, 10);
        player.getWorld().playSound(player.getLocation(), Sound.BLAZE_HIT, 1, 0);
        player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 0);

    }

    public void startClock() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            @Override
            public void run() {

                for(Player player : Bukkit.getOnlinePlayers()) {

                    if (data.get(player.getName()) == null) {

                        data.put(player.getName(), plugin.getConfig().getInt("sniper-ammo"));

                    } else {

                        int ammoLeft = data.get(player.getName());

                        if(ammoLeft < plugin.getConfig().getInt("sniper-ammo")) {

                            ammoLeft++;
                            data.put(player.getName(), ammoLeft);

                        }

                    }

                }

            }

        }, 0L, 20L * plugin.getConfig().getInt("sniper-reload-speed"));

    }

}
