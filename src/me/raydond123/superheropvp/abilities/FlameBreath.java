package me.raydond123.superheropvp.abilities;

import de.slikey.effectlib.effect.DragonEffect;
import me.raydond123.superheropvp.SuperheroPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;

public class FlameBreath implements Listener {

    SuperheroPvP plugin;

    public FlameBreath(SuperheroPvP plugin) {

        this.plugin = plugin;

    }

    HashSet<String> cooldown = new HashSet<String>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(held.getType() == Material.FIREBALL && held.hasItemMeta()) {

            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

                if(!cooldown.contains(player.getName())) {

                    cooldown.add(player.getName());

                    player.sendMessage(ChatColor.BLUE + "You used Flame Breath!");

                    DragonEffect dragonEffect = new DragonEffect(plugin.effectManager);
                    dragonEffect.setLocation(player.getEyeLocation());
                    dragonEffect.iterations = 15;
                    dragonEffect.start();

                    for (Block block : player.getLineOfSight(null, 4)) {

                        for (Player otherPlayer : player.getWorld().getPlayers()) {

                            if(otherPlayer != player) {

                                if (otherPlayer.getLocation().distanceSquared(block.getLocation()) <= 3) {

                                    otherPlayer.setFireTicks(40);

                                }

                            }

                        }

                    }

                    player.getWorld().playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 10, 0);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            cooldown.remove(player.getName());
                            player.sendMessage(ChatColor.BLUE + "Your Flame Breath ability has been replenished!");

                        }

                    }, 20L * plugin.getConfig().getInt("flamebreath-cooldown"));

                } else {

                    player.sendMessage(ChatColor.BLUE + "This item is under a cooldown!");

                }

            }

        }

    }

}
