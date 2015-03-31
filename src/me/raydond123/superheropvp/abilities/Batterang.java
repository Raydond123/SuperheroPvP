package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import me.raydond123.superheropvp.utils.ParticleEffect;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.HashSet;

public class Batterang implements Listener {

    SuperheroPvP plugin;

    public Batterang(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();
    HashMap<Integer, Integer> data = new HashMap<Integer, Integer>();
    HashMap<Player, Location> blockData = new HashMap<Player, Location>();

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (held.getType() == Material.ARROW && held.hasItemMeta()) {

                if (!cooldown.contains(player.getName())) {

                    Location eyeLocation = player.getEyeLocation();
                    Vector vec = player.getLocation().getDirection();
                    Location frontLocation = eyeLocation.add(vec);

                    final Snowball snowball = (Snowball) player.getWorld().spawnEntity(frontLocation, EntityType.SNOWBALL);
                    Vector direction = player.getLocation().getDirection();
                    Vector vector = direction.multiply(2);
                    snowball.setVelocity(vector);

                    cooldown.add(player.getName());

                    player.sendMessage(ChatColor.BLUE + "You used your Batterang!");

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your Batterang is ready to use again!");

                        }

                    }, 20L * plugin.getConfig().getInt("batterang-cooldown"));

                    data.put(snowball.getEntityId(), Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            Location loc = snowball.getLocation();
                            ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, loc, 1000);

                        }

                    }, 0L, 1L));

                } else {

                    player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

                }

            }

        }

    }

    @EventHandler
    public void onHit(ProjectileHitEvent e) {

        Entity entity = e.getEntity();
        int id = entity.getEntityId();

        if(data.containsKey(id)) {

            int task = data.get(entity.getEntityId());
            Bukkit.getScheduler().cancelTask(task);
            data.remove(id);

        }

    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {

        Entity entity = e.getEntity();
        Entity attack = e.getDamager();
        int id = attack.getEntityId();

        if(data.containsKey(id)) {

            e.setDamage(plugin.getConfig().getInt("batterang-damage"));

        }

    }

}
