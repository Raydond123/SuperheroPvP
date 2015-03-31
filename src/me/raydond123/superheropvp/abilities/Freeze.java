package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import me.raydond123.superheropvp.utils.ParticleEffect;
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

public class Freeze implements Listener {

    SuperheroPvP plugin;

    public Freeze(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onRClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.SNOW_BLOCK && held.hasItemMeta()) {

            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

                if(!cooldown.contains(player.getName())) {

                    cooldown.add(player.getName());

                    generateFilledCylinder(player.getLocation(), 5, 1);

                    player.sendMessage(ChatColor.BLUE + "You used your Freeze ability!");

                    for(Player otherPlayer : Bukkit.getOnlinePlayers()) {

                        if(otherPlayer != player) {

                            if (player.getLocation().distanceSquared(otherPlayer.getLocation()) <= 25) {

                                setBlocks(otherPlayer);

                            }

                        }

                    }

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your Freeze ability has been replenished!");

                        }

                    }, 20L * plugin.getConfig().getInt("freeze-cooldown"));

                } else {

                    player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

                }

            }

        }

    }

    public void setBlocks(final Player player) {

        Location loc = player.getLocation();
        player.teleport(loc.add(0, 3, 0));

            final int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            @Override
            public void run() {

                player.setVelocity(new Vector(0, 0, 0));

                player.getWorld().playEffect(player.getLocation(), Effect.STEP_SOUND, 174);

            }

        }, 0L, 1L);

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

            @Override
            public void run() {

                Bukkit.getScheduler().cancelTask(task);

            }

        }, 20L * plugin.getConfig().getInt("freeze-time"));

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
                ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, loc.add(0.5, 0.5, 0.5), 1000);
            }
        }

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.SNOW_BLOCK && held.hasItemMeta()) {

            e.setCancelled(true);

        }

    }

}
