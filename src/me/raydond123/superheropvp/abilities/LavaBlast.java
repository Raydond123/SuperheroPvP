package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.*;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Random;

public class LavaBlast implements Listener {

    SuperheroPvP plugin;

    public LavaBlast(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    int checker;

    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if(held.getType() == Material.LAVA_BUCKET && held.hasItemMeta()) {

                if(!cooldown.contains(player.getName())) {

                    cooldown.add(player.getName());

                    player.sendMessage(ChatColor.BLUE + "You used your Lava Blast ability!");

                    int counter = 0;

                    for(int i = 0; i <= plugin.getConfig().getInt("lavablast-amount"); i++) {

                        Byte data = null;

                        if(counter == 0) {
                            counter = 1;
                            data = 14;
                        } else if(counter == 1) {
                            counter = 0;
                            data = 1;
                        }

                        final FallingBlock lava = player.getWorld().spawnFallingBlock(player.getLocation(), Material.WOOL, data);
                        lava.setDropItem(false);

                        Vector direction = player.getLocation().getDirection();
                        Vector shoot = direction.add(new Vector(random(), random(), random()));
                        lava.setVelocity(shoot);

                        checker = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

                            @Override
                            public void run() {

                                Location location = lava.getLocation();

                                for(Player player1 : lava.getWorld().getPlayers()) {

                                    if(player1 != player) {

                                        if (player1.getLocation().distanceSquared(location) <= 1) {

                                            lava.getWorld().playEffect(lava.getLocation(), Effect.STEP_SOUND, lava.getLocation().getBlock().getTypeId(), lava.getBlockData());
                                            lava.remove();
                                            lava.getLocation().getBlock().setType(Material.AIR);

                                            player.damage(plugin.getConfig().getDouble("lavablast-damage"));

                                            Bukkit.broadcastMessage("test");

                                            Bukkit.getScheduler().cancelTask(checker);

                                        }

                                    }

                                }

                            }

                        }, 0L, 1L);

                        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                            @Override
                            public void run() {

                                if(lava != null) {

                                    lava.getWorld().playEffect(lava.getLocation(), Effect.STEP_SOUND, lava.getLocation().getBlock().getTypeId(), lava.getBlockData());
                                    lava.remove();
                                    lava.getLocation().getBlock().setType(Material.AIR);

                                    Bukkit.getScheduler().cancelTask(checker);

                                }

                            }

                        }, 20L * plugin.getConfig().getInt("lavablast-delay"));

                    }

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your Lava Blast ability has been replenished!");

                        }

                    }, 20L * plugin.getConfig().getInt("lavablast-cooldown"));

                } else {

                    player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

                }

            }

        }

    }

    public double random() {

        double max = -0.2;
        double min = 0.2;

        Random random = new Random();

        double rand = min + (max - min) * random.nextDouble();

        return rand;

    }

    @EventHandler
    public void onPlace(PlayerBucketEmptyEvent e) {

        Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.LAVA_BUCKET && held.hasItemMeta()) {

            e.setCancelled(true);

        }

    }

}
