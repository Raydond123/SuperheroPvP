package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import me.raydond123.superheropvp.utils.ParticleEffect;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Gun implements Listener {

    SuperheroPvP plugin;

    public Gun(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<Player> shooting = new HashSet<Player>();
    HashMap<String, Integer> data = new HashMap<String, Integer>();

    @EventHandler
    public void onShoot(PlayerInteractEvent e) {

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (e.getPlayer().getItemInHand().getType() == Material.IRON_BARDING) {

                final Player player = e.getPlayer();

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

        plugin.fireGun.shoot(player, 6.0, 30, 0.15, 10);
        player.getWorld().playSound(player.getLocation(), Sound.IRONGOLEM_HIT, 1, 2);
        player.getWorld().playSound(player.getLocation(), Sound.ZOMBIE_METAL, 1, 2);
        shooting.add(player);

    }

    public void startClock() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            @Override
            public void run() {

                for(Player player : Bukkit.getOnlinePlayers()) {

                    if (data.get(player.getName()) == null) {

                        data.put(player.getName(), plugin.getConfig().getInt("gun-ammo"));

                    } else {

                        int ammoLeft = data.get(player.getName());

                        if(ammoLeft < plugin.getConfig().getInt("gun-ammo")) {

                            ammoLeft++;
                            data.put(player.getName(), ammoLeft);

                        }

                    }

                }

            }

        }, 0L, 20L * plugin.getConfig().getInt("gun-reload-speed"));

    }

}
