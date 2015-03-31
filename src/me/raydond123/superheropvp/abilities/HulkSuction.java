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
import java.util.HashSet;

public class HulkSuction implements Listener {

    SuperheroPvP plugin;

    public HulkSuction(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if (held.getType() == Material.HOPPER && held.hasItemMeta()) {

            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

                if(!cooldown.contains(player.getName())) {

                    cooldown.add(player.getName());

                    player.sendMessage(ChatColor.BLUE + "You used Hulk Suction!");

                    for (Player otherPlayer : Bukkit.getOnlinePlayers()) {

                        if (player.getLocation().distanceSquared(otherPlayer.getLocation()) <= 36) {

                            generateFilledCylinder(player.getLocation(), 6, 1);
                            pullPlayerToLocation(otherPlayer, player.getLocation());

                        }

                    }

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your Hulk Suction ability is ready to use again!");

                        }

                    }, 20L * plugin.getConfig().getInt("hulksuction-cooldown"));

                } else {

                    player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

                }

            }

        }

    }

    private void pullPlayerToLocation(final Player p, Location loc){

        Location entityLoc = p.getLocation();

        entityLoc.setY(entityLoc.getY()+0.5);
        p.teleport(entityLoc);

        double g = -0.08;
        double t = loc.distance(entityLoc);
        double v_x = (1.0 + 0.07 * t) * (loc.getX() - entityLoc.getX()) / t;
        double v_y = (1.0 + 0.03 * t) * (loc.getY() - entityLoc.getY()) / t - 0.5 * g * t;
        double v_z = (1.0 + 0.07 * t) * (loc.getZ() - entityLoc.getZ()) / t;

        Vector v = p.getVelocity();
        v.setX(v_x);
        v.setY(v_y);
        v.setZ(v_z);
        p.setVelocity(v);

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

        if(held.getType() == Material.HOPPER && held.hasItemMeta()) {

            e.setCancelled(true);

        }

    }

}
