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

import java.util.HashSet;

public class WebShot implements Listener {

    SuperheroPvP plugin;

    public WebShot(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.WEB && held.hasItemMeta()) {

            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

                if(!cooldown.contains(player.getName())) {

                    cooldown.add(player.getName());

                    Byte blockData = 0x0;
                    final FallingBlock block = player.getWorld().spawnFallingBlock(player.getLocation().add(0, 1, 0), Material.WEB, blockData);

                    block.setDropItem(false);

                    block.setVelocity(player.getLocation().getDirection().multiply(1));

                    player.sendMessage(ChatColor.BLUE + "You used Web Shot!");

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your Web Shot ability is ready to use again!");

                        }

                    }, 20L * plugin.getConfig().getInt("webshot-cooldown"));

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            if(block.getLocation() != null) {

                                Location loc = block.getLocation();
                                loc.getBlock().setType(Material.AIR);

                            }

                        }

                    }, 20L * plugin.getConfig().getInt("webshot-remove-delay"));

                } else {

                    player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

                }

            }

        }

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.WEB && held.hasItemMeta()) {

            e.setCancelled(true);

        }

    }

}
