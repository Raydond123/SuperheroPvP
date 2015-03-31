package me.raydond123.superheropvp.abilities;

import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashSet;

public class Choke implements Listener {

    SuperheroPvP plugin;

    public Choke(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onRClick(PlayerInteractAtEntityEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.LEASH && held.hasItemMeta()) {

            if(e.getRightClicked() instanceof Player) {

                if (!cooldown.contains(player.getName())) {

                    cooldown.add(player.getName());

                    player.sendMessage(ChatColor.BLUE + "You used your Choke ability!");

                    Player otherPlayer = (Player) e.getRightClicked();

                    setBlocks(otherPlayer);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your Choke ability has been replenished!");

                        }

                    }, 20L * plugin.getConfig().getInt("choke-cooldown"));

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

                player.getWorld().playEffect(player.getLocation(), Effect.STEP_SOUND, 152);

            }

        }, 0L, 1L);

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

            @Override
            public void run() {

                Bukkit.getScheduler().cancelTask(task);

            }

        }, 20L * plugin.getConfig().getInt("choke-time"));

    }

}
