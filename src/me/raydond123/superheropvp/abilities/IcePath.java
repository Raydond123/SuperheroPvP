package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class IcePath implements Listener {

    SuperheroPvP plugin;

    public IcePath(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (held.getType() == Material.DIAMOND_BLOCK && held.hasItemMeta()) {

                if (!cooldown.contains(player.getName())) {

                    cooldown.add(player.getName());

                    player.sendMessage(ChatColor.BLUE + "You used your Ice Path ability!");

                    int counter = 0;
                    int counter2 = 0;

                    for(Block block : player.getLineOfSight(null, 21)) {

                        if(counter == 0) {

                            final ArrayList<Location> blocks = generateFilledCylinder(block.getLocation(), 3, 1);

                            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                                @Override
                                public void run() {

                                    for(final Location loc : blocks) {

                                        if(loc.getBlock().getType() == Material.AIR) {

                                            loc.getWorld().playEffect(loc, Effect.STEP_SOUND, 174);
                                            loc.getBlock().setType(Material.PACKED_ICE);
                                            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                                                @Override
                                                public void run() {

                                                    loc.getBlock().setType(Material.AIR);
                                                    loc.getWorld().playEffect(loc, Effect.STEP_SOUND, 174);

                                                }

                                            }, 20L * plugin.getConfig().getInt("icepath-remove-delay"));

                                        }

                                    }

                                }

                            }, 10L * counter2);

                            counter2++;

                            counter++;

                        } else if(counter == 1) {

                            counter = 0;

                        }

                    }

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your Ice Path ability has been replenished!");

                        }

                    }, 20L * plugin.getConfig().getInt("icepath-cooldown"));

                } else {

                    player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

                }

            }

        }

    }

    public ArrayList<Location> generateFilledCylinder(Location origin, int radius, int height) {

        // Array list for bottom level of cylinder
        ArrayList<Location> list = new ArrayList<Location>();

        ArrayList<Location> result = new ArrayList<Location>();

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

                result.add(loc);

            }
        }

        return result;

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.hasItemMeta() && held.getType() == Material.DIAMOND_BLOCK) {

            e.setCancelled(true);

        }

    }

}
