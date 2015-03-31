package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
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
import java.util.List;

public class SpiderSurround implements Listener {

    SuperheroPvP plugin;

    public SpiderSurround(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();

    Player player;

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if (held.getType() == Material.CAULDRON_ITEM && held.hasItemMeta()) {

            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

                if (!cooldown.contains(player.getName())) {

                    cooldown.add(player.getName());

                    player.sendMessage(ChatColor.BLUE + "You used your Web Surround ability!");

                    Byte b = 0x0;
                    final FallingBlock web = player.getWorld().spawnFallingBlock(player.getLocation(), Material.WEB, b);
                    web.setDropItem(false);

                    web.setVelocity(player.getLocation().getDirection());

                    final HashMap<Location, Material> data = new HashMap<Location, Material>();

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            player.sendMessage(ChatColor.BLUE + "Your WebSurround ability has been replenished!");
                            cooldown.remove(player.getName());

                        }

                    }, 20L * plugin.getConfig().getInt("spidersurround-cooldown"));

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            if(web.getLocation() != null) {

                                for(Location loc : generateSphere(web.getLocation(), 8, true)) {

                                    data.put(loc, loc.getBlock().getType());
                                    Location location = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
                                    location.getBlock().setType(Material.WEB);

                                }

                            }

                        }

                    }, 20L * plugin.getConfig().getInt("spidersurround-time"));

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            if(web.getLocation() != null) {

                                for(Location loc : generateSphere(web.getLocation(), 8, true)) {

                                    loc.getBlock().setType(data.get(loc));

                                }

                                web.getLocation().getBlock().setType(Material.AIR);

                            }

                        }

                    }, 20L * plugin.getConfig().getInt("spidersurround-delay") + (20L * plugin.getConfig().getInt("spidersurround-time")));

                } else {

                    player.sendMessage(ChatColor.BLUE + "This item is currently under a cooldown!");

                }

            }

        }

    }

    public static List<Location> generateSphere(Location centerBlock, int radius, boolean hollow) {

        List<Location> circleBlocks = new ArrayList<Location>();

        int bx = centerBlock.getBlockX();
        int by = centerBlock.getBlockY();
        int bz = centerBlock.getBlockZ();

        for(int x = bx - radius; x <= bx + radius; x++) {
            for(int y = by - radius; y <= by + radius; y++) {
                for(int z = bz - radius; z <= bz + radius; z++) {

                    double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)) + ((by-y) * (by-y)));

                    if(distance < radius * radius && !(hollow && distance < ((radius - 1) * (radius - 1)))) {

                        Location l = new Location(centerBlock.getWorld(), x, y, z);

                        circleBlocks.add(l);

                    }

                }
            }
        }

        return circleBlocks;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.CAULDRON_ITEM && held.hasItemMeta()) {

            e.setCancelled(true);

        }

    }

}
