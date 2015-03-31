package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class IceShooter implements Listener {

    SuperheroPvP plugin;

    public IceShooter(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();
    HashMap<String, Integer> data = new HashMap<String, Integer>();
    HashSet<Integer> data2 = new HashSet<Integer>();

    @EventHandler
    public void onRClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.ICE && held.hasItemMeta()) {

            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

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

    public void startClock() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            @Override
            public void run() {

                for(Player player : Bukkit.getOnlinePlayers()) {

                    if (data.get(player.getName()) == null) {

                        data.put(player.getName(), plugin.getConfig().getInt("iceshooter-ammo"));

                    } else {

                        int ammoLeft = data.get(player.getName());

                        if(ammoLeft < plugin.getConfig().getInt("iceshooter-ammo")) {

                            ammoLeft++;
                            data.put(player.getName(), ammoLeft);

                        }

                    }

                }

            }

        }, 0L, 20L * plugin.getConfig().getInt("iceshooter-reload-speed"));

    }

    public void shoot(Player player) {

        Location eyeLocation = player.getEyeLocation();
        Vector vec = player.getLocation().getDirection();
        Location frontLocation = eyeLocation.add(vec);

        Snowball snowball = (Snowball) player.getWorld().spawnEntity(frontLocation, EntityType.SNOWBALL);
        Vector direction = player.getLocation().getDirection();
        Vector vector = direction.add(new Vector(random(), random(), random()));
        snowball.setVelocity(vector);

        data2.add(snowball.getEntityId());

    }

    public double random() {

        double max = -0.1;
        double min = 0.1;

        Random random = new Random();

        double rand = min + (max - min) * random.nextDouble();

        return rand;

    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {

        Entity entity = e.getDamager();
        Entity hit = e.getEntity();

        if(hit instanceof Player) {

            if(data2.contains(entity.getEntityId())) {

                data2.remove(entity.getEntityId());
                e.setDamage(plugin.getConfig().getInt("iceshooter-damage"));

            }

        }

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.ICE && held.hasItemMeta()) {

            e.setCancelled(true);

        }

    }

}
