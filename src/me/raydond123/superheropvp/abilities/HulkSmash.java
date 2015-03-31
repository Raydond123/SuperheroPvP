package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashSet;

public class HulkSmash implements Listener {

    SuperheroPvP plugin;

    public HulkSmash(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.ANVIL && held.hasItemMeta()) {

            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

                if(!cooldown.contains(player.getName())) {

                    if (player.getLocation().add(0, -1, 0).getBlock().getType() != Material.AIR) {

                        generateFilledCylinder(player.getLocation().add(0, -1, 0), 4, 1);

                        cooldown.add(player.getName());
                        player.sendMessage(ChatColor.BLUE + "You used your Hulk Smash ability!");

                        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                            @Override
                            public void run() {

                                cooldown.remove(player.getName());
                                player.sendMessage(ChatColor.BLUE + "Your Hulk Smash ability has been replenished!");

                            }

                        }, 20L * plugin.getConfig().getInt("hulksmash-cooldown"));

                        for(Player otherPlayer : Bukkit.getOnlinePlayers()) {

                            if(otherPlayer.getLocation().distanceSquared(player.getLocation()) <= 16) {

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

                    } else {

                        player.sendMessage(ChatColor.BLUE + "You must be on the ground to use this!");

                    }

                } else {

                    player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

                }

            }

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
                origin.getWorld().playEffect(loc, Effect.STEP_SOUND, loc.getBlock().getTypeId());
            }
        }

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.ANVIL && held.hasItemMeta()) {

            e.setCancelled(true);

        }

    }

}
