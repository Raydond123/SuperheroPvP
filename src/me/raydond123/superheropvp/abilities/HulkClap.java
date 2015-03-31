package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import me.raydond123.superheropvp.utils.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class HulkClap implements Listener {

    SuperheroPvP plugin;

    public HulkClap(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();
    HashMap<String, Integer> data = new HashMap<String, Integer>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(!cooldown.contains(player.getName())) {

            if (held.getType() == Material.BRICK && held.hasItemMeta()) {

                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

                    cooldown.add(player.getName());

                    final int[] counter = {1};

                    Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            if (counter[0] < 8) {

                                generateFilledCylinder(player.getLocation().add(0, 1, 0), counter[0], 1);
                                int time = counter[0];
                                time++;
                                counter[0] = time;

                            }

                        }

                    }, 0L, 5L);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your Hulk Clap ability is ready to use again!");

                        }

                    }, 20L * plugin.getConfig().getInt("hulkclap-cooldown"));

                    player.sendMessage(ChatColor.BLUE + "You used your Hulk Clap ability!");

                    for(Player otherPlayer : Bukkit.getOnlinePlayers()) {

                        if(otherPlayer.getLocation().distanceSquared(player.getLocation()) <= 64) {

                            if (otherPlayer != player) {

                                double x = otherPlayer.getLocation().getX()
                                        - player.getLocation().getX();
                                double y = otherPlayer.getLocation().getY()
                                        - player.getLocation().getY();
                                double z = otherPlayer.getLocation().getZ()
                                        - player.getLocation().getZ();
                                Vector throwVelocity = new Vector(x * 1.5,
                                        y + 1, z * 1.5);
                                otherPlayer.setVelocity(throwVelocity);

                                otherPlayer.damage(4);

                            }

                        }

                    }

                }

            }

        } else {

            player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

        }

    }

    public void generateFilledCylinder(Location origin, int radius, int height) {

        // Array list for bottom level of cylinder
        ArrayList<Location> list = new ArrayList<Location>();

        // Iterate through every block in the diameter
        for (int x = -radius; x <= radius; x++) {

            // Calculate corresponding 'y' point at each 'x' point in the circle
            // We call it 'z' here, because minecraft's value on the horizontal plane are 'x' and 'z'
            int zmax = (int) Math.round(Math.sqrt((radius * radius) - (x * x)));
            int zmin = -zmax;

            // Iterate from minimum to maximum 'y' value and add points for all the blocks in between
            for (int z = zmin; z <= zmax; z++) {
                list.add(origin.clone().add(x, 0, z));
            }
        }

        // At this point, we've created an ArrayList for all the blocks for one level of the cylinder

        // Now we just go through them for each level until we reach the desired height, setting blocks accordingly
        for (int h = 0; h < height; h++) {
            for (Location loc : list) {
                ParticleEffect.CLOUD.display(0, 0, 0, 0, 1, loc.add(0.5, 0.5, 0.5), 1000);
            }
        }

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.BRICK && held.hasItemMeta()) {

            e.setCancelled(true);

        }

    }

}
