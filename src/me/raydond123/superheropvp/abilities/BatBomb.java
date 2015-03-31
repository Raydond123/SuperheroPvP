package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;

public class BatBomb implements Listener {

    SuperheroPvP plugin;

    public BatBomb(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            final Player player = e.getPlayer();

            if(player.getItemInHand().getType() == Material.TNT && player.getItemInHand().hasItemMeta()) {

                if(!cooldown.contains(player.getName())) {

                    TNTPrimed tnt = player.getWorld().spawn(player.getEyeLocation(), TNTPrimed.class);
                    tnt.setFuseTicks(20 * plugin.getConfig().getInt("batbomb-fuse-ticks"));
                    tnt.setVelocity(player.getLocation().getDirection().multiply(0.95));

                    cooldown.add(player.getName());

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your Bomb ability is ready to use again!");

                        }

                    }, 20L * plugin.getConfig().getInt("batbomb-cooldown"));

                    player.sendMessage(ChatColor.BLUE + "You used Bomb!");

                } else {

                    player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

                }

            }

        }

    }

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {

        Entity entity = e.getEntity();
        Location eLoc = entity.getLocation();

        if(entity.getType() == EntityType.PRIMED_TNT) {

            e.setCancelled(true);
            eLoc.getWorld().createExplosion(eLoc.getX(), eLoc.getY(), eLoc.getZ(), 2, false, false);

        }

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.TNT && held.hasItemMeta()) {

            e.setCancelled(true);

        }

    }

}
