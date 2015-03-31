package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.*;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Random;

public class IceBash implements Listener {

    SuperheroPvP plugin;

    public IceBash(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    int checker;
    
    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if(held.getType() == Material.PACKED_ICE && held.hasItemMeta()) {

                if(!cooldown.contains(player.getName())) {

                    cooldown.add(player.getName());

                    player.sendMessage(ChatColor.BLUE + "You used your Ice Bash ability!");

                    for(int i = 0; i <= plugin.getConfig().getInt("icebash-amount"); i++) {

                        Byte data = 0x0;
                        final FallingBlock ice = player.getWorld().spawnFallingBlock(player.getLocation(), Material.PACKED_ICE, data);
                        ice.setDropItem(false);

                        Vector direction = player.getLocation().getDirection();
                        Vector shoot = direction.add(new Vector(random(), random(), random()));
                        ice.setVelocity(shoot);

                        checker = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

                            @Override
                            public void run() {

                                Location location = ice.getLocation();

                                for(Player player1 : ice.getWorld().getPlayers()) {

                                    if(player1 != player) {

                                        if (player1.getLocation().distanceSquared(location) <= 1) {

                                            ice.getWorld().playEffect(ice.getLocation(), Effect.STEP_SOUND, ice.getLocation().getBlock().getTypeId(), ice.getBlockData());
                                            ice.remove();
                                            ice.getLocation().getBlock().setType(Material.AIR);

                                            player.damage(plugin.getConfig().getDouble("icebash-damage"));

                                            Bukkit.getScheduler().cancelTask(checker);

                                        }

                                    }

                                }

                            }

                        }, 0L, 1L);
                        
                        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                            @Override
                            public void run() {

                                if(ice != null) {

                                    ice.getWorld().playEffect(ice.getLocation(), Effect.STEP_SOUND, ice.getLocation().getBlock().getTypeId(), ice.getBlockData());
                                    ice.getLocation().getBlock().setType(Material.AIR);
                                    ice.remove();

                                    Bukkit.getScheduler().cancelTask(checker);

                                }

                            }

                        }, 20L * plugin.getConfig().getInt("icebash-delay"));

                    }

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your Ice Bash ability has been replenished!");

                        }

                    }, 20L * plugin.getConfig().getInt("icebash-cooldown"));

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
    public void onPlace(BlockPlaceEvent e) {

        Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.PACKED_ICE && held.hasItemMeta()) {

            e.setCancelled(true);

        }

    }


}
